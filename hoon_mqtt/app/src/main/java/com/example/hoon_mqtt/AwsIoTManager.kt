package com.example.hoon_mqtt

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos
import com.google.gson.JsonObject
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONObject
import java.io.InputStream
import java.security.KeyFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import java.util.UUID

class AwsIotManager(private val context: Context) {
    // AWS IoT 설정값 (AWS 콘솔에서 확인)
    private val CUSTOMER_SPECIFIC_ENDPOINT = BuildConfig.AWS_IOT_ENDPOINT
    private val CLIENT_ID = UUID.randomUUID().toString()

    private var mqttManager: AWSIotMqttManager? = null

    init {
        mqttManager = AWSIotMqttManager(CLIENT_ID, CUSTOMER_SPECIFIC_ENDPOINT)
        mqttManager?.setKeepAlive(30)
        mqttManager?.setMaxAutoReconnectAttempts(
            Int.MAX_VALUE
        )
        mqttManager?.setReconnectRetryLimits(
            1,
            1
        )
        mqttManager?.setAutoResubscribe(true)
        mqttManager?.setAutoReconnect(false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun connect() {
        try {
            val keyStore = loadKeystoreFromAssets()
            mqttManager?.connect(keyStore) { status, throwable ->
                when (status) {
                    AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected -> {
                        println("✅ AWS IoT 연결 성공!")
                    }

                    AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting -> {
                        println("⚠️ AWS IoT 재연결 중...")
                    }

                    AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.ConnectionLost -> {
                        println("❌ AWS IoT 연결 끊어짐!")
                    }

                    AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connecting -> {
                        println("🔄 AWS IoT 연결 중...")
                    }
//                    AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Disconnected -> {
//                        println("🔌 AWS IoT 연결 해제됨!")
//                    }
                }
                throwable?.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        mqttManager?.disconnect()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadKeystoreFromAssets(): KeyStore {

        val certFactory = CertificateFactory.getInstance("X.509")

        // ✅ 1. CA 인증서 로드
        val caInput: InputStream = context.resources.openRawResource(R.raw.amazon_root_ca1)
        var rootCaCert = certFactory.generateCertificate(caInput) as X509Certificate
        caInput.close()


        // ✅ 2. 클라이언트 인증서 로드
        val clientCertInput: InputStream =
            context.resources.openRawResource(R.raw.certificate_pem_crt)
        var clientCaCert = certFactory.generateCertificate(clientCertInput) as X509Certificate
        clientCertInput.close()

        // ✅ 3. 클라이언트 개인 키 로드 (PEM -> PrivateKey 변환)
        val keyInput: InputStream = context.resources.openRawResource(R.raw.private_pem_key)
        val keyBytes = keyInput.readBytes()
        keyInput.close()

        // 🔥 여기서 PEM 파일을 올바르게 파싱해야 함 🔥
        val keyString = String(keyBytes)
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("-----BEGIN RSA PRIVATE KEY-----", "")
            .replace("-----END RSA PRIVATE KEY-----", "")
            .replace("\\s".toRegex(), "") // 공백, 개행 문자, 탭 모두 제거

        val privateKeyBytes = Base64.getDecoder().decode(keyString)
        val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey = keyFactory.generatePrivate(keySpec)

        // key store create
        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(null, null)
        keyStore.setCertificateEntry("root-ca", rootCaCert)
        keyStore.setCertificateEntry("client-cert", clientCaCert)

        keyStore.setKeyEntry("client-key", privateKey, null, arrayOf(clientCaCert, rootCaCert))

        return keyStore
    }

    fun publish(topic: String, message: String) {
        mqttManager?.publishString(message, topic, AWSIotMqttQos.QOS0)
    }

    fun subscribe(topic: String) {
        mqttManager?.subscribeToTopic(topic, AWSIotMqttQos.QOS0) { topic, message ->

            println("subscribe!, from $topic")

            val messageString = String(message)

            try {
                val jsonObject = JSONObject(messageString)
                val messageContent = jsonObject.getString("message")
                println("📩 message : $messageContent ")
            } catch (e: Exception) {
                println("err, msg = ${e.message}")
            }
        }
    }

}
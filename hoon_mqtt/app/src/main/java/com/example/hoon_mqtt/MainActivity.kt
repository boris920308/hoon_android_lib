package com.example.hoon_mqtt

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hoon_mqtt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var awsIoTManager: AwsIotManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        awsIoTManager = AwsIotManager(this)

        binding.btnConnect.setOnClickListener {
            Log.d("boris92", "click btnConnect")
            awsIoTManager.connect()
        }

        binding.btnDisconnect.setOnClickListener {
            Log.d("boris92", "click btnDisConnect")
            awsIoTManager.disconnect()
        }

        binding.btnStartSubscribe.setOnClickListener {
            Log.d("boris92", "click btnStartSubscribe")
            awsIoTManager.subscribe("hoon/hello_world")
        }

        binding.btnStartPublish.setOnClickListener {
            Log.d("boris92", "click btnStopSubscribe")
            awsIoTManager.publish("hoon/publish_android", "im android")
        }
    }
}
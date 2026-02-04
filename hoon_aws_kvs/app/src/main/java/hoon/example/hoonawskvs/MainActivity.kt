package hoon.example.hoonawskvs

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import hoon.example.hoonawskvs.presentation.ui.screen.MasterScreen
import hoon.example.hoonawskvs.presentation.ui.screen.ViewerScreen
import hoon.example.hoonawskvs.ui.theme.HoonAwsKvsTheme

enum class Screen {
    Main, Master, Viewer
}

private val REQUIRED_PERMISSIONS = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.RECORD_AUDIO
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoonAwsKvsTheme {
                var currentScreen by remember { mutableStateOf(Screen.Main) }
                var showPermissionDialog by remember { mutableStateOf(false) }
                val context = LocalContext.current

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    val denied = permissions.any { !it.value }
                    if (denied) {
                        showPermissionDialog = true
                    }
                }

                LaunchedEffect(Unit) {
                    val deniedPermissions = REQUIRED_PERMISSIONS.filter {
                        ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
                    }
                    if (deniedPermissions.isNotEmpty()) {
                        permissionLauncher.launch(deniedPermissions.toTypedArray())
                    }
                }

                if (showPermissionDialog) {
                    AlertDialog(
                        onDismissRequest = { showPermissionDialog = false },
                        title = { Text("권한 필요") },
                        text = { Text("카메라와 마이크 권한이 필요합니다. 설정에서 권한을 허용해주세요.") },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    showPermissionDialog = false
                                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                        data = Uri.fromParts("package", context.packageName, null)
                                    }
                                    context.startActivity(intent)
                                }
                            ) {
                                Text("설정으로 이동")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showPermissionDialog = false }) {
                                Text("취소")
                            }
                        }
                    )
                }

                when (currentScreen) {
                    Screen.Main -> {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            MainScreen(
                                onMasterClick = { currentScreen = Screen.Master },
                                onViewerClick = { currentScreen = Screen.Viewer },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                    Screen.Master -> {
                        MasterScreen(
                            onBackClick = { currentScreen = Screen.Main },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Screen.Viewer -> {
                        ViewerScreen(
                            onBackClick = { currentScreen = Screen.Main },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    onMasterClick: () -> Unit,
    onViewerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AWS KVS WebRTC",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onMasterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Master")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onViewerClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Viewer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HoonAwsKvsTheme {
        MainScreen(
            onMasterClick = {},
            onViewerClick = {}
        )
    }
}

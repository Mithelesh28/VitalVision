package com.example.vitalvision

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import com.example.vitalvision.camera.CameraScreen
import com.example.vitalvision.ui.ResultScreen
import com.example.vitalvision.ui.SymptomsScreen
import com.example.vitalvision.ai.SmartEngine

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var currentScreen by remember { mutableStateOf("camera") }
            var capturedImagePath by remember { mutableStateOf("") }
            var finalResult by remember { mutableStateOf("") }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { granted ->
                // Optional: handle denied permission later
            }

            LaunchedEffect(Unit) {
                if (
                    ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    launcher.launch(Manifest.permission.CAMERA)
                }
            }

            when (currentScreen) {

                "camera" -> CameraScreen(
                    onBackClick = {
                        finish()
                    },
                    onImageCaptured = { path ->
                        capturedImagePath = path
                        currentScreen = "symptoms"
                    }
                )

                "symptoms" -> SymptomsScreen(
                    imagePath = capturedImagePath,
                    onAnalyzeClick = { symptoms ->

                        finalResult = SmartEngine.analyze(symptoms)

                        currentScreen = "result"
                    }
                )

                "result" -> ResultScreen(
                    resultText = finalResult,
                    onBackHome = {
                        currentScreen = "camera"
                    }
                )
            }
        }
    }
}
package com.example.vitalvision.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SymptomsScreen(
    imagePath: String,
    onAnalyzeClick: (String) -> Unit = {}
) {
    var symptoms by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Describe Symptoms",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Captured Image:\n$imagePath",
            style = MaterialTheme.typography.bodySmall
        )

        OutlinedTextField(
            value = symptoms,
            onValueChange = { symptoms = it },
            label = { Text("Enter symptoms") },
            placeholder = {
                Text("itching, pain, bleeding, swelling...")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onAnalyzeClick(symptoms)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Analyze")
        }
    }
}
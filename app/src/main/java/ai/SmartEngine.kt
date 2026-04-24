package com.example.vitalvision.ai

object SmartEngine {

    fun analyze(symptoms: String): String {

        val text = symptoms.lowercase()

        return when {

            text.contains("bleeding") &&
                    (text.contains("10") || text.contains("15") || text.contains("heavy")) ->
                """
Urgency: HIGH

Possible Condition:
Ongoing bleeding

Action:
Seek hospital immediately.

First Aid:
Apply firm pressure using clean cloth.
Keep injured area elevated.
                """.trimIndent()

            text.contains("deep cut") || text.contains("severe pain") ->
                """
Urgency: HIGH

Possible Condition:
Serious wound

Action:
Visit hospital immediately.
                """.trimIndent()

            text.contains("rash") && text.contains("fever") ->
                """
Urgency: MEDIUM

Possible Condition:
Infected rash

Action:
Consult doctor soon.
                """.trimIndent()

            text.contains("itching") || text.contains("minor cut") ->
                """
Urgency: LOW

Possible Condition:
Minor issue

Action:
Clean area and monitor symptoms.
                """.trimIndent()

            else ->
                """
Urgency: UNKNOWN

Action:
Monitor condition and seek doctor if worsening.
                """.trimIndent()
        }
    }
}
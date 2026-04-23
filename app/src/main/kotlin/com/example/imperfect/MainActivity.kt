package com.example.imperfect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.imperfect.core.navigation.AppNavHost
import com.example.imperfect.core.ui.theme.ImPerfectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImPerfectTheme {
                AppNavHost()
            }
        }
    }
}


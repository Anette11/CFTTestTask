package com.example.cfttesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cfttesttask.ui.theme.CFTTestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CFTTestTaskTheme {

            }
        }
    }
}
package com.example.cfttesttask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cfttesttask.navigation.CardNavHost
import com.example.cfttesttask.ui.theme.CFTTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            CFTTestTaskTheme {
                CardNavHost()
            }
        }
    }
}
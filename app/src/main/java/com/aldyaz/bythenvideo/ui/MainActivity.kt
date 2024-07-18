package com.aldyaz.bythenvideo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aldyaz.bythenvideo.base.ui.theme.BythenVideoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BythenVideoTheme {
            }
        }
    }
}
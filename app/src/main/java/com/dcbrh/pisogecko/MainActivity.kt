package com.dcbrh.pisogecko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dcbrh.pisogecko.presentation.navigation.PisoGeckoNavigation
import com.dcbrh.pisogecko.ui.theme.PisogeckoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PisogeckoTheme {
                PisoGeckoNavigation()
            }
        }
    }
}

package com.adiluhung.jamuin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.ui.JamuinApp
import com.adiluhung.jamuin.ui.screen.customer.scanResult.ScanResultScreen
import com.adiluhung.jamuin.ui.theme.JamuInTheme

class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}

        val prediction = intent.getStringExtra("prediction")
        setContent {
            JamuInTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (prediction != null) {
                        JamuinApp(argument = prediction)
                    } else {
                        JamuinApp()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JamuinAppPreview() {
    JamuInTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            JamuinApp()
        }
    }
}
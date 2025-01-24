package com.example.newsnow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.newsnow.domain.usecases.AppEntryUseCases
import com.example.newsnow.presentation.onboarding.OnBoardingScreen
import com.example.newsnow.ui.theme.NewsNowTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("PRUN", it.toString())
            }
        }
        setContent {
            NewsNowTheme {
                OnBoardingScreen()
            }
        }
    }
}


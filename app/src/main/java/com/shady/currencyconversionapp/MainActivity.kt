package com.shady.currencyconversionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shady.currencyconversionapp.presentation.model.CurrencyIntent
import com.shady.currencyconversionapp.presentation.ui.screen.CurrencyApp
import com.shady.currencyconversionapp.presentation.viewmodel.CurrencyViewModel
import com.shady.currencyconversionapp.ui.theme.CurrencyConversionAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val currencyViewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sendIntent()
        setContent {
            CurrencyConversionAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyApp(
                        currencyList = currencyViewModel.currencyRates.collectAsState().value,
                        retryOnClick = ::sendIntent,
                        calculateOnClick = ::calculateCurrency
                    )
                }
            }
        }
    }

    private fun sendIntent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                currencyViewModel.intentChannel.send(CurrencyIntent.GetCurrency)
            }
        }
    }

    private fun calculateCurrency(rate: Float, currentValue: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                currencyViewModel.intentChannel.send(CurrencyIntent.Calculate(rate, currentValue))
            }
        }
    }
}

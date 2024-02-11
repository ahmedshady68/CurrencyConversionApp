package com.shady.currencyconversionapp.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.shady.currencyconversionapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarCurrency() {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.currency_screen_title),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold
            )
        }
    )
}
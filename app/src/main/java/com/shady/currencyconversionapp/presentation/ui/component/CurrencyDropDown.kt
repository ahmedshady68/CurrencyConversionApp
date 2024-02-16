package com.shady.currencyconversionapp.presentation.ui.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState
import com.shady.currencyconversionapp.presentation.viewmodel.CurrencyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyDropdownMenu(
    state: CurrencyViewState,
    onValueChange: (Float) -> Unit,
    calculateOnClick: (Float, String) -> Unit,
    currencyTextFieldValue: String,
    viewModel: CurrencyViewModel = hiltViewModel(),
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var currency by remember {
        mutableStateOf(state.currencyAppModel?.rates?.get(0)?.code.toString())
    }
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
        TextField(
            value = currency, onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            }, enabled = false,
            colors = ExposedDropdownMenuDefaults.textFieldColors(), modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            viewModel.currencyRates.collectAsState().value?.currencyAppModel?.rates?.forEach { selectionOption ->
                DropdownMenuItem(text = { Text(text = selectionOption.code) },
                    onClick = {
                        onValueChange(selectionOption.value)
                        calculateOnClick(selectionOption.value,
                            currencyTextFieldValue.ifEmpty { "1" })
                        currency = selectionOption.code
                        isExpanded = false
                    }
                )
            }
        }
    }
}


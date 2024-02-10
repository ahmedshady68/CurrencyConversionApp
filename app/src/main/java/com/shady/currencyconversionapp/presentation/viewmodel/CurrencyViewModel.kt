package com.shady.currencyconversionapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shady.currencyconversionapp.presentation.mapper.CurrencyAppMapper
import com.shady.currencyconversionapp.presentation.model.CurrencyIntent
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState
import com.shady.domain.usecase.CurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrencyUseCase: CurrencyUseCase,
    private val mapper: CurrencyAppMapper,
) : ViewModel() {
    val intentChannel = Channel<CurrencyIntent>(Channel.UNLIMITED)

    private val _currency: MutableStateFlow<CurrencyViewState?> =
        MutableStateFlow(CurrencyViewState())
    val currency = _currency.asStateFlow()

    init {
        processCurrency()
    }

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _currency.emit(
                    currency.value?.copy(
                        currencyAppModel = null,
                        isLoading = false,
                        error = throwable
                    )
                )
            }
        }

    private fun processCurrency() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is CurrencyIntent.GetCurrency -> reduceCurrency()
                }
            }
        }
    }

    private fun reduceCurrency() {
        viewModelScope.launch(exceptionHandler) {
            _currency.emit(
                currency.value?.copy(isLoading = true, error = null)
            )
            _currency.emit(
                try {
                    currency.value?.copy(
                        currencyAppModel = mapper.apply(getCurrencyUseCase()),
                        isLoading = false,
                        error = null
                    )
                } catch (e: Exception) {
                    currency.value?.copy(currencyAppModel = null, isLoading = false, error = e)
                }
            )
        }
    }
}

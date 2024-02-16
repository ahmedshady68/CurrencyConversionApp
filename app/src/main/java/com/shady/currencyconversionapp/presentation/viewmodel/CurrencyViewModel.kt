package com.shady.currencyconversionapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shady.currencyconversionapp.presentation.mapper.CurrencyAppMapper
import com.shady.currencyconversionapp.presentation.model.CurrencyIntent
import com.shady.currencyconversionapp.presentation.model.CurrencyViewState
import com.shady.domain.usecase.CurrencyUseCase
import com.shady.domain.usecase.UpdatedListUseCase
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
    private val getUpdatedCurrencyUseCase: UpdatedListUseCase,
    private val mapper: CurrencyAppMapper,
) : ViewModel() {
    val intentChannel = Channel<CurrencyIntent>(Channel.UNLIMITED)

    private val _currencyRates: MutableStateFlow<CurrencyViewState?> =
        MutableStateFlow(CurrencyViewState())
    val currencyRates = _currencyRates.asStateFlow()

    init {
        processCurrency()
    }

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _currencyRates.emit(
                    currencyRates.value?.copy(
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
                    is CurrencyIntent.Calculate -> reduceNewList(it.rate, it.currentValue)
                }
            }
        }
    }

    private fun reduceNewList(rate: Float, currentValue: String) {
        viewModelScope.launch(exceptionHandler) {
            _currencyRates.emit(
                currencyRates.value?.copy(isLoading = true, error = null)
            )
            _currencyRates.emit(
                try {
                    /*currency.value?.copy(
                        currencyAppModel = mapper.apply(getCurrencyUseCase()),
                        isLoading = true,
                        error = null
                    )*/
                    currencyRates.value?.copy(
                        currencyAppModel = mapper.apply(
                            getUpdatedCurrencyUseCase(
                                rate,
                                currentValue
                            )
                        ),
                        isLoading = false,
                        error = null
                    )
                } catch (e: Exception) {
                    currencyRates.value?.copy(currencyAppModel = null, isLoading = false, error = e)
                }
            )
        }
    }

    private fun reduceCurrency() {
        viewModelScope.launch(exceptionHandler) {
            _currencyRates.emit(
                currencyRates.value?.copy(isLoading = true, error = null)
            )
            _currencyRates.emit(
                try {
                    currencyRates.value?.copy(
                        currencyAppModel = mapper.apply(getCurrencyUseCase()),
                        isLoading = false,
                        error = null
                    )
                } catch (e: Exception) {
                    currencyRates.value?.copy(currencyAppModel = null, isLoading = false, error = e)
                }
            )
        }
    }
}

package aleshka.developement.exchanger.feature_exchange.domain.view_models

import aleshka.developement.exchanger.feature_exchange.domain.events.ExchangeEvent
import aleshka.developement.exchanger.feature_exchange.domain.repositories.ExchangeRepository
import aleshka.developement.exchanger.feature_exchange.domain.states.ExchangeState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeViewModel : ViewModel() {

    companion object {
        private const val TAG: String = "ViewModelExchange"
    }

    private val _state = MutableStateFlow(ExchangeState())
    val state = _state.asStateFlow()

    private val repository = ExchangeRepository()

    init {
        viewModelScope.launch {
            repository.getCurrencies()
        }
    }

    fun onEvent(
        event: ExchangeEvent
    ) {
        when (event) {
            is ExchangeEvent.OnChangedAmount -> {
                _state.update {
                    it.copy(
                        amount = event.amount
                    )
                }
            }
            is ExchangeEvent.OnChangedFromCurrency -> {
                _state.update {
                    it.copy(
                        fromCurrency = event.fromCurrency
                    )
                }
            }
            is ExchangeEvent.OnChangedToCurrency -> {
                _state.update {
                    it.copy(
                        toCurrency = event.toCurrency
                    )
                }
            }
            is ExchangeEvent.OnExchangeCurrencies -> {
                viewModelScope.launch {
                    val result = repository.exchangeCurrencies(
                        fromCurrency = state.value.fromCurrency,
                        toCurrency = state.value.toCurrency,
                        amount = state.value.amount,
                    )

                    Log.i(TAG, "result - ${result.body()}")

                    if (result.body()?.amount != null) {
                        _state.update {
                            it.copy(
                                result = result.body()!!.amount
                            )
                        }
                    }
                }
            }
        }
    }
}

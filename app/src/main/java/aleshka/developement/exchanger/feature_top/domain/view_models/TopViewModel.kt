package aleshka.developement.exchanger.feature_top.domain.view_models

import aleshka.developement.exchanger.feature_top.domain.repositories.LatestRepository
import aleshka.developement.exchanger.feature_top.domain.states.TopState
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopViewModel : ViewModel() {

    companion object {
        private const val TAG: String = "ViewModelTop"
    }

    private val repository = LatestRepository()

    private val _state = MutableStateFlow(TopState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getLatest()

            Log.i(TAG, "result - ${result.body()}")
        }
    }

    private suspend fun getLatest() =
        repository.latestCurrencies(
            base = state.value.base
        )

}

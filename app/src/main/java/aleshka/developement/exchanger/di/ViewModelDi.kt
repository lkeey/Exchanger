package aleshka.developement.exchanger.di

import aleshka.developement.exchanger.feature_exchange.domain.view_models.ExchangeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    /*
        Create viewModels here
    */

    /* ProfileScreen */
    viewModel {
        ExchangeViewModel()
    }

}

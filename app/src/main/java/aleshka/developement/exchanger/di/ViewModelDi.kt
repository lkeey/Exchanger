package aleshka.developement.exchanger.di

import aleshka.developement.exchanger.feature_exchange.domain.view_models.ExchangeViewModel
import aleshka.developement.exchanger.feature_top.domain.view_models.TopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    /*
        Create viewModels here
    */

    /* Exchange Screen */
    viewModel {
        ExchangeViewModel()
    }

    /* Top Currencies Screen */
    viewModel {
        TopViewModel()
    }
}

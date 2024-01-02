package aleshka.developement.exchanger.ui.presentation.navigation

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_exchange.presentation.screen.destinations.DirectionDestination
import aleshka.developement.exchanger.feature_exchange.presentation.screen.destinations.ExchangeScreen2Destination
import aleshka.developement.exchanger.feature_exchange.presentation.screen.destinations.ExchangeScreen3Destination
import aleshka.developement.exchanger.feature_exchange.presentation.screen.destinations.ExchangeScreenDestination
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

sealed class BottomNavigationDestinations (
    val direction: DirectionDestination,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val color: Color,
) {

    data object Exchange : BottomNavigationDestinations(
        ExchangeScreenDestination, R.drawable.exchange, R.string.exchange, Color(0xFFFFA574)
    )

    data object Exchange2 : BottomNavigationDestinations(
        ExchangeScreen2Destination, R.drawable.diagrams, R.string.diagrams, Color(0xFFFA6FFF)    )

    data object Exchange3 : BottomNavigationDestinations(
        ExchangeScreen3Destination, R.drawable.star, R.string.star, Color(0xFFADFF64)    )

}

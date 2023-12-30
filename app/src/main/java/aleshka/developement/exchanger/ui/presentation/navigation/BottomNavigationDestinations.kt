package aleshka.developement.exchanger.ui.presentation.navigation

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_exchange.presentation.screen.destinations.DirectionDestination
import aleshka.developement.exchanger.feature_exchange.presentation.screen.destinations.ExchangeScreenDestination
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

enum class BottomNavigationDestinations (
    val direction: DirectionDestination,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val color: Color,
) {
    Exchange(ExchangeScreenDestination, R.drawable.ic_launcher_background, R.string.app_name, Color(0xFFFFA574)),
    Exchange2(ExchangeScreenDestination, R.drawable.ic_launcher_background, R.string.app_name, Color(0xFFFA6FFF)),
    Exchange3(ExchangeScreenDestination, R.drawable.ic_launcher_background, R.string.app_name, Color(0xFFADFF64)),
}

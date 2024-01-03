package aleshka.developement.exchanger.ui.theme

import aleshka.developement.exchanger.ui.presentation.navigation.BottomNavigationBar
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.chrisbanes.haze.HazeState

@Composable
fun ExchangerTheme(
    navController: NavController,
    hazeState: HazeState,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (PaddingValues) -> Unit
) {

    val colors = if (useDarkTheme) DarkColors else LightColors

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {

        systemUiController.setNavigationBarColor(
            color = colors.background,
            darkIcons = useDarkIcons
        )
        systemUiController.setStatusBarColor(
            colors.background,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        hazeState = hazeState
                    )
                },
                content = content
            )
        }
    )
}

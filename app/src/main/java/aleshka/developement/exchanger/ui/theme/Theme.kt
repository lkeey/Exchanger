package aleshka.developement.exchanger.ui.theme

import aleshka.developement.exchanger.ui.presentation.navigation.BottomNavigationBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.navigation.NavController

@Composable
fun ExchangerTheme(
    navController: NavController,
    statusBarColor: Color = Transparent,
    navigationBarColor: Color = Transparent,
    content: @Composable (PaddingValues) -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                },
                content = content,
                contentColor = Black
            )
        }
    )
}
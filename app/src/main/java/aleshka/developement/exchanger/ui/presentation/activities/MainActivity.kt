package aleshka.developement.exchanger.ui.presentation.activities

import aleshka.developement.exchanger.feature_exchange.presentation.screen.NavGraphs
import aleshka.developement.exchanger.ui.theme.ExchangerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import dev.chrisbanes.haze.HazeState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            val navController = rememberNavController()

            val hazeState = remember {
                HazeState()
            }

            ExchangerTheme(
                navController = navController,
                hazeState = hazeState
            ) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController
                )
            }
        }
    }
}

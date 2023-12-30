package aleshka.developement.exchanger.ui.presentation.activities

import aleshka.developement.exchanger.feature_exchange.presentation.screen.NavGraphs
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}

package aleshka.developement.exchanger.feature_exchange.presentation.screen

import aleshka.developement.exchanger.ui.theme.ExchangerTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import java.util.Random

@Composable
@Destination (start = true)
fun ExchangeScreen (
    navController: NavController
) {
    ExchangerTheme (
        navController = navController,
    ) {
        Text(text = "Exchanger")

        LazyColumn(Modifier.fillMaxSize()) {
            repeat(300) {
                val rnd = Random()
                val color = Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                item {
                    Box(modifier = Modifier
                        .size(500.dp)
                        .padding(16.dp)
                        .background(color)
                    )
                }
            }
        }

    }
}

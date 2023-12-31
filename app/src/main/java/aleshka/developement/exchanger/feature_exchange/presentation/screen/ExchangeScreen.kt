package aleshka.developement.exchanger.feature_exchange.presentation.screen

import aleshka.developement.exchanger.ui.theme.Black
import aleshka.developement.exchanger.ui.theme.ExchangerTheme
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import java.util.Random

@Composable
@Destination (start = true)
fun ExchangeScreen (
    navController: NavController
) {

    val hazeState = remember {
        HazeState()
    }

    ExchangerTheme (
        navController = navController,
        hazeState = hazeState
    ) {
        Text(text = "Exchanger 1")

        LazyColumn(
            modifier = Modifier
                .haze(
                    hazeState,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    tint = Black.copy(alpha = .2f),
                    blurRadius = 30.dp,
                )
                .fillMaxSize()

        ) {
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

@Composable
@Destination
fun ExchangeScreen2 (
    navController: NavController
) {

    val hazeState = remember {
        HazeState()
    }

    ExchangerTheme (
        navController = navController,
        hazeState = hazeState

    ) {
        Text(text = "Exchanger 2")

        LazyColumn(Modifier.fillMaxSize().haze(
            hazeState,
            backgroundColor = MaterialTheme.colorScheme.background,
            tint = Black.copy(alpha = .2f),
            blurRadius = 30.dp,
        )) {
            items(50) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.DarkGray, RoundedCornerShape(12.dp))
                        .border(
                            width = Dp.Hairline,
                            color = Color.White.copy(alpha = .5f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    AsyncImage(
                        model = "https://source.unsplash.com/random?neon,$it",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }

    }
}

@Composable
@Destination
fun ExchangeScreen3 (
    navController: NavController
) {

    val hazeState = remember {
        HazeState()
    }

    ExchangerTheme (
        navController = navController,
        hazeState = hazeState
    ) {
        Text(text = "Exchanger 3")

        LazyColumn(Modifier.fillMaxSize().haze(
            hazeState,
            backgroundColor = MaterialTheme.colorScheme.background,
            tint = Black.copy(alpha = .2f),
            blurRadius = 30.dp,
        )) {
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
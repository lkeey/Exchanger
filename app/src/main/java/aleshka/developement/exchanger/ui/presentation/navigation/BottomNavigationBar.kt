package aleshka.developement.exchanger.ui.presentation.navigation

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_exchange.presentation.screen.NavGraphs
import aleshka.developement.exchanger.ui.theme.Black
import aleshka.developement.exchanger.ui.theme.White
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.isRouteOnBackStack

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalAlignment = Alignment.Bottom
        ) {

            BottomNavigationDestinations.values().forEach { tab->
                val isOpened = navController.isRouteOnBackStack(tab.direction)

                val alpha by animateFloatAsState(
                    targetValue = if (isOpened) 1f else .35f,
                    label = "alpha"
                )

                val scale by animateFloatAsState(
                    targetValue = if (isOpened) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                if (isOpened) {
                                    // When we click again on a bottom bar item and it was already selected
                                    // we want to pop the back stack until the initial destination of this bottom bar item
                                    navController.popBackStack(tab.direction, false)
                                    return@detectTapGestures
                                }

                                navController.navigate(tab.direction.route) {

                                    // Pop up to the root of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(NavGraphs.root) {
                                        saveState = true
                                    }

                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true

                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(painter = painterResource(R.drawable.logo), contentDescription = "tab ${tab.label}")
                    Text(
                        text = stringResource(tab.label),
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight(500),
                            color = Black,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp,
                        )
                    )
                }
            }
        }
    }
}

package aleshka.developement.exchanger.ui.presentation.navigation

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_exchange.presentation.screen.NavGraphs
import aleshka.developement.exchanger.ui.theme.Black
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun BottomNavigationBar(
    navController: NavController,
    hazeState: HazeState
) {

    val tabs = listOf(
        BottomNavigationDestinations.Exchange,
        BottomNavigationDestinations.Exchange2,
        BottomNavigationDestinations.Exchange3,
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(
           getCurrentDestination(tabs, navController)
        )
    }

    val animatedSelectedTabIndex by animateFloatAsState(
        targetValue = selectedTabIndex.toFloat(), label = "animatedSelectedTabIndex",
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioLowBouncy,
        )
    )
    val animatedColor by animateColorAsState(
        targetValue = tabs[selectedTabIndex].color,
        label = "animatedColor",
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
        )
    )

    Log.i("AWDAWD", navController.currentDestination.toString())

    Box(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 64.dp)
            .fillMaxWidth()
            .height(64.dp)
            .hazeChild(state = hazeState, shape = CircleShape)
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = .8f),
                        Color.White.copy(alpha = .2f),
                    ),
                ),
                shape = CircleShape
            )
    ) {
        BottomBarTabs(
            navController,
            tabs,
            selectedTab = selectedTabIndex,
            onTabSelected = { tab, isOpened ->
                selectedTabIndex = tabs.indexOf(tab)

                if (isOpened) {
                    // When we click again on a bottom bar item and it was already selected
                    // we want to pop the back stack until the initial destination of this bottom bar item
                    navController.popBackStack(tab.direction, false)
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
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        ) {
            val tabWidth = size.width / tabs.size
            drawCircle(
                color = animatedColor.copy(alpha = .6f),
                radius = size.height / 2,
                center = Offset(
                    (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                    size.height / 2
                )
            )
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            val path = Path().apply {
                addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
            }
            val length = PathMeasure().apply { setPath(path, false) }.length
            val tabWidth = size.width / tabs.size
            drawPath(
                path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        animatedColor.copy(alpha = 0f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 1f),
                        animatedColor.copy(alpha = 0f),
                    ),
                    startX = tabWidth * animatedSelectedTabIndex,
                    endX = tabWidth * (animatedSelectedTabIndex + 1),
                ),
                style = Stroke(
                    width = 6f,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(length / 2, length)
                    )
                )
            )
        }
    }
}

@Composable
fun BottomBarTabs(
    navController: NavController,
    tabs: List<BottomNavigationDestinations>,
    selectedTab: Int,
    onTabSelected: (BottomNavigationDestinations, Boolean) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            for (tab in tabs) {
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
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
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {

                                onTabSelected(tab, navController.isRouteOnBackStack(tab.direction))
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

fun getCurrentDestination(
    tabs: List<BottomNavigationDestinations>,
    navController: NavController
): Int {

    for (tab in tabs) {
        if (navController.isRouteOnBackStack(tab.direction)) return tabs.indexOf(tab)
    }

    return 0
}
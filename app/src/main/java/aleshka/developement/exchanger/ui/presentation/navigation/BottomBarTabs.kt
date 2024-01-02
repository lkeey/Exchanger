package aleshka.developement.exchanger.ui.presentation.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.utils.isRouteOnBackStack

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
            modifier = Modifier
                .fillMaxSize(),
        ) {
            for (tab in tabs) {

                val isSelected = selectedTab == tabs.indexOf(tab)

                val alpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1f else .98f,
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
                        .fillMaxSize()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {

                                onTabSelected(tab, navController.isRouteOnBackStack(tab.direction))
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(tab.icon),
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "tab ${tab.label}"
                        )

                        Text(
                            text = stringResource(tab.label),
                            style = TextStyle(
                                fontSize = if (isSelected) 12.sp else 10.sp,
                                fontWeight = FontWeight(500),
                                color = if (isSelected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.2.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}

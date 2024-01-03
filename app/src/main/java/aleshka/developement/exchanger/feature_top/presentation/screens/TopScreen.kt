package aleshka.developement.exchanger.feature_top.presentation.screens

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_top.domain.view_models.TopViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun TopScreen() {

    val viewModel = koinViewModel<TopViewModel>()
    val state = viewModel.state.collectAsState().value

    Text(
        text = state.base,
        style = TextStyle(
            fontSize = 40.sp,
            lineHeight = 40.sp,
            fontFamily = FontFamily(Font(R.font.pacifico)),
            fontWeight = FontWeight(600),
            color = MaterialTheme.colorScheme.onBackground,
        )
    )

}

package aleshka.developement.exchanger.feature_exchange.presentation.components

import aleshka.developement.exchanger.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedText (
    label: String,
    isNumber: Boolean,
    onTextChanged: (Float) -> Unit,
    onCompleted: () -> Unit,
) {

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pacifico)),
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.tertiary,
                    letterSpacing = 0.3.sp,
                )
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {onCompleted()}
        ),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            if (it.toFloatOrNull() != null) {
                textValue.value = it
                onTextChanged(it.toFloat())
            }
        },
        shape = RoundedCornerShape(16.dp),
    )
}
package aleshka.developement.exchanger.feature_exchange.presentation.components

import aleshka.developement.exchanger.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown (
    options: List<String>,
    previousData: String,
    label: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var textValue by remember {
        mutableStateOf(previousData)
    }

    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {

        // The `menuAnchor` modifier must be passed to the text field for correctness.

        OutlinedTextField(
            modifier = modifier
                .menuAnchor(),
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
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                Icon (
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .rotate(
                            if (expanded) 180f else 0f
                        ),
                    painter = painterResource(
                        id = R.drawable.ic_profile_arrow_drop_down
                    ),
                    contentDescription = "open icon"
                )
            },
            singleLine = true,
            maxLines = 1,
            value = textValue,
            onValueChange = {
                // user can't write anything
            },
            shape = RoundedCornerShape(16.dp),
            readOnly = true,
        )

        ExposedDropdownMenu(
            modifier = Modifier
                .background(
                    color = Color.White,
                )
                .heightIn(
                    max = 150.dp
                ),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },

        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = if (selectionOption == textValue) {
                        Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer.copy(.6f),
                                shape = RoundedCornerShape(16.dp)
                            )
                    } else Modifier,
                    text = {
                        Text(
                            text = selectionOption,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pacifico)),
                                fontWeight = FontWeight(300),
                                color = MaterialTheme.colorScheme.tertiary,
                                letterSpacing = 0.3.sp,
                            )
                        )
                    },
                    onClick = {
                        textValue = selectionOption
                        expanded = false
                        onTextChanged(selectionOption)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

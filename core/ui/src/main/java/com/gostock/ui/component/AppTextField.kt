package com.gostock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gostock.ui.theme.BorderColor

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onValueChanged: (String) -> Unit,
) {
    val valueChange by rememberUpdatedState(onValueChanged)

    Column(
        modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .background(Color.White, CircleShape)
                .border(width = 1.dp, color = BorderColor, shape = CircleShape),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = valueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, CircleShape),
                placeholder = {
                    Text(text = placeholder)
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun AppTextFieldPrev() {
    AppTextField(
        label = "Title",
        value = "",
        onValueChanged = {},
        placeholder = "placeholder text"
    )
}
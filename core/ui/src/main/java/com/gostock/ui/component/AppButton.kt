package com.gostock.ui.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gostock.ui.theme.ButtonTextColor
import com.gostock.ui.theme.ButtonTextSecondaryColor
import com.gostock.ui.theme.LightPrimaryColor
import com.gostock.ui.theme.LightSecondaryColor

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    containerColor: Color = LightPrimaryColor,
    contentColor: Color = ButtonTextColor,
    isFullButton: Boolean = true,
    onClick: () -> Unit,
) {
    val modifierButton = if (isFullButton) {
        modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .defaultMinSize(minWidth = 200.dp, minHeight = 50.dp)
    } else {
        modifier
            .wrapContentWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .defaultMinSize(minWidth = 200.dp, minHeight = 50.dp)
    }

    Button(
        modifier = modifierButton,
        onClick = {
            if (!isLoading) onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = modifier
                    .width(20.dp)
                    .height(20.dp),
                trackColor = contentColor,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                color = contentColor
            )
        }
    }
}

@Composable
fun AppSecondaryButton(
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    AppButton(
        text = text,
        isLoading = isLoading,
        containerColor = LightSecondaryColor,
        contentColor = ButtonTextSecondaryColor,
        onClick = onClick
    )
}

@Preview
@Composable
fun FullButtonPreview() {
    AppButton(text = "Submit", onClick = {})
}
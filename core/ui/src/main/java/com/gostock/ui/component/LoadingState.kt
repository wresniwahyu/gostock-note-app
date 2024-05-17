package com.gostock.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gostock.ui.theme.LightPrimaryColor

@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    if (isLoading) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = LightPrimaryColor
            )
        }
    }
}

@Preview
@Composable
fun LoadingStatePrev() {
    LoadingState(isLoading = true)
}
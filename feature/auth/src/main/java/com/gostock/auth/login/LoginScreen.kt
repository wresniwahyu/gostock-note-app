package com.gostock.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gostock.auth.R
import com.gostock.ui.component.AppButton
import com.gostock.ui.component.AppTextField
import com.gostock.ui.theme.LightGrey
import com.gostock.util.constant.Screens
import com.gostock.util.extension.showToast

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        viewModel.event.collect { event ->
            when (event) {
                is LoginViewModel.Event.LoginSuccess -> {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(0)
                    }
                }

                is LoginViewModel.Event.ShowMessage -> {
                    context.showToast(event.message)
                }
            }
        }
    })

    LoginContent(
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "MY NOTE",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(0.5f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White, RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            AppTextField(
                modifier = Modifier.padding(horizontal = 12.dp),
                label = stringResource(R.string.label_email),
                value = email,
                placeholder = stringResource(R.string.placeholder_email),
                onValueChanged = { email = it }
            )
            AppTextField(
                modifier = Modifier.padding(horizontal = 12.dp),
                label = stringResource(R.string.label_password),
                value = password,
                placeholder = stringResource(R.string.placeholder_password),
                onValueChanged = { password = it }
            )

            AppButton(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = stringResource(id = R.string.login),
                isLoading = state.isLoading
            ) {
                viewModel.login(username = email, password = password)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        val annotatedString = buildAnnotatedString {
            append("Don't have an account? ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Register here")
            }
        }
        Text(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .clickable {
                    navController.navigate(Screens.Register.route)
                },
            text = annotatedString
        )

    }
}
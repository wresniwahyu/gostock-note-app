package com.gostock.featurenote.home

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gostock.local.UserPref
import com.gostock.ui.component.LoadingState
import com.gostock.ui.theme.LightGrey
import com.gostock.ui.theme.LightPrimaryColor
import com.gostock.util.constant.Screens
import com.gostock.util.extension.showToast
import com.gostock.util.extension.toSimpleDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    userPref: UserPref
) {

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.getNotes()
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when(event) {
                is HomeViewModel.Event.Logout -> {
                    navController.navigate(Screens.Login.route) {
                        popUpTo(0)
                    }
                }
                is HomeViewModel.Event.ShowMessage -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    HomeScreenContent(
        navController = navController,
        viewModel = viewModel,
        userPref = userPref
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel,
    userPref: UserPref
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    var startDate by remember { mutableStateOf<Long?>(null) }
    var endDate by remember { mutableStateOf<Long?>(null) }

    val openDatePicker = { onDateSelected: (Long) -> Unit ->
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(calendar.timeInMillis)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddNote.route)
                },
                containerColor = LightPrimaryColor,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LightGrey)
        ) {
            HomeHeaderComponent(
                username = userPref.username,
                onLogoutClicked = {
                    viewModel.logout()
                }
            )
            TitleSection("") {
                openDatePicker { startDate = it }
            }

            if (state.notes.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    itemsIndexed(state.notes) { index, item ->
                        NoteItem(
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    Screens.NoteDetail.createRoute(
                                        id = item.id,
                                        title = item.title,
                                        note = item.note,
                                        date = item.createdAt
                                    )
                                )
                            },
                            title = item.title,
                            note = item.note,
                            date = item.createdAt.toSimpleDate() ?: item.createdAt
                        )
                    }
                }
            } else {
                LoadingState(isLoading = state.isLoading)
            }
        }
    }
}

@Composable
fun HomeHeaderComponent(
    username: String,
    onLogoutClicked: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Text(
                text = "MY NOTE",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable {
                    onLogoutClicked.invoke()
                },
                imageVector = Icons.Rounded.ExitToApp,
                contentDescription = null,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = username)
        }
    }
}

@Composable
fun TitleSection(
    dateRange: String,
    onDateRangeClicked: () -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Note List")
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable {
                    onDateRangeClicked.invoke()
                }
        ) {
            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
            Text(text = dateRange)
        }
    }
}
package com.gostock.featurenote.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gostock.ui.theme.LightGrey
import com.gostock.ui.theme.LightPrimaryColor

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Do something */ },
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
            HomeHeaderComponent("Wresni Wahyu")
            TitleSection()

            val items = listOf("test", "test 2", "test 3")
            LazyColumn(
                modifier = modifier.weight(1f),
            ) {
                itemsIndexed(items) { index, item ->
                    Text(text = item)
                }
            }
        }
    }
}

@Composable
fun HomeHeaderComponent(
    username: String
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
fun TitleSection() {
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
        ) {
            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
            Text(text = "dd/MM/yyyy - dd/MM-yyyy")
        }
    }
}

@Preview
@Composable
fun HomePrev() {
    HomeScreenContent()
}
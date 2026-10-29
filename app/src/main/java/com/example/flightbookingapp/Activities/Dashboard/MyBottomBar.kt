package com.example.flightbookingapp.Activities.Dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightbookingapp.R

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {

    return listOf(
        BottomMenuItem(label = "Home", icon = painterResource(R.drawable.bottom_btn1)),
        BottomMenuItem(label = "Cart", icon = painterResource(R.drawable.bottom_btn2)),
        BottomMenuItem(label = "Favorite", icon = painterResource(R.drawable.bottom_btn3)),
        BottomMenuItem(label = "Order", icon = painterResource(R.drawable.bottom_btn4))
    )
}

@Composable
fun MyBottomBar() {

    val bottomMenuItemList = prepareBottomMenu()
    val context = LocalContext.current
    val selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        containerColor = colorResource(R.color.darkPurple),
        tonalElevation = 3.dp
    ) {
        bottomMenuItemList.forEach {
            BottomNavigationItem(
                selected = (selectedItem == it.label),
                onClick = {
                    selectedItem == it.label
                    if (it.label == "Cart") {

                    } else {
                        Toast.makeText(context, it.label, Toast.LENGTH_SHORT).show()
                    }
                },
                icon = {
                    Icon(
                        painter = it.icon,
                        contentDescription = null,
                        tint = colorResource(R.color.orange),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp)
                    )
                }
            )
        }
    }
}
package com.example.flightbookingapp.Activities.Dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.flightbookingapp.Activities.SearchResult.SearchResultActivity
import com.example.flightbookingapp.Activities.Splash.GradiantButton
import com.example.flightbookingapp.Domain.LocationModel
import com.example.flightbookingapp.Activities.Splash.StatusTopBarColor
import com.example.flightbookingapp.ViewModel.MainViewModel
import com.example.flightbookingapp.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
@Preview
fun MainScreen() {

    val context = LocalContext.current

    val locations = remember { mutableStateListOf<LocationModel>() }
    val viewModel = MainViewModel()
    var showLocationLoading by remember { mutableStateOf(true) }
    var from: String = ""
    var to: String = ""
    var classes: String = ""
    var adultPassenger: String = ""
    var childPassenger: String = ""

    StatusTopBarColor()

    LaunchedEffect(Unit) {

        viewModel.loadLocations().observeForever { result ->

            locations.clear()
            locations.addAll(result)
            showLocationLoading = false
        }
    }

    Scaffold(
        bottomBar = { MyBottomBar() }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
                .padding(paddingValues = it)
        ) {
            item { TopBar() }
            item {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .background(
                            colorResource(R.color.darkPurple), shape = RoundedCornerShape(29.dp)
                        )
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {

//                    from Section
                    YellowTitle("From")
                    val locationNames: List<String> = locations.map{it.name}

                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select Origin",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->

                        from = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))

//                    to Section
                    YellowTitle("To")

                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.to_ic),
                        hint = "Select Destination",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->

                        if (selectedItem != from) {
                            to = selectedItem
                        }
                        else {
                            Toast.makeText(context, "Departure and destination locations can’t be the same.", Toast.LENGTH_LONG).show()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

//                    passenger counter
                    YellowTitle("Passengers")
                    Row(modifier = Modifier.fillMaxWidth()) {

                        PassengerCounter(
                            title = "Adult",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {adultPassenger = it}
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        PassengerCounter(
                            title = "Child",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {childPassenger = it}
                        )

                    }

//                    calender picker
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {

                        YellowTitle("Departure Date", Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(66.dp))
                        YellowTitle("Return Date", Modifier.weight(1f))
                    }

                    DatePickerScreen(Modifier.weight(1f))

                    Spacer(modifier = Modifier.height(16.dp))

//                    clases Section
                    YellowTitle("Class")
                    val classItem = listOf("Business Class", "First Class", "Economy Class")

                    DropDownList(
                        items = classItem,
                        loadingIcon = painterResource(R.drawable.seat_black_ic),
                        hint = "Select Class",
                        showLocationLoading = showLocationLoading
                    ) { selectedItem ->

                        classes = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))

//                    search button
                    GradiantButton(
                        onClick = {

                            val intent = Intent(context, SearchResultActivity::class.java).apply {
                                putExtra("from", from)
                                putExtra("to", to)
                                putExtra("numPassenger", adultPassenger + childPassenger)
                            }
                            startActivity(context, intent, null)
                        },
                        text = "Search"
                    )

                }
            }
        }
    }
}

@Composable
fun YellowTitle(text: String, modifier: Modifier = Modifier) {

    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.orange)
    )
}
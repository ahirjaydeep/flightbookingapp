package com.example.flightbookingapp.Domain

import com.google.firebase.database.PropertyName
import java.io.Serializable

data class FlightModel(
    var AirlineLogo: String = "",
    var AirlineName: String = "",
    @PropertyName("arriveTime")
    @JvmField
    var ArrivelTime: String = "",
    var ClassSeat: String = "",
    var Date: String = "",
    var From: String = "",
    var FromShort: String = "",
    var NumberSeat: Int = 0,
    var Price: Double = 0.0,
    var Passenger: String = "",
    var Seats: String = "",
    var ReservedSeats: String = "",
    var Time: String = "",
    var To: String = "",
    var ToShort: String = ""
): Serializable

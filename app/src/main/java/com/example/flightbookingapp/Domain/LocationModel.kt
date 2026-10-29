package com.example.flightbookingapp.Domain

import com.google.firebase.database.PropertyName

data class LocationModel(
    @get:PropertyName("Id") @set:PropertyName("Id")
    var id: Int = 0,
    @get:PropertyName("Name") @set:PropertyName("Name")
    var name: String = ""
)
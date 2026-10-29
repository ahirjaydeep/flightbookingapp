package com.example.flightbookingapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.flightbookingapp.Domain.FlightModel
import com.example.flightbookingapp.Domain.LocationModel
import com.example.flightbookingapp.Repository.MainRepository

class MainViewModel {

    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>> {
        return repository.loadLocation()
    }

    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)
    }
}
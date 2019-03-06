package com.example.s16005532.boat_tracker.Model

class Port{

    private var id: Int
    private var name: String
    private var latitude: Float
    private var longitude: Float

    constructor(id: Int, name: String, latitude: Float, longitude: Float) {
        this.id = id
        this.name = name
        this.latitude = latitude
        this.longitude = longitude
    }
}
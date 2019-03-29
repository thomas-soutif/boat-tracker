package com.example.s16005532.boat_tracker.Model

import java.io.Serializable

class Port : Serializable{

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

    public fun getLatitude() : Float {

        return this.latitude
    }
    public fun getLongitude() : Float {

        return this.longitude
    }
    override fun toString(): String {

        return "id ="+ this.id + " name=" + this.name + " latitude=" + this.latitude + " longitude=" + this.longitude

    }

    public fun getId(): Int{

        return this.id
    }

}
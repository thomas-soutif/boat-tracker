package com.example.s16005532.boat_tracker.Model

import java.net.PortUnreachableException

class Containership{

    private var id: Int = 0
    private var name: String
    private var captainName: String
    private var latitude: Float
    private var longitude: Float
    private var port: Port?
    private var type: ContainershipType?
    private var containers: Array<Container>?

    constructor(
        id: Int,
        name: String,
        captainName: String,
        latitude: Float,
        longitude: Float,
        port: Port?,
        type: ContainershipType?,
        containers: Array<Container>?
    ) {
        this.id = id
        this.name = name
        this.captainName = captainName
        this.latitude = latitude
        this.longitude = longitude
        this.port = port
        this.type = type
        this.containers = containers
    }

    override fun toString(): String {
        return "Containership(name='$name', captainName='$captainName')"
    }

    public fun getName(): String{

        return this.name
    }

    public fun getCaptainName(): String{

        return this.captainName
    }
}
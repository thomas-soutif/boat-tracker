package com.example.s16005532.boat_tracker.Model

import java.io.Serializable

class Containership : Serializable
{

    private var id: Int = 0
    private var name: String
    private var captainName: String
    private var latitude: Float
    private var longitude: Float
    private var port: Port?
    private var type: ContainershipType?
    private var containers: Array<Container>?
    private var documentRef: String

    constructor(
        id: Int,
        name: String,
        captainName: String,
        latitude: Float,
        longitude: Float,
        port: Port?,
        type: ContainershipType?,
        containers: Array<Container>?,
        documentRef: String
    ) {
        this.id = id
        this.name = name
        this.captainName = captainName
        this.latitude = latitude
        this.longitude = longitude
        this.port = port
        this.type = type
        this.containers = containers
        this.documentRef = documentRef
    }

    override fun toString(): String {
        var newLine : String? = System.getProperty("line.separator")
        return "Boat number ° $id"+ newLine +"Name=$name " + newLine+ "CaptainName=$captainName"
    }

    public fun getName(): String{

        return this.name
    }

    public fun getCaptainName(): String{

        return this.captainName
    }

    public fun getType(): ContainershipType? {

        return this.type
    }

    public fun getLatitude() : Float {

        return this.latitude
    }
    public fun getLongitude() : Float {

        return this.longitude
    }
    public fun getPort() : Port? {

        return this.port
    }
    public fun getDocumentRef() : String{

        return this.documentRef
    }

    public fun setName(name : String){
        this.name = name
    }
    public fun setCaptainName(name: String)
    {
        this.captainName = name
    }
    public fun setLatitude(lat : Float){
        this.latitude = lat
    }
    public fun setLongitude(long : Float){
        this.longitude = long
    }
}
package com.example.s16005532.boat_tracker.Model

import java.io.Serializable

class ContainershipType : Serializable{

    private var id: Int
    private var name: String
    private var length: Int
    private var height: Int
    private var width: Int


    constructor(id: Int, name: String, length: Int, height: Int, width: Int) {
        this.id = id
        this.name = name
        this.length = length
        this.height = height
        this.width = width
    }

    public fun getName() : String{

        return this.name

    }
    public fun getId(): Int{

        return this.id
    }

}
package com.example.s16005532.boat_tracker.Model

import java.io.Serializable

class Container : Serializable{

    private var id: Int
    private var length: Int
    private var height: Int
    private var width: Int

    constructor(id: Int, length: Int, height: Int, width: Int) {
        this.id = id
        this.length = length
        this.height = height
        this.width = width
    }
}
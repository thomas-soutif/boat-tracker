package com.example.s16005532.boat_tracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.s16005532.boat_tracker.Model.Containership

class showBoat : AppCompatActivity() {


    override fun onCreate(savedInstanceState : Bundle?){


        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_boat)
        val mon_bateau = Containership(1,"Merry","Luffy",(12.12).toFloat(),(12.85).toFloat(),null,null,null)
        val text = findViewById<TextView>(R.id.viewShip)
        text.setText(mon_bateau.toString())

    }




}
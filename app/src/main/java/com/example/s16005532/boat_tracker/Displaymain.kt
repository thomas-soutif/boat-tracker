package com.example.s16005532.boat_tracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.s16005532.boat_tracker.Model.Containership

class Displaymain : AppCompatActivity(),View.OnClickListener{

    override fun onCreate(savedInstanceState : Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_main)
        //start(this.findViewById(android.R.id.content))
        val BT_showListBoat : Button = findViewById(R.id.BT_showListBoat)
        BT_showListBoat.setOnClickListener(this)
    }

    override fun onClick(view: View){

        var id: Int = view.getId()
        when(id){


            R.id.BT_showListBoat->  {

                val myShowListBoatIntend : Intent  =  Intent(this, showBoat::class.java)
                startActivity(Intent(myShowListBoatIntend))

                findViewById<TextView>(R.id.text).setText("allo")
            }



        }

    }




}
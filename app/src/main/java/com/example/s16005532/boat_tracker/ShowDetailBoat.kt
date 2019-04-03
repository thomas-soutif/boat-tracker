package com.example.s16005532.boat_tracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.s16005532.boat_tracker.Model.Containership
import kotlin.math.cos
import kotlin.math.sin


class ShowDetailBoat : AppCompatActivity() {


    private var TAG: String = "L/ ShowDetailBoat ---------"
    private var list: ArrayList<Containership> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_detail_boat)

        var name_ship : TextView = findViewById(R.id.nameship)
        var modele_ship : TextView = findViewById(R.id.model)
        var distance: TextView = findViewById(R.id.distance)

        //var bateau = intent.getSerializableExtra("object") as Containership
        var bateau = ShowBoat.actual_boat
        name_ship.text = bateau!!.getName()
        modele_ship.text = bateau!!.getType()!!.getName()


        val buttonCalcul : Button = findViewById(R.id.button4)
        val buttonModify : Button = findViewById(R.id.button_modify)
        val buttonShowMaps : Button = findViewById(R.id.button_showMap)
        buttonCalcul.setOnClickListener {
            val distance_string: String = gps2mToKm(bateau.getLatitude(),bateau.getLongitude(), bateau.getPort()!!.getLatitude(),
                bateau.getPort()!!.getLongitude()).toString() + " km"
            distance.text = distance_string
            Log.d(TAG,"click on button")
        }

        buttonShowMaps.setOnClickListener{

            val myintent: Intent = Intent(this,BoatOnMaps::class.java)

            startActivity(myintent)
        }
        buttonModify.setOnClickListener{

            val myintent : Intent = Intent(this,ModifyBoat::class.java)
            startActivity(myintent)

        }

    }


    private fun gps2mToKm(lat_a: Float, lng_a: Float, lat_b: Float, lng_b: Float): Double {
        val pk = (180 / Math.PI).toFloat()

        val a1 = lat_a / pk
        val a2 = lng_a / pk
        val b1 = lat_b / pk
        val b2 = lng_b / pk

        val t1 = cos(a1) * cos(a2) * cos(b1) * cos(b2)
        val t2 = cos(a1) * sin(a2) * cos(b1) * sin(b2)
        val t3 = sin(a1) * sin(b1)
        val tt = Math.acos((t1 + t2 + t3).toDouble())

        return (6366000 * tt) / 1000
    }

}
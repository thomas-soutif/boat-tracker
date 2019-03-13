package com.example.s16005532.boat_tracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.FloatMath
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import com.example.s16005532.boat_tracker.Model.Containership
import java.io.Serializable
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

        var bateau = intent.getSerializableExtra("object") as Containership

        name_ship.text = bateau.getName()
        modele_ship.text = bateau.getType()!!.getName()


        val button : Button = findViewById(R.id.button4)
        button.setOnClickListener {
            val distance_string: String = gps2m(bateau.getLatitude(),bateau.getLongitude(), bateau.getPort()!!.getLatitude(),
                bateau.getPort()!!.getLongitude()).toString()
            distance.text = distance_string
            Log.d(TAG,"click on button")
        }

    }

    private fun gps2m(lat_a: Float, lng_a: Float, lat_b: Float, lng_b: Float): Double {
        val pk = (180 / 3.14169).toFloat()

        val a1 = lat_a / pk
        val a2 = lng_a / pk
        val b1 = lat_b / pk
        val b2 = lng_b / pk

        val t1 = cos(a1) * cos(a2) * cos(b1) * cos(b2)
        val t2 = cos(a1) * sin(a2) * cos(b1) * sin(b2)
        val t3 = sin(a1) * sin(b1)
        val tt = Math.acos((t1 + t2 + t3).toDouble())

        return 6366000 * tt
    }

}
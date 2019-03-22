package com.example.s16005532.boat_tracker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import com.example.s16005532.boat_tracker.Adapter.BoatListAdapter
import com.example.s16005532.boat_tracker.Model.Containership
import com.example.s16005532.boat_tracker.Model.Port
import kotlinx.android.synthetic.main.show_boat.*
import org.w3c.dom.Text
import android.widget.Toast
import android.widget.TextView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.icu.lang.UCharacter.GraphemeClusterBreak.LV
import android.content.Intent
import android.nfc.Tag
import android.util.Log
import android.widget.AdapterView.VIEW_LOG_TAG
import com.example.s16005532.boat_tracker.Model.Container
import com.example.s16005532.boat_tracker.Model.ContainershipType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint


class ShowBoat : AppCompatActivity(){


    private var TAG: String = "L/ ShowBoat ---------"
    private var list: ArrayList<Containership> = ArrayList()
    companion object {
        public  var actual_boat: Containership? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_boat)

        val user = FirebaseAuth.getInstance().currentUser

        val db = FirebaseFirestore.getInstance() // instance de la base de donnée firebase




        val typeship = ContainershipType(1,"Xd8",45,10,15)







        val mon_bateau = Containership(1, "Merry", "Luffy", (43.296749).toFloat(), (5.357172).toFloat(), Port(1,"MerryPort",80.56.toFloat(),148.21.toFloat()), typeship, null)
        val mon_bateau2 = Containership(2, "Death", "SkullTomson", (49.285428).toFloat(), (-4.781706).toFloat(), Port(2,"DeathPort",0.000.toFloat(),0.0001.toFloat()), typeship, null)
        val list = ArrayList<Containership>()

        list.add(mon_bateau)
        list.add(mon_bateau2)
        var list_boat: ListView = findViewById(R.id.list_boat)
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        list_boat.adapter = adapter


        if(list.isEmpty())
        {

            var no_boat = Containership(0,"No boat to show","Verify your database",0.toFloat(),0.toFloat(),null,null,null)
            list.add(no_boat)
        }
        else {



            list_boat.setOnItemClickListener { parent, view, position, id ->


                Toast.makeText(this,list.get(position).getName(),Toast.LENGTH_LONG).show()
                val myintent: Intent = Intent(this,ShowDetailBoat::class.java)
                ShowBoat.actual_boat = list.get(position)

                startActivity(myintent)

            }





        }




    }




}


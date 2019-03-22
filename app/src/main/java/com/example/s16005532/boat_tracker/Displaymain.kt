package com.example.s16005532.boat_tracker

import android.app.Activity
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.s16005532.boat_tracker.Model.Port
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class Displaymain : AppCompatActivity(),View.OnClickListener{
    private var TAG: String = "L/ DisplayName ---------"
    private val RC_SIGN_IN = 100
    override fun onCreate(savedInstanceState : Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_main)
        //start(this.findViewById(android.R.id.content))
        val providers = arrayListOf(

            AuthUI.IdpConfig.GoogleBuilder().build())


        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)





    }

    override fun onClick(view: View){

        var id: Int = view.getId()
        when(id){


            R.id.BT_showListBoat->  {

                val myShowListBoatIntend : Intent  =  Intent(this, ShowBoat::class.java)
                startActivity(Intent(myShowListBoatIntend))


            }



        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            Log.d(TAG,"result code :" + resultCode)
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in

                findViewById<TextView>(R.id.authMsg).setText("L'authentification google a réussit")
                val BT_showListBoat : Button = findViewById(R.id.BT_showListBoat)
                BT_showListBoat.setOnClickListener(this)
                // ...
            } else {
               findViewById<TextView>(R.id.authMsg).setText("L'authentification google a échoue")
            }
        }
    }


}
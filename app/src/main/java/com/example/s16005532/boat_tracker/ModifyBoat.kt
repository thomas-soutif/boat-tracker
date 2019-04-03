package com.example.s16005532.boat_tracker

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.s16005532.boat_tracker.Model.Container
import com.example.s16005532.boat_tracker.Model.Containership
import com.example.s16005532.boat_tracker.Model.ContainershipType
import com.example.s16005532.boat_tracker.Model.Port
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.synthetic.main.display_main.view.*
import kotlinx.android.synthetic.main.modify_boat.*

class ModifyBoat : AppCompatActivity() {


    private var TAG: String = "L/ Modify Boat ---------"
    private val db = FirebaseFirestore.getInstance()// instance de la base de donnée firebase
    companion object {
        public var actual_boat: Containership? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_boat)

        val user = FirebaseAuth.getInstance().currentUser
        var bateau = ShowBoat.actual_boat
        var input_name_ship : TextInputLayout = findViewById(R.id.name_ship)
        var input_captain_name : TextInputLayout = findViewById(R.id.captain_name)
        var input_latitude: EditText = findViewById(R.id.latitude_number)
        var input_longitude: EditText = findViewById(R.id.longitude_number)
        var button_modify : Button = findViewById(R.id.save_change)
        input_name_ship.hint= bateau?.getName()
        input_captain_name.hint = bateau?.getCaptainName()
        input_latitude.hint = bateau?.getLatitude().toString()
        input_longitude.hint = bateau?.getLongitude().toString()

        button_modify.setOnClickListener {

            var new_name: TextInputEditText = findViewById(R.id.new_name)
            var new_captain : TextInputEditText = findViewById(R.id.new_captain)
            var new_latitude : EditText = findViewById(R.id.latitude_number)
            var new_longitude : EditText = findViewById(R.id.longitude_number)



            val containerShipRef = db.collection("Containership").document(bateau!!.getDocumentRef())
            var number_modify : Int = 0
            ShowBoat.listeContainerShip.remove(bateau)

            if(!new_name.text.toString().isEmpty())
            {


                containerShipRef
                    .update(
                        "name",new_name.text.toString()

                    )
                    .addOnSuccessListener {
                        Log.d(TAG, "Name successfully updated!")
                        bateau.setName(new_name.text.toString())
                        Toast.makeText(this, "new name update", Toast.LENGTH_LONG).show()
                        number_modify = number_modify.plus(1)
                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }


            }

            if(!new_captain.text.toString().isEmpty())
            {
                containerShipRef
                    .update(
                        "captain_name",new_captain.text.toString()
                    )
                    .addOnSuccessListener {
                        Log.d(TAG, " Captain Name successfully updated!")
                        bateau.setCaptainName(new_captain.text.toString())
                        Toast.makeText(this, "new  captain name update", Toast.LENGTH_LONG).show()
                        number_modify = number_modify.plus(1)
                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }



            }

            if(!new_latitude.text.toString().isEmpty() && !new_longitude.text.toString().isEmpty() )
            {
                val point : GeoPoint
                point = GeoPoint(new_latitude.text.toString().toDouble(),new_longitude.text.toString().toDouble())

                containerShipRef
                    .update("pos",point)
                    .addOnSuccessListener {
                        Log.d(TAG, " Latitude and Longitude successfully updated!")
                        bateau.setLatitude(new_latitude.text.toString().toFloat())
                        bateau.setLongitude(new_longitude.text.toString().toFloat())

                    }
                    .addOnFailureListener {
                            e -> Log.w(TAG, "Error updating document", e)
                    }
44

            }
            else if(new_latitude.text.toString().isEmpty() && !new_longitude.text.toString().isEmpty())
            {
                Toast.makeText(this, "Latitude and latitude must be set", Toast.LENGTH_LONG).show()
            }
            else if(!new_latitude.text.toString().isEmpty() && new_longitude.text.toString().isEmpty())
            {
                Toast.makeText(this, "Longitude and latitude must be set", Toast.LENGTH_LONG).show()
            }

            if (number_modify == 0)
            {
                //Toast.makeText(this, "No change to Update", Toast.LENGTH_LONG).show()
            }
            else {
                number_modify = 0
            }
            ShowBoat.listeContainerShip.add(bateau)
        }



    }

}

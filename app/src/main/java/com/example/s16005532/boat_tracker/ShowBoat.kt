package com.example.s16005532.boat_tracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.s16005532.boat_tracker.Model.Containership
import com.example.s16005532.boat_tracker.Model.Port
import android.widget.Toast
import android.content.Intent
import android.util.Log
import com.example.s16005532.boat_tracker.Model.Container
import com.example.s16005532.boat_tracker.Model.ContainershipType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint


class ShowBoat : AppCompatActivity(){


    private var TAG: String = "L/ ShowBoat ---------"
    private var list: ArrayList<Containership> = ArrayList()
    private var listePort: ArrayList<Port> = ArrayList()
    private var listeContainerShipType : ArrayList<ContainershipType> = ArrayList()
    private var listeContainer : ArrayList<Container> = ArrayList()
    private val db = FirebaseFirestore.getInstance()// instance de la base de donnée firebase
    companion object {
        public  var actual_boat: Containership? = null
        var listeContainerShip: ArrayList<Containership> = ArrayList()
    }



    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG,"hey listen")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_boat)

        val user = FirebaseAuth.getInstance().currentUser
        listeContainerShip.clear()
        getPortListOnFirebase()









    }

    private fun nextProcess() {


        db.collection("Containership")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val id: Int = document.data.get("id").toString().toInt()
                    val captainName: String = document.data.get("captain_name").toString()
                    val name: String = document.data.get("name").toString()
                    val point: GeoPoint = document.data.get("pos") as GeoPoint
                    //Log.d(TAG, "${document.id} => ${document.data}")
                    val port: Port = this.getPortById(listePort, document.data.get("port_id").toString().toInt())!!
                    val type: ContainershipType =
                        this.getTypeById(listeContainerShipType, document.data.get("type_id").toString().toInt())!!

                    val ship: Containership = Containership(
                        id,
                        name,
                        captainName,
                        point.latitude.toFloat(),
                        point.longitude.toFloat(),
                        port,
                        type,
                        null,
                        document.id
                    )
                    listeContainerShip.add(ship)
                    Log.d(TAG,"ref : " + document.id)

                }
                nextNextProcess(listeContainerShip)


            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }


        //val typeship = ContainershipType(1,"Xd8",45,10,15)


        //val mon_bateau = Containership(1, "Merry", "Luffy", (43.296749).toFloat(), (5.357172).toFloat(), Port(1,"MerryPort",80.56.toFloat(),148.21.toFloat()), typeship, null)
        //val mon_bateau2 = Containership(2, "Death", "SkullTomson", (49.285428).toFloat(), (-4.781706).toFloat(), Port(2,"DeathPort",0.000.toFloat(),0.0001.toFloat()), typeship, null)
    }

    private fun nextNextProcess(listeContainerShip: ArrayList<Containership>) {
        val list = ArrayList<Containership>()


        for (ship in listeContainerShip) {
            list.add(ship)
            Log.d(TAG, ship.toString())
        }
        //list.add(mon_bateau)
        //list.add(mon_bateau2)
        var list_boat: ListView = findViewById(R.id.list_boat)
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        list_boat.adapter = adapter


        if (list.isEmpty()) {

            var no_boat =
                Containership(0, "No boat to show", "Verify your database", 0.toFloat(), 0.toFloat(), null, null, null,"")
            list.add(no_boat)
        } else {


            list_boat.setOnItemClickListener { parent, view, position, id ->


                Toast.makeText(this, list.get(position).getName(), Toast.LENGTH_LONG).show()
                val myintent: Intent = Intent(this, ShowDetailBoat::class.java)
                actual_boat = list.get(position)

                startActivity(myintent)
                this.recreate()

            }


        }
    }

    private fun getPortListOnFirebase() {


        db.collection("Port")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val portId : Int = document.data.get("id").toString().toInt()
                    val name : String = document.data.get("name").toString()
                    val point : GeoPoint = document.data.get("posPort") as GeoPoint
                    //Log.d(TAG, "${document.id} => ${document.data}")

                    val port : Port = Port(portId,name,point.latitude.toFloat(),point.longitude.toFloat())
                    listePort.add(port)

                }
                getContainerShipTypeListOnFirebase()

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }


    }

    private fun getContainerShipTypeListOnFirebase(){


        db.collection("Containership-type")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val id : Int = document.data.get("id").toString().toInt()
                    val name : String = document.data.get("name").toString()
                    val lenght : Int = document.data.get("length").toString().toInt()
                    val height : Int = document.data.get("height").toString().toInt()
                    val width: Int = document.data.get("width").toString().toInt()
                    //Log.d(TAG, "${document.id} => ${document.data}")

                    var type : ContainershipType = ContainershipType(id,name,lenght,height,width)
                    listeContainerShipType.add(type)
                }
                getContainerListOnFirebase()


            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }



    }

    private fun getContainerListOnFirebase() {


        db.collection("Container")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val id : Int = document.data.get("id").toString().toInt()
                    val containerId = document.data.get("id").toString().toInt()
                    val lenght : Int = document.data.get("length").toString().toInt()
                    val height : Int = document.data.get("height").toString().toInt()
                    val width: Int = document.data.get("width").toString().toInt()
                    //Log.d(TAG, "${document.id} => ${document.data}")

                    var container : Container = Container(id,lenght,height,width,containerId)
                    listeContainer.add(container)
                }
                nextProcess()

            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }



    }

    private fun getPortById(listePort: ArrayList<Port>, id: Int) : Port? {

        for(port in listePort)
        {
            if (port.getId().equals(id))
                return port
        }
        return null
    }

    private fun getTypeById(listeContainershipType: ArrayList<ContainershipType>,id:Int) : ContainershipType? {


        for(type in listeContainershipType)
        {
            if(type.getId().equals(id))
                return type
        }
        return null
    }



}


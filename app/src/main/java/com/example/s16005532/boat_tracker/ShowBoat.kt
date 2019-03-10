package com.example.s16005532.boat_tracker

import android.annotation.SuppressLint
import android.app.Activity
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


class ShowBoat : AppCompatActivity(), View.OnClickListener,AdapterView.OnItemClickListener {

        private var TAG: String = "L/ ShowBoat ---------"
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_boat)
        val mon_bateau = Containership(1, "Merry", "Luffy", (12.12).toFloat(), (12.85).toFloat(), Port(1,"MerryPort",80.56.toFloat(),148.21.toFloat()), null, null)
        val mon_bateau2 = Containership(2, "Death", "SkullTomson", (475.25).toFloat(), (487.85).toFloat(), Port(2,"DeathPort",0.000.toFloat(),0.0001.toFloat()), null, null)
        val list = ArrayList<Containership>()

        list.add(mon_bateau)
        list.add(mon_bateau2)
        var adapter: BoatListAdapter
        adapter = BoatListAdapter(this,list)
        var list_boat: ListView = findViewById(R.id.list_boat)
        list_boat.adapter=adapter


        list_boat.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this,"allo Zacord ",Toast.LENGTH_LONG).show()
            Log.d(TAG,"item listener")
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}



// class ShowBoat : AppCompatActivity() {
// var listView: ListView? = null
// var boatList = ArrayList<Containership>()
// var adapter: BoatListViewAdapter? = null
//
// override fun onCreate(savedInstanceState : Bundle?){
//
// super.onCreate(savedInstanceState)
// setContentView(R.layout.show_boat)
// val mon_bateau = Containership(1,"Merry","Luffy",(12.12).toFloat(),(12.85).toFloat(),null,null,null)
// listView = findViewById(R.id.list_boat)
// boatList.add(mon_bateau)
// adapter = BoatListViewAdapter(this,boatList)
//
//
//
// adapter?.notifyDataSetChanged()
// }
//
// class BoatListViewAdapter(private val activity: Activity, boatList: List<Containership>): BaseAdapter() {
//
// private var boatList = ArrayList<Containership>()
//
// init {
// this.boatList = boatList as ArrayList
// }
// @SuppressLint("InflateParams","ViewHolder")
// override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
// var vi: View = convertView as View
// val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
// vi = inflater.inflate(R.layout.boat_list_row,null)
// val name_boat = vi.findViewById<TextView>(R.id.name_boat)
// val name_captain = vi.findViewById<TextView>(R.id.captain_name)
// name_boat.text = boatList[position].getName()
// name_captain.text = boatList[position].getCaptainName()
// return vi
//
//
// }
//
// override fun getItem(position: Int): Any {
// return position
// }
//
// override fun getItemId(position: Int): Long {
// return position.toLong()
// }
//
// override fun getCount(): Int {
// return boatList.size
// }
//
//
// }
//
//
// }
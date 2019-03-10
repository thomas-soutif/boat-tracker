package com.example.s16005532.boat_tracker.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.s16005532.boat_tracker.Model.Containership
import com.example.s16005532.boat_tracker.R
import kotlinx.android.synthetic.main.boat_list_row.view.*
import android.widget.Toast




class BoatListAdapter(context:Context,list: ArrayList<Containership>) : BaseAdapter()
{
    var boatList : ArrayList<Containership>?=null
    var inflator: LayoutInflater?=null
    var init_context : Context?=null
    init {
        boatList = list
        inflator = LayoutInflater.from(context)
        init_context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view:View?=null
        view = inflator?.inflate(com.example.s16005532.boat_tracker.R.layout.boat_list_row,parent,false)
        var name_boat: TextView
        var name_captain: TextView

        name_boat = view?.findViewById(com.example.s16005532.boat_tracker.R.id.name_boat) as TextView
        name_captain = view?.findViewById(com.example.s16005532.boat_tracker.R.id.captain_name) as TextView

        var boat : Containership
        boat = getItem(position)!!

        name_boat.setText(boat.getName())
        name_captain.setText(boat.getCaptainName())




        return view!!
    }

    override fun getItem(position: Int): Containership? {
       return boatList?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return boatList!!.size
    }
}
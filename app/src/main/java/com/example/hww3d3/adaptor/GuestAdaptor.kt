package com.example.hww3d3.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.hww3d3.R
import com.example.hww3d3.model.Guest


class GuestAdapter(private val guestList: List<Guest>, private val delegate: GuestAdapterDelegate) : BaseAdapter() {

    interface GuestAdapterDelegate{
        fun deleteBooking(guestPosition: Int)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.guest_layout, parent, false)

        view.findViewById<TextView>(R.id.room_number_textview).text =
            guestList[position].roomNumber.toString()
        view.findViewById<TextView>(R.id.guest_name_textview).text = guestList[position].name
        view.findViewById<TextView>(R.id.price_textview).text =
            "$${guestList[position].price}.00"

        view.findViewById<Button>(R.id.delete_this).setOnClickListener {

            delegate.deleteBooking(position)

        }


        return view

    }

    override fun getItem(position: Int): Guest {
        return guestList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return guestList.size
    }


}
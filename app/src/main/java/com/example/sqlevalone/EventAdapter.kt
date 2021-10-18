package com.example.sqlevalone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val context:Context,
                   private val eventList:MutableList<EventModal>,
                   listener: OnClickListener
): RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
       val inflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.item_layout,parent,false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
       val eventModal:EventModal = eventList[position]
        holder.event_name.text = eventModal.eventName
        holder.event_desc.text = eventModal.eventDesc
        holder.event_date.text = eventModal.eventDate
        holder.event_location.text = eventModal.eventLocation
        holder.event_price.text = eventModal.eventPrice
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

}
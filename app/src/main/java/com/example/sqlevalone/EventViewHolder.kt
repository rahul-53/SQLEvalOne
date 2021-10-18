package com.example.sqlevalone

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var eventId:TextView = itemView.findViewById(R.id.eventId)
    var event_name:TextView = itemView.findViewById(R.id.eventName)
    var event_desc:TextView = itemView.findViewById(R.id.eventDesc)
    var event_date:TextView = itemView.findViewById(R.id.eventDate)
    var event_location:TextView = itemView.findViewById(R.id.eventLocation)
    var event_price:TextView = itemView.findViewById(R.id.eventPrice)
}

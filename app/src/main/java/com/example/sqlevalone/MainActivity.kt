package com.example.sqlevalone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*

abstract class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var databaseHandler:DatabaseHandler
    private var eventList:MutableList<EventModal> = mutableListOf()
    private lateinit var mAdapter:EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHandler = DatabaseHandler(this)
        eventList = databaseHandler.getEvents()

        mAdapter = EventAdapter(this, eventList,this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = mAdapter

        floatingActionButton.setOnClickListener {
            databaseHandler.insertEvent("Event One","this is first event",
                "12/08/2021","Bangalore","1200")
        }

    }
    override fun onEdit(event: EventModal) {
        databaseHandler.updateEvent(event.eventId, "Event Two", "edit event for one",
            "13/08/2021", "Bangalore", "1800")
        eventList = databaseHandler.getEvents()
        mAdapter.notifyDataSetChanged()
    }
    override fun  onDelete(event: EventModal){
        databaseHandler.deleteEvent(event.eventId)
        eventList = databaseHandler.getEvents()
        mAdapter.notifyDataSetChanged()
    }

}
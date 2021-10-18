package com.example.sqlevalone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

abstract class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var databaseHandler:DatabaseHandler
    var eventList:MutableList<EventModal> = mutableListOf()
    private lateinit var mAdapter:EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHandler = DatabaseHandler(this)
        eventList = databaseHandler.getRoutines()

        mAdapter = EventAdapter(this, eventList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = mAdapter

        floatingActionButton.setOnClickListener {
            databaseHandler.insertRoutine("Event One","this is first event",
                "12/08/2021","Bangalore","1200")
        }
    }
    override fun onEdit(event: EventModal) {
        databaseHandler.updateRoutine(event.eventId, "Event Two", "edit event for one",
            "13/08/2021", "Bangalore", "1800")
        eventList = databaseHandler.getRoutines()
        mAdapter.notifyDataSetChanged()
    }
    override fun  onDelete(event: EventModal){
        databaseHandler.deleteRoutine(event.eventId)
        eventList = databaseHandler.getRoutines()
        mAdapter.notifyDataSetChanged()
    }

}
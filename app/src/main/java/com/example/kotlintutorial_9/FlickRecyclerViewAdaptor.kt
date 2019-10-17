package com.example.kotlintutorial_9

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlickRecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}

class FlickRecyclerViewAdaptor(private var photolist : List<Photo>): RecyclerView.Adapter<FlickRecyclerViewHolder>(){
    private val TAG = "FlickRecyclerViewAdapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickRecyclerViewHolder {
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, ".getItemCount called")
        return if (photolist.isNotEmpty()) photolist.size else 0
    }

    fun loadNewData(newPhoto: List<Photo>){
        photolist = newPhoto
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photolist.isNotEmpty()) photolist[position]else null
    }

    override fun onBindViewHolder(holder: FlickRecyclerViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
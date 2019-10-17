package com.example.kotlintutorial_9

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

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
        //Log.d(TAG, ".getItemCount called")
        return if (photolist.isNotEmpty()) photolist.size else 1
    }

    fun loadNewData(newPhoto: List<Photo>){
        photolist = newPhoto
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photolist.isNotEmpty()) photolist[position]else null
    }

    override fun onBindViewHolder(holder: FlickRecyclerViewHolder, position: Int) {

        if (photolist.isEmpty()){
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.setText(R.string.empty_pho)
        }else{
            val photoItem = photolist[position]
            //Log.d(TAG, ".onBindViewHolder: ${photoItem.title} --> $position")
            Picasso.with(holder.thumbnail.context).load(photoItem.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)

            holder.title.text = photoItem.title
        }
    }
}
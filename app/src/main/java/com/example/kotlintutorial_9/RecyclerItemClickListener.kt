package com.example.kotlintutorial_9

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val listener: OnRecyclerItemClickListener)
    : RecyclerView.SimpleOnItemTouchListener(){

    private  val TAG = "RecyclerItemClickListen"

    interface OnRecyclerItemClickListener{
        fun onItemClick(view: View, position: Int)
        fun OnItemLongClick(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, ".onInterceptTouchEvent: starts $e")
        //return super.onInterceptTouchEvent(rv, e)
        return true
    }
}
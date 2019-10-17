package com.example.kotlintutorial_9

import android.location.Criteria
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), GetRewData.OnDownloadComplete, GetFlickrJsonData.OnDataAvailable {

    private val TAG = "MainActivity"
    private val flickRecyclerViewAdaptor = FlickRecyclerViewAdaptor(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = flickRecyclerViewAdaptor

        val url = createUri("https://www.flickr.com/services/feeds/photos_public.gne", "android.oreo", "en-us", true)
        val getRewData = GetRewData(this)
        getRewData.execute(url)
        Log.d(TAG, "onCreate ends")
    }
    //baseURL: String, searchCriteria: String, lang: String, matchAll: Boolean): String

    private fun createUri(baseURL: String, searchCriteria: String, lang: String, matchAll: Boolean): String{
        Log.d(TAG, ".createUrl starts")

        return Uri.parse(baseURL).
            buildUpon().
            appendQueryParameter("tags", searchCriteria).
            appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY").
            appendQueryParameter("lang", lang).
            appendQueryParameter("format", "json").
            appendQueryParameter("nojsoncallback", "1").
            build().toString()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, "onCreateOptionsMenu called")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected called")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
//    companion object{
//        private const val TAG = "MainActivity"
//    }

    override fun onDownloadComplete(data: String, status: DownloadStatus){
        if (status == DownloadStatus.OK){
            Log.d(TAG, "onDownloadComplete called")

            val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
        }else{
            Log.d(TAG, "onDownloadComplete failed with status $status. Error message is: $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable called")
        flickRecyclerViewAdaptor.loadNewData(data)
        Log.d(TAG, "onDataAvailable ends")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError called with ${exception.message}")
    }
}

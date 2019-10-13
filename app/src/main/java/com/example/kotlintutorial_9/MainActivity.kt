package com.example.kotlintutorial_9

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val getRewData = GetRewData()
        getRewData.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=android.oreo&format=json&nojsoncallback=1")

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        Log.d(TAG, "onCreate ends")
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

    fun onDownloadComplete(data: String, status: DownloadStatus){
        if (status == DownloadStatus.OK){
            Log.d(TAG, "onDownloadComplete called, data is $data")
        }else{
            Log.d(TAG, "onDownloadComplete failed with status $status. Error message is: $data")
        }
    }
}

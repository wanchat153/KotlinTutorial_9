package com.example.kotlintutorial_9

import android.os.AsyncTask
import android.util.Log
import android.view.View
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL


enum class DownloadStatus{
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}
class GetRewData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>(){

    private val TAG = "GetRewData"
    private var downloadStatus = DownloadStatus.IDLE

    interface OnDownloadComplete{
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

//    private var listener: MainActivity? = null
//
//    fun setDownloadCompleteListener(callbackObject: MainActivity){
//        listener = callbackObject
//    }

    override fun onPostExecute(result: String) {
        Log.d(TAG, "onPostExecute called")
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null){
            downloadStatus = DownloadStatus.NOT_INITIALISED
            return "No URL specified"
        }
        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        }catch (e: Exception){
            val errorManage = when(e){
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALISED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IO Exception reading data ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: Security exception: Needs permission ${e.message}"
                }else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "Unknown error: ${e.message}"
                }
            }
            Log.d(TAG, "errorMessage")
            return errorManage
        }
    }
}
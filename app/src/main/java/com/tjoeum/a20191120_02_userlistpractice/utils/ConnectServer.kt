package com.tjoeum.a20191120_02_userlistpractice.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonResponseHandler{
        fun onResponse(json:JSONObject)
    }

    companion object {

        var BASE_URL = "http://192.168.0.26:5000"

        fun gerRequestUserList(context: Context, neenActive: String, jsonResponseHandler: JsonResponseHandler){

            val client = OkHttpClient()

            var urBuilder = "${BASE_URL}/admin/user".toHttpUrlOrNull()!!.newBuilder()
            urBuilder.addEncodedQueryParameter("active",neenActive)

            var requestUrl = urBuilder.build().toString()

            Log.d("가공된GETURL",requestUrl)

            val request = Request.Builder().url(requestUrl).build()


            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val body = response.body!!.string()
                    val jsonObject = JSONObject(body)
                    jsonResponseHandler.onResponse(jsonObject)

                }
            })


        }

    }
}
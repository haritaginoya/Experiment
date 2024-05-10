package com.first.experiment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var que = Volley.newRequestQueue(this)
        var url = "https://jsonplaceholder.typicode.com/posts"
        var request = StringRequest(Request.Method.GET, url, { response ->


            var array = JSONArray(response)

            for (i in 0 until array.length()) {
                var map = array.getJSONObject(i)
                var userid = map.getInt("userId")
                var title = map.getString("title")

                Log.d("---====", "onCreate: $userid  $title")

            }
        }, { error ->

            Log.d("=======", "onCreate: $error")
        })

        que.add(request)

    }
}
       package com.arpit.simplerecylerviewexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.arpit.simplerecylerviewexample.Adaptor.recyclerAdaptor
import com.arpit.simplerecylerviewexample.models.heros
import org.json.JSONException

       class MainActivity : AppCompatActivity() {
           private lateinit var recyclerView: RecyclerView
           private lateinit var recyclerAdaptor: recyclerAdaptor
           private lateinit var layoutManager: RecyclerView.LayoutManager

           val heroInfoList = arrayListOf<heros>()

           override fun onCreate(savedInstanceState: Bundle?) {
               super.onCreate(savedInstanceState)
               setContentView(R.layout.activity_main)



               recyclerView = findViewById(R.id.recyclerview)
               layoutManager = LinearLayoutManager(this)

                sendingRequest()
           }



           private fun sendingRequest(){
               val herosUrl = "https:simplifiedcoding.net/demos/view-flipper/heroes.php"
               val queue=Volley.newRequestQueue(this)

               val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, herosUrl, null,
                       Response.Listener {
                           try {
                               val heros = it.getJSONArray("heroes")
                               for (i in 0 until heros.length()) {
                                   val heroesJsonObject = heros.getJSONObject(i)

                                   var herosString = heros(
                                           heroesJsonObject.getString("name"),
                                           heroesJsonObject.getString("imageurl")
                                   )
                                   heroInfoList.add(herosString)
                                   recyclerAdaptor = recyclerAdaptor(this, heroInfoList)
                                   recyclerView.adapter = recyclerAdaptor
                                   recyclerView.layoutManager = layoutManager

                               }
                           }catch (e:JSONException){

                           }
                           },

                       Response.ErrorListener {
                           println("error is $it")
                       }){


               }
               queue.add(jsonObjectRequest)

           }



       }



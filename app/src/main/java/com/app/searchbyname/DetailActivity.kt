package com.app.searchbyname

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.detail_main.*
import kotlinx.android.synthetic.main.search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity(){

    private lateinit var mAdapter: MyAdapter1
    private lateinit var apiService: MealService
    private lateinit var text_detail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_main)

        val bundle :Bundle?= intent.extras
        val message = bundle!!.getString("id")
        val strUser: String = intent.getStringExtra("id")
        //Toast.makeText(applicationContext,"id : ${message}",Toast.LENGTH_SHORT).show()

        text_detail = findViewById(R.id.text_detail)
        text_detail.setText(message)
        initRetrofit()
        getDetailbyId()
    }

    fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MealService::class.java)
    }
    private fun bindData(response:LatestMealResponse) {
        mAdapter = MyAdapter1(response.meals!!,this)
        recyclerViewDetail.adapter = mAdapter
        val layoutManager = GridLayoutManager(this, 1, GridLayout.VERTICAL, false)
        recyclerViewDetail.setLayoutManager(layoutManager)
    }

    private fun getDetailbyId() {

        apiService.getDetailbyId(text_detail.text.toString()).enqueue(object : Callback<LatestMealResponse> {
            override fun onFailure(call: Call<LatestMealResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext,t?.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LatestMealResponse>?, response: Response<LatestMealResponse>?) {
                if (response != null) {
                    // bind data to item list
                    bindData(response.body()!!)
                }
            }
        })
    }
}
package com.app.searchbyname

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MyAdapter
    private lateinit var apiService: MealService
   // var searchBtn = findViewById(R.id.searchBtn) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()
        getLatestMeals()
        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent);
        }
    }

    private fun getLatestMeals() {
        apiService.getLatestMeals().enqueue(object : Callback<LatestMealResponse> {
            override fun onFailure(call: Call<LatestMealResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext,t?.localizedMessage,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LatestMealResponse>?, response: Response<LatestMealResponse>?) {
                if (response != null) {
                    // bind data to item list
                    bindData(response.body())
                }
            }
        })
    }

    private fun bindData(response:LatestMealResponse) {
        mAdapter = MyAdapter(response.meals!!,this,object : ItemClickListener{
            override fun onItemClicked(id: String) {

                //To DetailActivity with id Data
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)

                Toast.makeText(applicationContext,id,Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = mAdapter
        val layoutManager = GridLayoutManager(this, 2, VERTICAL, false)
        recyclerView.setLayoutManager(layoutManager)
    }

    fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MealService::class.java)
    }
}

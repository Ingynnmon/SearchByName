package com.app.searchbyname

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query


class SearchActivity : AppCompatActivity(), ItemClickListener {


    private lateinit var mAdapter: MyAdapter
    private lateinit var apiService: MealService
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        //this.startActivity(intent)

        initRetrofit()

        editText = findViewById(R.id.mealName)
        searchMeal.setOnClickListener{
            getSearchMeals()
        }
    }

    fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MealService::class.java)
    }
    private fun bindData(response:LatestMealResponse) {
        mAdapter = MyAdapter(response.meals!!,this,this)
        recyclerViewSearch.adapter = mAdapter
        val layoutManager = GridLayoutManager(this, 2, GridLayout.VERTICAL, false)
        recyclerViewSearch.setLayoutManager(layoutManager)
    }

    override fun onItemClicked(id: String) {
        //To DetailActivity with id Data
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)

        //Toast.makeText(applicationContext,id,Toast.LENGTH_SHORT).show()
  }

    private fun getSearchMeals() {

        apiService.searchMeals(editText.text.toString()).enqueue(object : Callback<LatestMealResponse> {
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
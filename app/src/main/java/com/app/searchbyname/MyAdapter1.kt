package com.app.searchbyname

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MyAdapter1(private val meals:ArrayList<Meal>,
                private val mContext: Context) : RecyclerView.Adapter<MyHolder1>() {
    override fun onBindViewHolder(holder: MyHolder1, position: Int) {
        holder?.index(meals[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder1 {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.detail, parent, false)
        return MyHolder1(v, mContext)
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}
package com.app.searchbyname

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MyAdapter(private val meals:ArrayList<Meal>,
                private val mContext: Context,
                private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.main_page, parent, false)
        return MyHolder(v, mContext,itemClickListener)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder?.index(meals[position])
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}
package com.app.searchbyname

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MyHolder1(itemView: View, private val mContext: Context): RecyclerView.ViewHolder(itemView){

    private val iview: ImageView
    private val tview: TextView
    private val instructionDetail: TextView

    init {
        iview = itemView.findViewById<View>(R.id.iview) as ImageView
        tview = itemView.findViewById<View>(R.id.tview) as TextView
        instructionDetail=itemView.findViewById<View>(R.id.instructiondetail) as TextView
    }

    fun index(meal : Meal){
        Glide.with(mContext).load(meal.image).into(iview)
        tview.text= meal.strMeal
        instructionDetail.text = meal.instruction
    }
}

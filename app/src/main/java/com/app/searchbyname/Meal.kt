package com.app.searchbyname

import com.google.gson.annotations.SerializedName

class Meal{
    @SerializedName("idMeal")
     val idMeal: String?=null

    @SerializedName("strMeal")
     val strMeal:String?=null

    @SerializedName("strDrinkAlternate")
     val strDrinkAlternate:String?=null

    @SerializedName("strCategory")
     val category:String?=null

    @SerializedName("strArea")
     val area:String?=null

    @SerializedName("strMealThumb")
     val image:String?=null

    @SerializedName("strTags")
     val tag:String?=null
}
package com.app.searchbyname

import com.app.searchbyname.Meal
import com.google.gson.annotations.SerializedName

class LatestMealResponse {
    @SerializedName("meals")
    val meals : ArrayList<Meal>? = null
}
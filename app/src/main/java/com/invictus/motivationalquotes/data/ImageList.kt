package com.invictus.motivationalquotes.data

import com.invictus.motivationalquotes.db.MotivationSharedPreferences

object ImageList {


    fun getSelectedImage():String = imageList()[MotivationSharedPreferences.SELECTED_IMAGE]


    fun imageList(): List<String>{
        return arrayListOf(
            "https://images.pexels.com/photos/1459505/pexels-photo-1459505.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
            "https://images.pexels.com/photos/5829761/pexels-photo-5829761.jpeg?auto=compress&cs=tinysrgb&w=600"
        )
    }


}
package com.example.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.sunnyweather.MyApplication
import com.example.sunnyweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {

    //将Place对象存储进SP文件中
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    //从SP文件中读取Place对象
    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    //判断SP文件中是否保存有Place对象
    fun isSavedPlace(): Boolean {
        return sharedPreferences().contains("place")
    }

    //获取一个SP文件
    private fun sharedPreferences() =
        MyApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}
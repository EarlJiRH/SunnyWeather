package com.example.sunnyweather.util

import android.content.Context
import android.content.Intent
import com.example.sunnyweather.MyApplication

inline fun <reified T> startActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

inline fun <reified T> startActivity(
    context: Context = MyApplication.context,
    block: Intent.() -> Unit
) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
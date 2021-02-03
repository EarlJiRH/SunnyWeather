package com.example.sunnyweather.util

import android.content.Context
import android.widget.Toast
import com.example.sunnyweather.MyApplication

fun toastShort(context: Context = MyApplication.context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

/***
 * Toast 字符串文本消息
 */
fun String.showToast(context: Context = MyApplication.context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

/***
 * Toast 字符串资源文本消息
 */
fun Int.showToast(context: Context = MyApplication.context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}
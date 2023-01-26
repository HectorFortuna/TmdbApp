package com.hectorfortuna.tmdbapp.util

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


fun Context.getJsonFromRaw(@RawRes id: Int) =
    this.resources.openRawResource(id).bufferedReader().use { it.readText() }

fun <T: Any> Context.getJsonResponse(@RawRes id: Int, clazz: Class<T>): T {
    val json = this.resources.openRawResource(id)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(json, clazz)
}

fun String.transformToResponseBody() = this.toResponseBody("application/json".toMediaTypeOrNull())
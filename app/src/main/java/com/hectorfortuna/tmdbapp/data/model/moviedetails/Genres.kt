package com.hectorfortuna.tmdbapp.data.model.moviedetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
) : Parcelable

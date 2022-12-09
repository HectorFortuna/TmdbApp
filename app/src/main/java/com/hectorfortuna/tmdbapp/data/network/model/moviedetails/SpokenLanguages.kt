package com.hectorfortuna.tmdbapp.data.network.model.moviedetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguages(
    @SerializedName("iso_639_1")
    var iso: String,
    @SerializedName("name")
    var name: String
): Parcelable

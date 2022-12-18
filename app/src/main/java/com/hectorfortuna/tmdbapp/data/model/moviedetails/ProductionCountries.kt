package com.hectorfortuna.tmdbapp.data.model.moviedetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountries(
    @SerializedName("iso_3166_1")
    var iso: String,
    @SerializedName("name")
    var name: String
): Parcelable

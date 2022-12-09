package com.hectorfortuna.tmdbapp.data.network.model.moviedetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompanies(
    @SerializedName("name")
    var name : String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("logo_path")
    var logoPath: String?,
    @SerializedName("origin_country")
    var originCountry: String
): Parcelable
package com.hectorfortuna.tmdbapp.data.model.moviedetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BelongsToCollection(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("backdrop_path")
    var backdropPath: String
):Parcelable

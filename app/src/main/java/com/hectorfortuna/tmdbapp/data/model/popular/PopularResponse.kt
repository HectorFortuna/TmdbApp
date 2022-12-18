package com.hectorfortuna.tmdbapp.data.model.popular

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PopularResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: @RawValue List<Result>,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("total_pages")
    val totalPages: Int
): Parcelable

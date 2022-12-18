package com.hectorfortuna.tmdbapp.data.model.moviedetails

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
@Entity(tableName = "movie_details_table")
@Parcelize
data class MovieDetails(
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("belongs_to_collection")
    var belongsToCollection: @RawValue BelongsToCollection?,
    @SerializedName("budget")
    var budget: Int,
    @SerializedName("genres")
    var genres: List<Genres>,
    @SerializedName("homepage")
    var homePage: String?,
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("overview")
    var overView: String?,
    @SerializedName("popularity")
    var popularity: Number,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompanies>,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountries>,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("revenue")
    var revenue: Int,
    @SerializedName("runtime")
    var runTime: Int?,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguages>,
    @SerializedName("status")
    var status: String,
    @SerializedName("tagline")
    var tagline: String?,
    @SerializedName("title")
    var title: String,
    @SerializedName("video")
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Number,
    @SerializedName("vote_counter")
    var voteCount: Int

) : Parcelable

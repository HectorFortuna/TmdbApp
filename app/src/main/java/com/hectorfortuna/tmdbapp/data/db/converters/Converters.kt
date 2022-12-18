package com.hectorfortuna.tmdbapp.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hectorfortuna.tmdbapp.data.model.moviedetails.*
import java.lang.reflect.Type
import java.util.*

class Converters {
    @TypeConverter
    fun fromBelongsToCollection(belongsToCollection: BelongsToCollection?): String? =
        Gson().toJson(belongsToCollection)

    @TypeConverter
    fun toBelongsToCollection(string: String?): BelongsToCollection? =
        Gson().fromJson(string, BelongsToCollection::class.java)

    @TypeConverter
    fun fromGenresList(listGenres: List<Genres>): String =
        Gson().toJson(listGenres)

    @TypeConverter
    fun toGenresList(string: String?): List<Genres?> {
        if (string == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Genres?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun fromProductionCompaniesList(listProductionCompanies: List<ProductionCompanies>): String =
        Gson().toJson(listProductionCompanies)

    @TypeConverter
    fun toProductionCompaniesList(string: String?): List<ProductionCompanies?> {
        if (string == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ProductionCompanies?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun fromProductionCountriesList(listProductionCountries: List<ProductionCountries>): String =
        Gson().toJson(listProductionCountries)

    @TypeConverter
    fun toProductionCountriesList(string: String?): List<ProductionCountries?> {
        if (string == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ProductionCountries?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun fromSpokenLanguages(listSpokenLanguages: List<SpokenLanguages>): String =
        Gson().toJson(listSpokenLanguages)

    @TypeConverter
    fun toSpokenLanguages(string: String?): List<SpokenLanguages?> {
        if (string == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<SpokenLanguages?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

}

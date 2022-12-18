package com.hectorfortuna.tmdbapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hectorfortuna.tmdbapp.data.db.converters.Converters
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails

@Database(entities = [MovieDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        var MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //TODO WHEN IS NECESSARY
            }
        }
    }

    abstract fun moviesDetailsDao(): MovieDetailsDAO
}
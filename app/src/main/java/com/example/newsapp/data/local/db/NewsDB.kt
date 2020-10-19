package com.example.newsapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.local.db.dao.NewsDAO
import com.example.newsapp.data.local.db.entities.AllNewsEntity
import com.example.newsapp.data.local.db.entities.SourceEntity
import com.example.newsapp.utils.DATABASE_NAME

@Database(entities = [SourceEntity::class,AllNewsEntity::class], version = 1, exportSchema = false)

abstract class NewsDB : RoomDatabase() {
    /**
     * Connects the database to the DAO.
     */
    abstract val newsDAO: NewsDAO

    companion object {

        @Volatile
        private var INSTANCE: NewsDB? = null

        fun getInstance(context: Context): NewsDB {

            synchronized(this) {


                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            NewsDB::class.java,
                            DATABASE_NAME
                    )
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}
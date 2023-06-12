package com.example.p_kart.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductModel::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    companion object {
        private var database: AppDatabase? = null
        private var DATABASE_NAME = "pkart"


        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )//it is used to execute queries on main thread but it is not recommended to use it as it can
                    //  block the UI and make the app unresponsive
                    .allowMainThreadQueries()
                    //if a migration from the current version to the latest version is not found,
                    // the database should be recreated instead of throwing an exception.
                    // This means that all existing data will be lost during the migration process.
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }
    }
        abstract fun productDao(): ProductDao

}
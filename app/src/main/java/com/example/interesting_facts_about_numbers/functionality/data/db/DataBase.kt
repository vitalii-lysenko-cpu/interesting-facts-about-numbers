package com.example.interesting_facts_about_numbers.functionality.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.interesting_facts_about_numbers.functionality.entity.NumberFact

@Database(entities = [NumberFact::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun factDao(): FactDao

//    companion object {
//        @Volatile
//        private var INSTANCE: DataBase? = null
//
//        fun getInstance(context: Context): DataBase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DataBase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}
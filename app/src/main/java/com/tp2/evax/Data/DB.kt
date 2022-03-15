package com.tp2.evax.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class,Vaccination::class],version = 2 )
abstract class DB : RoomDatabase() {
    abstract fun personDAO() : PersonDao
    abstract fun vaccinationDAO():VaccinationDao
    companion object {
        private var INSTANCE: DB? = null

        fun getInstance(context: Context): DB? {
            if (INSTANCE == null) {
                synchronized(DB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DB::class.java, "person_vaccination.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
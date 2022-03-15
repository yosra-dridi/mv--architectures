package com.tp2.evax.Data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
 data class Person(val name: String,
             val birthDate: Long ) {
    @PrimaryKey(autoGenerate = true)
    var id : Long =0
}
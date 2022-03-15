package com.tp2.evax.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "vaccination_table",
    indices = [Index("personId")],
    foreignKeys = [ForeignKey(entity = Person::class,parentColumns = ["id"],personColumns =["personId"] )])
class Vaccination(val name : String , val time : Long,val personId:Long) {
    @PrimaryKey(autoGenerate = true)
    var id :Long = 0
}
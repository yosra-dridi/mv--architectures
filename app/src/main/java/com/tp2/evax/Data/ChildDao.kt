package com.tp2.evax.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person) : Long

    @Query("select * from person_table")
    fun getAllPersonren(): LiveData<List<Person>>

    @Query("select * from person_table where id = :personId" )
    fun getPerson(personId: Long): LiveData<Person>
}
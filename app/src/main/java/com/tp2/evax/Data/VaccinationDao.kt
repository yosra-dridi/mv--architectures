package com.tp2.evax.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VaccinationDao {
    @Insert
    fun insert(vaccination: Vaccination)

    @Insert
    fun insert(vaccinations: List<Vaccination>)

    @Query("select * from vaccination_table where personId = :personId" )
    fun getVaccinationForPerson(personId: Long):LiveData<List<Vaccination>>
}
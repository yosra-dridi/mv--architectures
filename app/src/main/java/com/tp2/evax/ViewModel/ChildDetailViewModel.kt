package com.tp2.evax.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tp2.evax.Data.Person
import com.tp2.evax.Data.Repo
import com.tp2.evax.Data.Vaccination

class PersonDetailViewModel (private val repo : Repo): ViewModel() {


    lateinit var person: LiveData<Person>
    lateinit var vaccinations: LiveData<List<Vaccination>>
    var personId :Long = -1
    set(value) {
        person = repo.getPerson(value)
        vaccinations = repo.getVaccinationsForPerson(value)
    }


}
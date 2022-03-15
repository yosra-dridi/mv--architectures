package com.tp2.evax.ViewModel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tp2.evax.Commons.Utils
import com.tp2.evax.Data.Person
import com.tp2.evax.Data.Repo
import java.util.*

class AddPersonViewModel(private val repo : Repo): ViewModel() {
    private var birthDateCalendar:Calendar? = null
    var personName :String? = null

    var activityAliveLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var personBirthDayLiveData : MutableLiveData<String> = MutableLiveData()


    fun setPersonBirthDay(calendar : Calendar){
        birthDateCalendar = calendar
        personBirthDayLiveData.value = Utils.formatDate(calendar)
    }
     fun addPerson () {
        val person = Person(personName!!, birthDateCalendar!!.timeInMillis)
         repo.insertPerson(person)
         activityAliveLiveData.value = false

     }


    fun hasValidName(): Boolean {
        return !TextUtils.isEmpty(personName)
    }
    fun hasValidBirthday(): Boolean {
        return birthDateCalendar!=null
    }

}
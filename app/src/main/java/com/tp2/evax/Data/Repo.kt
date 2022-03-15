package com.tp2.evax.Data

import androidx.lifecycle.LiveData
import com.tp2.evax.Commons.VaccinationUtils
import com.tp2.evax.Notifications.NotifyManager
import org.jetbrains.anko.doAsync


class Repo(db: DB) {

    private var personDao: PersonDao
    private var vaccinationDao: VaccinationDao

    fun getPerson(): LiveData<List<Person>> {
        return personDao.getAllPerson()
    }

    fun getPerson(personId: Long) = personDao.getPerson(personId)

    fun insertPerson(person: Person) {
        doAsync {
           val id = personDao.insert(person)
            person.id=id
            val vaccinations = VaccinationUtils.createNeededVaccination(person.birthDate,id)
            vaccinationDao.insert(vaccinations )
            for (va in vaccinations){
                NotifyManager.scheduleNotification(person,va)
            }
        }

    }



     fun getVaccinationsForPerson(personId:Long): LiveData<List<Vaccination>> {
        return vaccinationDao.getVaccinationForPerson(personId)
     }

    companion object {
        private var INSTANCE: Repo? = null

        fun init(db: DB): Repo {
            if (INSTANCE == null) {
                synchronized(Repo::class) {
                    INSTANCE =
                        Repo(db)
                }
            }
            return INSTANCE!!
        }

        fun getInstance(): Repo {
            if (INSTANCE == null) throw IllegalStateException("cant get instance before initializing")
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    init {
        personDao = db.personDAO()
        vaccinationDao = db.vaccinationDAO()
    }
}
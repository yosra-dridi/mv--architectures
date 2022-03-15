package com.tp2.evax.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.tp2.evax.Data.Person
import com.tp2.evax.Data.Repo
import com.tp2.evax.View.AddPersonActivity
import com.tp2.evax.View.PersonDetailsActivity

class PersonListViewModel(private val repo : Repo):ViewModel() {

    fun getPerson () = repo.getPerson();
    fun startAddingPerson(context: Context) {
        context.startActivity(AddPersonActivity.getStartIntent(context))
    }

    fun onClickedPerson(context: Context,person:Person) {
        context.startActivity(PersonDetailsActivity.getStartIntent(context,person.id))

    }

}
package com.tp2.evax.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp2.evax.Adapater.VaccinationAdapter
import com.tp2.evax.Commons.DependencyManager
import com.tp2.evax.Commons.Utils
import com.tp2.evax.Data.Person
import com.tp2.evax.Data.Vaccination
import com.tp2.evax.R
import com.tp2.evax.ViewModel.PersonDetailViewModel
import kotlinx.android.synthetic.main.activity_person_details.*

class PersonDetailsActivity : AppCompatActivity() {
    private lateinit var adapter: VaccinationAdapter

    companion object {
        private const val PERSON_ID="EXTRA_PERSON_ID"

        fun getStartIntent(context: Context, personId:Long): Intent {
            val intent = Intent(context,PersonDetailsActivity::class.java)
            intent.putExtra(PERSON_ID,personId)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        val personId :Long = intent.getLongExtra(PERSON_ID,-1)
        val viewModel  = ViewModelProviders.of(this, DependencyManager.modelViewFactory).get(PersonDetailViewModel::class.java)
        adapter = VaccinationAdapter()
        activity_person_details_recycler_view.layoutManager=LinearLayoutManager(this)
        activity_person_details_recycler_view.adapter=adapter;
        viewModel.personId = personId
        viewModel.person.observe(this, Observer { updateUiWithPerson(it)})
        viewModel.vaccinations.observe(this, Observer { updateUiWithVaccinations(it)})


    }

    private fun updateUiWithVaccinations(it: List<Vaccination>?) {
        adapter.items= it!!
        adapter.notifyDataSetChanged()
    }

    private fun updateUiWithPerson(person: Person?) {
        if(person == null)return
        activity_person_details_name.text=person.name
        activity_person_details_birth_day.text = Utils.formatDate(person.birthDate)
    }
}

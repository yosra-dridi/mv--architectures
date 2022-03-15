package com.tp2.evax.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp2.evax.Adapater.PersonAdapter
import com.tp2.evax.ViewModel.PersonListViewModel
import com.tp2.evax.Data.Person
import com.tp2.evax.Commons.DependencyManager
import com.tp2.evax.R
import kotlinx.android.synthetic.main.person_list_activity.*

class PersonListActivity : AppCompatActivity() {

     private lateinit var  personListModelView: PersonListViewModel
    private lateinit var adapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_list_activity)
         personListModelView=
            ViewModelProviders.of(this,
                DependencyManager.modelViewFactory
            ).get(PersonListViewModel::class.java)
        adapter = PersonAdapter()
        adapter.personSelectListener={person -> personListModelView.onClickedPerson(this,person) }
        person_list_activity_recycler_view.layoutManager = LinearLayoutManager(this)
        person_list_activity_recycler_view.adapter = adapter
        person_list_activity_add.setOnClickListener {personListModelView.startAddingPerson(this)}
        personListModelView.getPerson().observe(this, Observer{updateUI(it)})

    }



    private fun updateUI(personList: List<Person>){
        adapter.items=personList
        adapter.notifyDataSetChanged()
    }
}

package com.tp2.evax.Adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp2.evax.Commons.Utils
import com.tp2.evax.Data.Person
import com.tp2.evax.R
import kotlinx.android.synthetic.main.person_list_item.view.*


class PersonrenAdapter : RecyclerView.Adapter<MVH>() {
      var items: List<Person> = ArrayList()
    lateinit var personSelectListener: ((Person)->Unit)
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MVH, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_list_item, parent, false)
        return MVH(view,personSelectListener)
    }
}

 class MVH( view: View, listener: (Person)->Unit) : RecyclerView.ViewHolder(view) {
    private val titleTv  = view.person_list_item_name
    private val birthDayTv = view.person_list_item_birth_day
    private lateinit var person:Person

    init {
        view.setOnClickListener {listener.invoke(person)}
    }
    fun bind(person: Person) {
        titleTv.text = person.name
        birthDayTv.text = Utils.formatDate(person.birthDate)
        this.person=person;

    }


}

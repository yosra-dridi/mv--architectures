package com.tp2.evax.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tp2.evax.Data.Repo


class MyModelViewFactory(private val repo: Repo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            PersonListViewModel::class.java -> PersonListViewModel(repo) as T
            AddPersonViewModel::class.java -> AddPersonViewModel(repo) as T
            PersonDetailViewModel::class.java -> PersonDetailViewModel(repo)as T
            else -> super.create(modelClass)
        }
    }
}
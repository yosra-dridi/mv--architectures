package com.tp2.evax.Commons

import android.app.Application
import com.tp2.evax.Data.Repo
import com.tp2.evax.Data.DB
import com.tp2.evax.ViewModel.MyModelViewFactory

object  DependencyManager {
     lateinit  var modelViewFactory : MyModelViewFactory

    fun initDependencies(application : Application){
        val database = DB.getInstance(application)
        Repo.init(database!!)
        val personRepo = Repo.getInstance()
        modelViewFactory =
            MyModelViewFactory(personRepo)
    }


}
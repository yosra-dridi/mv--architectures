package com.tp2.evax.Commons

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initRepoDependencies();
    }

    private fun initRepoDependencies() {
        DependencyManager.initDependencies(this)
    }

}
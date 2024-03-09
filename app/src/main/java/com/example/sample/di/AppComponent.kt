package com.example.sample.di

import androidx.lifecycle.ViewModel
import com.example.sample.network.APIClient
import com.example.sample.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIClient::class,ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun getMap():Map<Class<*>, ViewModel>
}
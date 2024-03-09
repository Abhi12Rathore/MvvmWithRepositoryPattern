package com.example.sample.di

import androidx.lifecycle.ViewModel
import com.example.sample.viewmodels.MainActivityViewModels
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(MainActivityViewModels::class)
    @IntoMap
    abstract  fun getMainViewModel(mainViewModels: MainActivityViewModels):ViewModel

}
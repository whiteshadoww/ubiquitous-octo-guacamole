package me.snowshadow.customerlogs.activities

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

}
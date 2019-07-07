package me.snowshadow.customerlogs.activities

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.snowshadow.customerlogs.fragments.CustomerLogInputFragment
import me.snowshadow.customerlogs.fragments.CustomersLogListFragment

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentsModule::class])
    fun mainActivity(): MainActivity

}

@Module
interface MainFragmentsModule {

    @ContributesAndroidInjector
    fun customerLogInput(): CustomerLogInputFragment

    @ContributesAndroidInjector
    fun cutomerLogList(): CustomersLogListFragment
}

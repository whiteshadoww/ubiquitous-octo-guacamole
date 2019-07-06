package me.snowshadow.customerlogs.utils.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.snowshadow.customerlogs.activities.MainViewModel
import javax.inject.Singleton

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindDashViewModel(dashViewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ModelFactory): ViewModelProvider.Factory

}
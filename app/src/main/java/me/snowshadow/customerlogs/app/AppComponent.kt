package me.snowshadow.customerlogs.app

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.snowshadow.customerlogs.activities.MainActivityModule
import me.snowshadow.customerlogs.utils.di.ViewModelModule
import javax.inject.Singleton


@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ViewModelModule::class,
            AppModule::class,
            MainActivityModule::class
        ]
)
@Singleton
interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(appContext: Context): Builder

        fun build(): AppComponent
    }

}



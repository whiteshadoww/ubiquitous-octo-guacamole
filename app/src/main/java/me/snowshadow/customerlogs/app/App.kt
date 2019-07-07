package me.snowshadow.customerlogs.app

import android.app.Activity
import android.app.Application
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        RxPaparazzo.register(this)
        AppInjector.inject(this)

    }

}

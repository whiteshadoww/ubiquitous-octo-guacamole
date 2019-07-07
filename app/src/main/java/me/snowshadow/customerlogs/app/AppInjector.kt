package me.snowshadow.customerlogs.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import me.snowshadow.customerlogs.utils.di.Injectable

object AppInjector {

    fun inject(app: App) {

        val da = DaggerAppComponent.builder()
            .applicationContext(app)
            .build()

        da.inject(app)

        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity?) {
            }

            override fun onActivityResumed(p0: Activity?) {
            }

            override fun onActivityStarted(p0: Activity?) {
            }

            override fun onActivityDestroyed(p0: Activity?) {
            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
            }

            override fun onActivityStopped(p0: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, p1: Bundle?) {

                if (activity is HasSupportFragmentInjector) {
                    dagger.android.AndroidInjection.inject(activity)
                }

                (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(
                            fm: FragmentManager, f: Fragment,
                            savedInstanceState: Bundle?
                        ) {
                            if (f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
                )
            }

        })
    }


}
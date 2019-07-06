package me.snowshadow.customerlogs.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragInjector

    override fun onBackPressed() {

//        if (!BackFragmentHelper.fireOnBackPressedEvent(this)) {
//            // lets do the default back action if fragments don't consume it
//            super.onBackPressed()
//        }

    }


}
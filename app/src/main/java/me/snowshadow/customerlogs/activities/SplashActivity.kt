package me.snowshadow.customerlogs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import me.snowshadow.customerlogs.R
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Observable.just(0)
            .delay(3, TimeUnit.SECONDS)
            .subscribe {
                startActivity(Intent(this, MainActivity::class.java))
                supportFinishAfterTransition()
            }
    }
}

package siarhei.luskanau.example.dagger

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import siarhei.luskanau.example.dagger.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class AppApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = supportFragmentInjector

    override fun activityInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}
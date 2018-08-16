package siarhei.luskanau.example.dagger

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import siarhei.luskanau.example.dagger.di.DaggerAppComponent
import dagger.android.support.HasAndroidxFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class AppApplication : Application(), HasActivityInjector, HasAndroidxFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var androidxFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun androidxFragmentInjector() = androidxFragmentInjector

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
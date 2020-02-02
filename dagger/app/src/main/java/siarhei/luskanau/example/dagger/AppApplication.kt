package siarhei.luskanau.example.dagger

import android.app.Application
import androidx.fragment.app.FragmentActivity
import siarhei.luskanau.example.dagger.di.AppComponent
import siarhei.luskanau.example.dagger.di.DaggerAppComponent
import siarhei.luskanau.example.dagger.feature1.di.DaggerFeature1Component
import timber.log.Timber

class AppApplication : Application() {

    private val commonComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        registerActivityLifecycleCallbacks(OnActivityCreatedLifecycleCallbacks {
            (it as? FragmentActivity?)?.let { fragmentActivity ->

                val fragmentFactory =
                    DelegateFragmentFactory(
                        listOf {
                            DaggerFeature1Component.builder()
                                .bindDateService(commonComponent.provideDateService().get())
                                .build()
                                .provideFragmentFactory()
                                .get()
                        }
                    )

                fragmentActivity.supportFragmentManager.fragmentFactory = fragmentFactory
            }

            (it as? AppActivity?)?.let { activity ->
                activity.commonHelloService = commonComponent.provideCommonHelloService().get()
            }
        })
    }
}

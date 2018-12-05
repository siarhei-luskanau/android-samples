package siarhei.luskanau.example.dagger.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import siarhei.luskanau.example.dagger.AppApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BuildersModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AppApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: AppApplication)
}
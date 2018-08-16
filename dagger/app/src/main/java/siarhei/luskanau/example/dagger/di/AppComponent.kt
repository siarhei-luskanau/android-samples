package siarhei.luskanau.example.dagger.di

import dagger.BindsInstance
import dagger.Component
import siarhei.luskanau.example.dagger.AppApplication
import dagger.android.support.AndroidxInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidxInjectionModule::class,
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
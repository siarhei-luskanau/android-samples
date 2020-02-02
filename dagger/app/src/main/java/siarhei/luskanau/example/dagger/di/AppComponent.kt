package siarhei.luskanau.example.dagger.di

import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton
import siarhei.luskanau.example.dagger.AppApplication
import siarhei.luskanau.example.dagger.model.CommonHelloService
import siarhei.luskanau.example.dagger.model.DateService

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {

    fun provideCommonHelloService(): Provider<CommonHelloService>

    fun provideDateService(): Provider<DateService>

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AppApplication): Builder

        fun build(): AppComponent
    }
}

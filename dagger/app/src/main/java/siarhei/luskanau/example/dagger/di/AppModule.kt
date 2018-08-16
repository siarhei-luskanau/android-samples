package siarhei.luskanau.example.dagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import siarhei.luskanau.example.dagger.AppApplication
import siarhei.luskanau.example.dagger.model.CommonHelloService
import siarhei.luskanau.example.dagger.model.DateService
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: AppApplication): Context =
            application.applicationContext

    @Singleton
    @Provides
    fun provideCommonHelloService(): CommonHelloService =
            CommonHelloService()

    @Provides
    fun provideDateService(): DateService =
            DateService()

}
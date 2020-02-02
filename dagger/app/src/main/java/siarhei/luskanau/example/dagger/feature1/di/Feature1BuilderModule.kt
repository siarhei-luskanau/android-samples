package siarhei.luskanau.example.dagger.feature1.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import siarhei.luskanau.example.dagger.di.DaggerFragmentFactory
import siarhei.luskanau.example.dagger.feature1.Feature1Fragment
import siarhei.luskanau.example.dagger.model.DateService

@Module
class Feature1BuilderModule {

    @Provides
    fun providesFragmentFactory(
        providers: MutableMap<Class<out Fragment>, Provider<Fragment>>
    ): FragmentFactory = DaggerFragmentFactory(
        providers
    )

    @Provides
    fun provideFeature1Fragment(
        dateService: DateService
    ) = Feature1Fragment(
        dateService = dateService
    )
}

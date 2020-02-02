package siarhei.luskanau.example.dagger.feature1.di

import androidx.fragment.app.FragmentFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider
import siarhei.luskanau.example.dagger.model.DateService

@Component(
    modules = [
        Feature1BinderModule::class,
        Feature1BuilderModule::class
    ]
)
interface Feature1Component {

    fun provideFragmentFactory(): Provider<FragmentFactory>

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindDateService(dateService: DateService): Builder

        fun build(): Feature1Component
    }
}

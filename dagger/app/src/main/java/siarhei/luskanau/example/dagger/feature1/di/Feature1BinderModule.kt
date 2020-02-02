package siarhei.luskanau.example.dagger.feature1.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import siarhei.luskanau.example.dagger.di.FragmentKey
import siarhei.luskanau.example.dagger.feature1.Feature1Fragment

@Module
interface Feature1BinderModule {

    @Binds
    @IntoMap
    @FragmentKey(Feature1Fragment::class)
    fun bindFeature1Fragment(fragment: Feature1Fragment): Fragment
}

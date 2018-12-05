package siarhei.luskanau.example.dagger.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import siarhei.luskanau.example.dagger.feature1.Feature1Activity
import siarhei.luskanau.example.dagger.feature1.Feature1ActivityModule
import siarhei.luskanau.example.dagger.feature1.Feature1Fragment
import siarhei.luskanau.example.dagger.feature1.Feature1FragmentModule

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [Feature1ActivityModule::class])
    abstract fun bindFeature1Activity(): Feature1Activity

    @ContributesAndroidInjector(modules = [Feature1FragmentModule::class, Feature1ActivityModule::class])
    abstract fun bindFeature1Fragment(): Feature1Fragment
}
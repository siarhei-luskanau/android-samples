package siarhei.luskanau.example.dagger.module

import dagger.Module
import dagger.Provides
import siarhei.luskanau.example.dagger.model.ModelA
import siarhei.luskanau.example.dagger.scope.ScopeA

@Module
class ModuleA {

    @Provides
    @ScopeA
    internal fun provideModelA(): ModelA {
        return ModelA()
    }
}

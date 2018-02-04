package siarhei.luskanau.example.dagger.module

import dagger.Module
import dagger.Provides
import siarhei.luskanau.example.dagger.model.ModelA
import siarhei.luskanau.example.dagger.model.ModelB
import siarhei.luskanau.example.dagger.scope.ScopeB

@Module
class ModuleB {

    @Provides
    @ScopeB
    internal fun provideModelB(modelA: ModelA): ModelB {
        return ModelB(modelA)
    }
}

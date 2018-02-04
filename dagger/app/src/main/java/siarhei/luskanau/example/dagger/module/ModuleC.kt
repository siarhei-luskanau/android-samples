package siarhei.luskanau.example.dagger.module

import dagger.Module
import dagger.Provides
import siarhei.luskanau.example.dagger.model.ModelA
import siarhei.luskanau.example.dagger.model.ModelB
import siarhei.luskanau.example.dagger.model.ModelC
import siarhei.luskanau.example.dagger.scope.ScopeC

@Module
class ModuleC {

    @Provides
    @ScopeC
    internal fun provideModelC(modelA: ModelA, modelB: ModelB): ModelC {
        return ModelC(modelA, modelB)
    }
}

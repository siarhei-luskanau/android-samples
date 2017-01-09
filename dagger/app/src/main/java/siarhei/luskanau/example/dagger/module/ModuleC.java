package siarhei.luskanau.example.dagger.module;

import dagger.Module;
import dagger.Provides;
import siarhei.luskanau.example.dagger.model.ModelA;
import siarhei.luskanau.example.dagger.model.ModelB;
import siarhei.luskanau.example.dagger.model.ModelC;
import siarhei.luskanau.example.dagger.scope.ScopeC;

@Module
public class ModuleC {

    @Provides
    @ScopeC
    ModelC provideModelC(ModelA modelA, ModelB modelB) {
        return new ModelC();
    }
}

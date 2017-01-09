package siarhei.luskanau.example.dagger.module;

import dagger.Module;
import dagger.Provides;
import siarhei.luskanau.example.dagger.model.ModelA;
import siarhei.luskanau.example.dagger.scope.ScopeA;

@Module
public class ModuleA {

    @Provides
    @ScopeA
    ModelA provideModelA() {
        return new ModelA();
    }
}

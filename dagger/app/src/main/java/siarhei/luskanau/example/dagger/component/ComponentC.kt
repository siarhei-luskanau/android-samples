package siarhei.luskanau.example.dagger.component

import dagger.Component
import siarhei.luskanau.example.dagger.module.ModuleC
import siarhei.luskanau.example.dagger.scope.ScopeC
import siarhei.luskanau.example.dagger.target.TargetC

@Component(dependencies = [ComponentB::class], modules = [ModuleC::class])
@ScopeC
interface ComponentC {

    fun inject(targetC: TargetC)
}

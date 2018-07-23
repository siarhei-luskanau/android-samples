package siarhei.luskanau.example.dagger.component

import dagger.Component
import siarhei.luskanau.example.dagger.model.ModelA
import siarhei.luskanau.example.dagger.model.ModelB
import siarhei.luskanau.example.dagger.module.ModuleB
import siarhei.luskanau.example.dagger.scope.ScopeB
import siarhei.luskanau.example.dagger.target.TargetB

@Component(dependencies = [ComponentA::class], modules = [ModuleB::class])
@ScopeB
interface ComponentB {

    fun inject(targetB: TargetB)

    fun modelA(): ModelA

    fun modelB(): ModelB
}

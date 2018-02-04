package siarhei.luskanau.example.dagger.target

import javax.inject.Inject

import siarhei.luskanau.example.dagger.model.ModelB

class TargetB {

    @field:Inject
    lateinit var modelB: ModelB
}

package siarhei.luskanau.example.dagger.target

import javax.inject.Inject

import siarhei.luskanau.example.dagger.model.ModelC

class TargetC {

    @field:Inject
    lateinit var modelC: ModelC
}

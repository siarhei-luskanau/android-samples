package siarhei.luskanau.example.dagger.target

import siarhei.luskanau.example.dagger.model.ModelC
import javax.inject.Inject

class TargetC {

    @Inject
    lateinit var modelC: ModelC
}

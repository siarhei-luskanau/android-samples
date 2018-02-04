package siarhei.luskanau.example.dagger.target

import siarhei.luskanau.example.dagger.model.ModelB
import javax.inject.Inject

class TargetB {

    @Inject
    lateinit var modelB: ModelB
}

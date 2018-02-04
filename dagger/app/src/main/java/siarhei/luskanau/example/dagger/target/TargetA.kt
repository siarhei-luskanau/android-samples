package siarhei.luskanau.example.dagger.target

import siarhei.luskanau.example.dagger.model.ModelA
import javax.inject.Inject

class TargetA {

    @Inject
    lateinit var modelA: ModelA
}

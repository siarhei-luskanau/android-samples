package siarhei.luskanau.example.dagger.model

import timber.log.Timber

class ModelC(modelA: ModelA, modelB: ModelB) {
    init {
        Timber.d(
                "Constructor C %s ModelA %s ModelB %s",
                this.hashCode(),
                modelA.hashCode(),
                modelB.hashCode()
        )
    }
}

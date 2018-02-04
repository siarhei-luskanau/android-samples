package siarhei.luskanau.example.dagger.model

import timber.log.Timber

class ModelB(modelA: ModelA) {
    init {
        Timber.d(
                "Constructor B %s ModelA %s",
                this.hashCode(),
                modelA.hashCode()
        )
    }
}

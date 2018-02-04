package siarhei.luskanau.example.dagger.model

import timber.log.Timber

class ModelA {
    init {
        Timber.d("Constructor %s", this.hashCode())
    }
}

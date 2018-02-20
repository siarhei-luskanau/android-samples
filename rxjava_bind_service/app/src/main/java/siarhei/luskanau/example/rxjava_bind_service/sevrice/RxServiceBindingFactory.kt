package siarhei.luskanau.example.rxjava_bind_service.sevrice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import timber.log.Timber

class RxServiceBindingFactory {

    companion object {
        fun <B : Binder> bind(context: Context, launch: Intent, flags: Int): Single<Pair<B, ServiceConnection>> {
            val con = Connection<B>()
            return Single.create(con)
                    .doOnSubscribe {
                        Timber.d("doOnSubscribe and bindService")
                        context.bindService(launch, con, flags)
                    }
        }
    }

    private class Connection<B : Binder> : ServiceConnection, SingleOnSubscribe<Pair<B, ServiceConnection>> {

        private var emitter: SingleEmitter<Pair<B, ServiceConnection>>? = null
        private var service: B? = null

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Timber.d("onServiceConnected")
            @Suppress("UNCHECKED_CAST")
            this.service = service as B
            emmitIfNotNull()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Timber.d("onServiceDisconnected")
        }

        override fun subscribe(emitter: SingleEmitter<Pair<B, ServiceConnection>>) {
            Timber.d("subscribe")
            this.emitter = emitter
            emmitIfNotNull()
        }

        private fun emmitIfNotNull() {
            emitter?.let { emitter ->
                service?.let { service ->
                    Timber.d("emmit")
                    emitter.onSuccess(Pair(service, this))
                }
            }
        }
    }

}
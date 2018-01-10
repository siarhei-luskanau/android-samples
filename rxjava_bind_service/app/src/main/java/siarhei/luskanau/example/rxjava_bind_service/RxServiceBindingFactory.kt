package siarhei.luskanau.example.rxjava_bind_service

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
        fun <B : Binder> bind(context: Context, launch: Intent, flags: Int): Single<B> {
            val con = Connection<B>()
            return Single.create(con)
                    .doOnSubscribe {
                        Timber.d("doOnSubscribe and bindService")
                        context.bindService(launch, con, flags)
                    }
                    .doFinally {
                        Timber.d("doFinally and unbindService")
                        context.unbindService(con)
                    }
                    .doOnDispose { Timber.d("doOnDispose") }
                    .doOnSuccess { Timber.d("doOnSuccess") }
                    .doOnError { Timber.d("doOnError") }
                    .doAfterTerminate { Timber.d("doAfterTerminate") }
                    .doAfterSuccess { Timber.d("doAfterSuccess") }
        }
    }

    private class Connection<B : Binder> : ServiceConnection, SingleOnSubscribe<B> {

        private var emitter: SingleEmitter<in B>? = null

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Timber.d("onServiceConnected")
            emitter?.onSuccess(service as B)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Timber.d("onServiceDisconnected")
        }

        override fun subscribe(emitter: SingleEmitter<B>) {
            this.emitter = emitter
        }
    }
}
package siarhei.luskanau.example.rxjavabindservice.api

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import siarhei.luskanau.example.rxjavabindservice.service.BackgroundService
import siarhei.luskanau.example.rxjavabindservice.service.RxServiceBindingFactory
import timber.log.Timber

class BindApiRepository(private val context: Context) : ApiRepository {

    override fun startCountdown(): Completable = bindService()
            .flatMapCompletable { pair: Pair<ApiRepository, ServiceConnection> ->
                pair.first
                        .startCountdown()
                        .doFinally {
                            Timber.d("doFinally and unbindService")
                            context.unbindService(pair.second)
                        }
            }

    override fun watchCountdown(): Observable<Int> = bindService()
            .flatMapObservable { pair: Pair<ApiRepository, ServiceConnection> ->
                pair.first
                        .watchCountdown()
                        .doFinally {
                            Timber.d("doFinally and unbindService")
                            context.unbindService(pair.second)
                        }
            }

    private fun bindService(): Single<Pair<ApiRepository, ServiceConnection>> =
            RxServiceBindingFactory.bind<BackgroundService.ServiceBinder>(
                    context,
                    Intent(context, BackgroundService::class.java),
                    Context.BIND_AUTO_CREATE
            )
                    .map { pair: Pair<BackgroundService.ServiceBinder, ServiceConnection> ->
                        Pair(pair.first.getApiRepository(), pair.second)
                    }
}
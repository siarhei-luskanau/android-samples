package siarhei.luskanau.example.rxjava_bind_service.api

import io.reactivex.Completable
import io.reactivex.Observable

interface ApiRepository {

    fun startCountdown(): Completable

    fun watchCountdown(): Observable<Int>

}
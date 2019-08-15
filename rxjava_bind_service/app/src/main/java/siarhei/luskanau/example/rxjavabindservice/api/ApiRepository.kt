package siarhei.luskanau.example.rxjavabindservice.api

import io.reactivex.Completable
import io.reactivex.Observable

interface ApiRepository {

    fun startCountdown(): Completable

    fun watchCountdown(): Observable<Int>
}

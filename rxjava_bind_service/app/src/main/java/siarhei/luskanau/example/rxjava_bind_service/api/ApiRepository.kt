package siarhei.luskanau.example.rxjava_bind_service.api

import io.reactivex.Observable

interface ApiRepository {

    fun getStrings(): Observable<String>

}
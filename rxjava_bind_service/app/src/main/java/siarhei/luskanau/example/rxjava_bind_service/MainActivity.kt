package siarhei.luskanau.example.rxjava_bind_service

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import siarhei.luskanau.example.rxjava_bind_service.api.ApiRepository
import siarhei.luskanau.example.rxjava_bind_service.api.BindApiRepository
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val apiRepository: ApiRepository = BindApiRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button?>(R.id.test_call_button)
                ?.setOnClickListener { testCall() }
    }

    private fun testCall() {
        apiRepository.getStrings()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onNextString: String -> Timber.d(onNextString) },
                        { t: Throwable? -> Timber.e(t) },
                        { Timber.d("onComplete") }
                )
    }

}

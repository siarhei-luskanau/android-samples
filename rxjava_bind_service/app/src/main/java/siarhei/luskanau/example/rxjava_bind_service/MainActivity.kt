package siarhei.luskanau.example.rxjava_bind_service

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button?>(R.id.test_call_button)
                ?.setOnClickListener { testCall() }
    }

    private fun testCall() {
        BackgroundService.bindToBackgroundRepository(this)
                .flatMapObservable { backgroundRepository -> backgroundRepository.getStrings() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onNextString: String -> Timber.d(onNextString) },
                        { t: Throwable? -> Timber.e(t) },
                        { Timber.d("onComplete") }
                )
    }

}

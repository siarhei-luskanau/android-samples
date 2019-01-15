package siarhei.luskanau.example.rxjavabindservice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import siarhei.luskanau.example.rxjavabindservice.api.ApiRepository
import siarhei.luskanau.example.rxjavabindservice.api.BindApiRepository
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var countdownValueTextView: TextView
    private val apiRepository: ApiRepository = BindApiRepository(this)
    private var watchCountdownDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        countdownValueTextView = findViewById(R.id.countdown_value)

        findViewById<Button?>(R.id.start_countdown_button)?.setOnClickListener {
            startCountdown()
        }

        findViewById<Button?>(R.id.watch_countdown_button)?.setOnClickListener {
            watchCountdown()
        }

        findViewById<Button?>(R.id.unwatch_countdown_button)?.setOnClickListener {
            unwatchCountdown()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unwatchCountdown()
    }

    private fun startCountdown() =
            apiRepository.startCountdown()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onComplete = { Timber.d("onComplete") },
                            onError = { t: Throwable? -> Timber.e(t) }
                    )

    private fun watchCountdown() {
        unwatchCountdown()
        watchCountdownDisposable = apiRepository.watchCountdown()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { countdown: Int ->
                            val countdownValue = "Countdown: $countdown"
                            Timber.d(countdownValue)
                            countdownValueTextView.text = countdownValue
                        },
                        onError = { t: Throwable? -> Timber.e(t) },
                        onComplete = { Timber.d("onComplete") }
                )
    }

    private fun unwatchCountdown() {
        watchCountdownDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        watchCountdownDisposable = null
    }
}

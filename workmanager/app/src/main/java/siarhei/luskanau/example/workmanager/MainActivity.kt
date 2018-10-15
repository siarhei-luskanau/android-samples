package siarhei.luskanau.example.workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.WorkManager
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by lazy { ViewModelProviders.of(this).get(AppViewModel::class.java) }

    private var disposable: Disposable? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        beginUniqueWorkButton.setOnClickListener { appViewModel.beginUniqueWork() }

        cancelUniqueWorkButton.setOnClickListener { appViewModel.cancelUniqueWork() }

        appViewModel.workStatusListLiveData?.observe(this, Observer { it ->

            outputDataTextView.text = "LiveData: outputData:\n" + it.orEmpty()
                    .map {
                        "\n${it.outputData.keyValueMap}\n"
                    }

            workStatusTextView.text = "LiveData: WorkStatus:\n" + it.orEmpty()
                    .map {
                        "\n${it.state} ${it.id} ${it.getTags()}\n"
                    }
        })
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        disposable = Flowable
                .interval(0, 300, TimeUnit.MILLISECONDS)
                .map {
                    WorkManager.getInstance().getStatusesForUniqueWork(AppViewModel.TAG_OUTPUT).get()
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { it ->
                            outputDataTextView.text = "Sync: outputData:\n" + it
                                    .map {
                                        "\n${it.outputData.keyValueMap}\n"
                                    }

                            workStatusTextView.text = "Sync: WorkStatus:\n" + it
                                    .map {
                                        "\n${it.state} ${it.id} ${it.tags}\n"
                                    }

                        },
                        onError = { Timber.e(it) }
                )
    }

    override fun onPause() {
        super.onPause()

        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        disposable = null
    }

}
package siarhei.luskanau.example.workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.WorkManager
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val appViewModel: AppViewModel by lazy { ViewModelProviders.of(this).get(AppViewModel::class.java) }

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

        Flowable
                .interval(0, 300, TimeUnit.MILLISECONDS)
                .map {
                    WorkManager.getInstance().synchronous().getStatusesForUniqueWorkSync(AppViewModel.TAG_OUTPUT)
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->
                            outputDataTextView.text = "Sync: outputData:\n" + it
                                    .map {
                                        "\n${it.outputData.keyValueMap}\n"
                                    }

                            workStatusTextView.text = "Sync: WorkStatus:\n" + it
                                    .map {
                                        "\n${it.state} ${it.id} ${it.tags}\n"
                                    }

                        },
                        { Timber.e(it) }
                )
    }

}
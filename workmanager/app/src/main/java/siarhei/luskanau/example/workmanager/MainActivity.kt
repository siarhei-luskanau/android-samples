package siarhei.luskanau.example.workmanager

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val appViewModel: AppViewModel by lazy { ViewModelProviders.of(this).get(AppViewModel::class.java) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        beginUniqueWorkButton.setOnClickListener { appViewModel.beginUniqueWork() }

        cancelUniqueWorkButton.setOnClickListener { appViewModel.cancelUniqueWork() }

        appViewModel.workStatusListLiveData?.observe(this, Observer {

            outputDataTextView.text = "outputData:\n" + it.orEmpty()
                    .map {
                        "\n${it.outputData.keyValueMap}\n"
                    }

            workStatusTextView.text = "WorkStatus:\n" + it.orEmpty()
                    .map {
                        "\n${it.state} ${it.id}\n"
                    }
        })
    }

}
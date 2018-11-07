package siarhei.luskanau.example.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val appViewModel by lazy { ViewModelProviders.of(this).get(AppViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        beginUniqueWorkButton.setOnClickListener { appViewModel.beginUniqueWork() }

        cancelUniqueWorkButton.setOnClickListener { appViewModel.cancelUniqueWork() }
    }
}
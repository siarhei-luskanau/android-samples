package siarhei.luskanau.example.fonts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import siarhei.luskanau.example.fonts.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAppBinding = DataBindingUtil.setContentView(this, R.layout.activity_app)
    }
}
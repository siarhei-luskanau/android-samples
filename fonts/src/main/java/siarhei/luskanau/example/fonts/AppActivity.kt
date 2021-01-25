package siarhei.luskanau.example.fonts

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import siarhei.luskanau.example.fonts.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityAppBinding.inflate(LayoutInflater.from(this))
            .also { binding ->
                setContentView(binding.root)
            }
    }
}

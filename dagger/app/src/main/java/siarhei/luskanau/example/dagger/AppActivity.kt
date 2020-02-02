package siarhei.luskanau.example.dagger

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import siarhei.luskanau.example.dagger.databinding.ActivityFeature1Binding
import siarhei.luskanau.example.dagger.feature1.Feature1Fragment
import siarhei.luskanau.example.dagger.model.CommonHelloService

class AppActivity : AppCompatActivity() {

    lateinit var commonHelloService: CommonHelloService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityFeature1Binding.inflate(LayoutInflater.from(this))
            .also { binding ->
                setContentView(binding.root)
                binding.textView.text = commonHelloService.sayHello()
            }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    supportFragmentManager.fragmentFactory.instantiate(
                        classLoader,
                        Feature1Fragment::class.java.name
                    )
                )
                .commit()
        }
    }
}

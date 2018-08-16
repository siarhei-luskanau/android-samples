package siarhei.luskanau.example.dagger.feature1

import android.os.Bundle
import siarhei.luskanau.example.dagger.R
import siarhei.luskanau.example.dagger.base.BindingActivity
import siarhei.luskanau.example.dagger.databinding.ActivityFeature1Binding
import siarhei.luskanau.example.dagger.model.CommonHelloService
import javax.inject.Inject

class Feature1Activity : BindingActivity<ActivityFeature1Binding>() {

    @Inject
    lateinit var commonHelloService: CommonHelloService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.textView.text = commonHelloService.sayHello()

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, Feature1Fragment())
                    .commit()
        }
    }

    override fun getViewLayout() =
            R.layout.activity_feature1

}

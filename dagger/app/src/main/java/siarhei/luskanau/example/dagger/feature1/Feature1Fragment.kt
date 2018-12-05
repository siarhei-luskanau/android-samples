package siarhei.luskanau.example.dagger.feature1

import siarhei.luskanau.example.dagger.R
import siarhei.luskanau.example.dagger.base.BindingFragment
import siarhei.luskanau.example.dagger.databinding.FragmentFeature1Binding
import siarhei.luskanau.example.dagger.model.DateService
import javax.inject.Inject

class Feature1Fragment : BindingFragment<FragmentFeature1Binding>() {

    @Inject
    lateinit var dateService: DateService

    override fun onResume() {
        super.onResume()

        binding.textView.text = dateService.sayDate()
    }

    override fun getViewLayout(): Int =
            R.layout.fragment_feature1
}
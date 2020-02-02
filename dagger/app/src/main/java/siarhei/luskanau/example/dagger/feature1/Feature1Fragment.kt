package siarhei.luskanau.example.dagger.feature1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import javax.inject.Inject
import siarhei.luskanau.example.dagger.databinding.FragmentFeature1Binding
import siarhei.luskanau.example.dagger.model.DateService

class Feature1Fragment @Inject constructor(
    private val dateService: DateService
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentFeature1Binding.inflate(inflater, container, false)
            .also { binding ->
                binding.textView.text = dateService.sayDate()
            }
            .root
}

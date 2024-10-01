package siarhei.luskanau.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import siarhei.luskanau.example.navigation.databinding.FragmentFeature2ListBinding

class Feature2ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeature2ListBinding.inflate(inflater, container, false)

        binding.feature2DetailListItem.setOnClickListener {
            val direction =
                Feature2ListFragmentDirections
                    .actionFeature2ListFragmentToFeature2DetailFragment("feature2Id")
            it.findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = javaClass.simpleName
    }
}

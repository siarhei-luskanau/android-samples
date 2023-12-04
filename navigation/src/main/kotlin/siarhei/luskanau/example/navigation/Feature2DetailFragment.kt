package siarhei.luskanau.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import siarhei.luskanau.example.navigation.databinding.FragmentFeature2DetailBinding

class Feature2DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentFeature2DetailBinding.inflate(inflater, container, false)

        val args = Feature2DetailFragmentArgs.fromBundle(arguments ?: Bundle())

        binding.feature2Details.text = args.feature2Id

        binding.feature2Details.setOnClickListener {
            val direction = Feature2DetailFragmentDirections.actionFeature2ListToFeature2Detail()
            it.findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = javaClass.simpleName
    }
}

package siarhei.luskanau.example.workmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import siarhei.luskanau.example.workmanager.databinding.FragmentBeginCancelWorkBinding

abstract class BaseBeginCancelWorkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBeginCancelWorkBinding.inflate(inflater, container, false)

        binding.beginWorkButton.setOnClickListener { onBeginButtonPressed() }

        binding.cancelWorkButton.setOnClickListener { onCancelButtonPressed() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = javaClass.simpleName
    }

    abstract fun onBeginButtonPressed()

    abstract fun onCancelButtonPressed()
}

package siarhei.luskanau.example.workmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.work.WorkManager
import siarhei.luskanau.example.workmanager.databinding.FragmentWorkManagerActionsBinding

class WorkManagerActionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWorkManagerActionsBinding.inflate(inflater, container, false)

        binding.cancelAllButton.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelAllWork()
        }

        binding.pruneButton.setOnClickListener {
            WorkManager.getInstance(requireContext()).pruneWork()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = javaClass.simpleName
    }
}

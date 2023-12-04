package siarhei.luskanau.example.workmanager.monitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import siarhei.luskanau.example.workmanager.databinding.FragmentWorkManagerMonitorBinding

class WorkManagerMonitorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentWorkManagerMonitorBinding.inflate(inflater, container, false)

        val adapter = WorkInfoAdapter()
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                VERTICAL,
            ),
        )

        val workManagerMonitorViewModel =
            ViewModelProvider(this).get(WorkManagerMonitorViewModel::class.java)
        workManagerMonitorViewModel.getWorkStatusListLiveData(requireContext())
            .observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        return binding.root
    }
}

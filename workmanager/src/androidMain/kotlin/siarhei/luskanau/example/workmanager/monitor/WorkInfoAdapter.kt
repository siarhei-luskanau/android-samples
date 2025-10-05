package siarhei.luskanau.example.workmanager.monitor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkInfo
import siarhei.luskanau.example.workmanager.databinding.ListItemWorkStatusBinding

class WorkInfoAdapter : ListAdapter<WorkInfo, WorkInfoAdapter.ViewHolder>(WorkInfoDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemWorkStatusBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    class ViewHolder(private val binding: ListItemWorkStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: WorkInfo) {
            binding.workInfoTextView.text =
                "${item.id}\n ${item.state} ${item.tags}\n${item.outputData.keyValueMap}"
            itemView.tag = item
        }
    }
}

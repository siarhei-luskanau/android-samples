package siarhei.luskanau.example.workmanager.monitor

import androidx.recyclerview.widget.DiffUtil
import androidx.work.WorkInfo

class WorkInfoDiffCallback : DiffUtil.ItemCallback<WorkInfo>() {
    override fun areItemsTheSame(oldItem: WorkInfo, newItem: WorkInfo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WorkInfo, newItem: WorkInfo): Boolean =
        oldItem == newItem
}

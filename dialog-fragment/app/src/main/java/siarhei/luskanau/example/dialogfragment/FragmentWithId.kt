package siarhei.luskanau.example.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentWithId : Fragment(), OnPositiveClickListener, OnNegativeClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_with_id, container, false).also {
                it.findViewById<View>(R.id.fragmentWithIdDialogButton).setOnClickListener {
                    AppDialogFragment.Builder(
                            dialogId = "FragmentWithId_dialogId",
                            title = "FragmentWithId",
                            message = "Dialog from FragmentWithId"
                    )
                            .setPositiveButton(android.R.string.ok, this)
                            .setNegativeButton(android.R.string.no, this)
                            .show(this)
                }
            }

    override fun onClickPositive(dialogId: String) {
        Toast.makeText(requireContext(), "FragmentWithId.onClickPositive $dialogId", Toast.LENGTH_SHORT).show()
    }

    override fun onClickNegative(dialogId: String) {
        Toast.makeText(requireContext(), "FragmentWithId.onClickNegative $dialogId", Toast.LENGTH_SHORT).show()
    }
}

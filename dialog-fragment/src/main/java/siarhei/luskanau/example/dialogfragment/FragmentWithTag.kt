package siarhei.luskanau.example.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import siarhei.luskanau.example.dialogfragment.databinding.FragmentWithTagBinding

class FragmentWithTag : Fragment(), OnPositiveClickListener, OnNegativeClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? =
        FragmentWithTagBinding.inflate(inflater, container, false)
            .also { binding ->
                binding.fragmentWithTagDialogButton.setOnClickListener {
                    AppDialogFragment.Builder(
                        dialogId = "FragmentWithTag_dialogId",
                        title = "FragmentWithTag",
                        message = "Dialog from FragmentWithTag",
                    )
                        .setPositiveButton(android.R.string.ok, this)
                        .setNegativeButton(android.R.string.cancel, this)
                        .show(this)
                }
            }
            .root

    override fun onClickPositive(dialogId: String) {
        Toast.makeText(
            requireContext(),
            "FragmentWithTag.onClickPositive $dialogId",
            Toast.LENGTH_SHORT,
        ).show()
    }

    override fun onClickNegative(dialogId: String) {
        Toast.makeText(
            requireContext(),
            "FragmentWithTag.onClickNegative $dialogId",
            Toast.LENGTH_SHORT,
        ).show()
    }
}

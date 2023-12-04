package siarhei.luskanau.example.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import siarhei.luskanau.example.dialogfragment.databinding.ActivityAppBinding

private const val TAG = "FragmentWithTag"

class AppActivity : AppCompatActivity(), OnPositiveClickListener, OnNegativeClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityAppBinding.inflate(LayoutInflater.from(this))
            .also { binding ->
                setContentView(binding.root)

                binding.activityDialogButton.setOnClickListener {
                    AppDialogFragment.Builder(
                        dialogId = "AppActivity_dialogId",
                        title = "AppActivity",
                        message = "Dialog from AppActivity",
                    )
                        .setPositiveButton(android.R.string.ok, this)
                        .setNegativeButton(android.R.string.cancel, this)
                        .show(this)
                }
            }

        if (supportFragmentManager.findFragmentByTag(TAG) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, FragmentWithTag(), TAG)
                .commit()
        }
    }

    override fun onClickPositive(dialogId: String) {
        Toast.makeText(this, "AppActivity.onClickPositive $dialogId", Toast.LENGTH_SHORT).show()
    }

    override fun onClickNegative(dialogId: String) {
        Toast.makeText(this, "AppActivity.onClickNegative $dialogId", Toast.LENGTH_SHORT).show()
    }
}

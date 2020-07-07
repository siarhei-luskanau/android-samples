package siarhei.luskanau.example.dialogfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

private const val BASIC_DIALOG_FRAGMENT_TAG = "AppDialogFragment_"
private const val ARG_DIALOG_ID = "dialog_id"
private const val ARG_TITLE = "title"
private const val ARG_MESSAGE = "message"
private const val ARG_POSITIVE_BUTTON_TEXT = "positive_button_text"
private const val ARG_NEGATIVE_BUTTON_TEXT = "negative_button_text"
private const val ARG_POSITIVE_LISTENER_FRAGMENT_TAG = "positive_listener_fragment_tag"
private const val ARG_POSITIVE_LISTENER_FRAGMENT_ID = "positive_listener_fragment_id"
private const val ARG_POSITIVE_LISTENER_ACTIVITY_NAME = "positive_listener_activity_name"
private const val ARG_NEGATIVE_LISTENER_FRAGMENT_TAG = "negative_listener_fragment_tag"
private const val ARG_NEGATIVE_LISTENER_FRAGMENT_ID = "negative_listener_fragment_id"
private const val ARG_NEGATIVE_LISTENER_ACTIVITY_NAME = "negative_listener_activity_name"

@Suppress("TooManyFunctions")
class AppDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val args = arguments ?: Bundle()
        setTitle(builder, args)
        setMessage(builder, args)
        setPositiveButton(builder, args)
        setNegativeButton(builder, args)
        return builder.create()
    }

    private fun getDialogId(): String =
        arguments?.getString(ARG_DIALOG_ID).orEmpty()

    private fun setTitle(builder: AlertDialog.Builder, args: Bundle) {
        val title = args.getCharSequence(ARG_TITLE)
        if (title.isNullOrEmpty().not()) {
            builder.setTitle(title)
        }
    }

    private fun setMessage(builder: AlertDialog.Builder, args: Bundle) {
        val message = args.getCharSequence(ARG_MESSAGE)
        if (message.isNullOrEmpty().not()) {
            builder.setMessage(message)
        }
    }

    private fun setPositiveButton(builder: AlertDialog.Builder, args: Bundle) {
        val positiveButtonText = args.getCharSequence(ARG_POSITIVE_BUTTON_TEXT)
        if (positiveButtonText.isNullOrEmpty().not()) {
            builder.setPositiveButton(positiveButtonText) { _, _ ->
                val fragmentTag: String? = arguments?.getString(ARG_POSITIVE_LISTENER_FRAGMENT_TAG)
                val fragmentId: Int =
                    arguments?.getInt(ARG_POSITIVE_LISTENER_FRAGMENT_ID) ?: View.NO_ID
                val activityName: String? =
                    arguments?.getString(ARG_POSITIVE_LISTENER_ACTIVITY_NAME)
                findListener(
                    OnPositiveClickListener::class.java,
                    fragmentTag,
                    fragmentId,
                    activityName
                )?.onClickPositive(getDialogId())
            }
        }
    }

    private fun setNegativeButton(builder: AlertDialog.Builder, args: Bundle) {
        val negativeButtonText = args.getCharSequence(ARG_NEGATIVE_BUTTON_TEXT)
        if (negativeButtonText.isNullOrEmpty().not()) {
            builder.setNegativeButton(negativeButtonText) { _, _ ->
                val fragmentTag: String? = arguments?.getString(ARG_NEGATIVE_LISTENER_FRAGMENT_TAG)
                val fragmentId: Int =
                    arguments?.getInt(ARG_NEGATIVE_LISTENER_FRAGMENT_ID) ?: View.NO_ID
                val activityName: String? =
                    arguments?.getString(ARG_NEGATIVE_LISTENER_ACTIVITY_NAME)
                findListener(
                    OnNegativeClickListener::class.java,
                    fragmentTag,
                    fragmentId,
                    activityName
                )?.onClickNegative(getDialogId())
            }
        }
    }

    private fun <T> findListener(
        clazz: Class<T>,
        fragmentTag: String?,
        fragmentId: Int,
        activityName: String?
    ): T? {
        return when {
            fragmentTag.isNullOrEmpty().not() -> {
                val fragment =
                    requireActivity().supportFragmentManager.findFragmentByTag(fragmentTag)
                findListener(clazz, fragment)
            }
            fragmentId != View.NO_ID -> {
                val fragment = requireActivity().supportFragmentManager.findFragmentById(fragmentId)
                findListener(clazz, fragment)
            }
            else -> {
                val activityByName =
                    if (activity?.javaClass?.name == activityName) activity else null
                findListener(clazz, activityByName)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> findListener(clazz: Class<T>, theObject: Any?): T? =
        when {
            theObject != null && clazz.isInstance(theObject) -> theObject as T?
            else -> null
        }

    data class Builder(
        val dialogId: String = "default_dialog_id",
        @StringRes val titleResId: Int = View.NO_ID,
        val title: String? = null,
        @StringRes val messageResId: Int = View.NO_ID,
        val message: String? = null,
        @StringRes private val positiveButtonTextResId: Int = View.NO_ID,
        private val positiveButtonText: String? = null,
        private val positiveListenerFragmentTag: String? = null,
        private val positiveListenerFragmentId: Int = View.NO_ID,
        @StringRes private val negativeButtonTextResId: Int = View.NO_ID,
        private val positiveListenerActivityName: String? = null,
        private val negativeButtonText: String? = null,
        private val negativeListenerFragmentTag: String? = null,
        private val negativeListenerFragmentId: Int = View.NO_ID,
        private val negativeListenerActivityName: String? = null,
        val isCancelable: Boolean = false
    ) {

        fun show(fragmentActivity: FragmentActivity) {
            create(fragmentActivity.applicationContext)
                .show(fragmentActivity.supportFragmentManager, BASIC_DIALOG_FRAGMENT_TAG + dialogId)
        }

        fun show(fragment: Fragment) {
            create(fragment.requireContext().applicationContext)
                .show(
                    fragment.requireActivity().supportFragmentManager,
                    BASIC_DIALOG_FRAGMENT_TAG + dialogId
                )
        }

        private fun create(
            context: Context
        ) = AppDialogFragment().also { appDialogFragment ->
            appDialogFragment.arguments = Bundle().apply {
                putString(ARG_DIALOG_ID, dialogId)
                putCharSequence(
                    ARG_TITLE,
                    if (titleResId != View.NO_ID) {
                        context.getString(titleResId)
                    } else {
                        title
                    }
                )
                putCharSequence(
                    ARG_MESSAGE,
                    if (messageResId != View.NO_ID) {
                        context.getString(messageResId)
                    } else {
                        message
                    }
                )
                putCharSequence(
                    ARG_POSITIVE_BUTTON_TEXT,
                    if (positiveButtonTextResId != View.NO_ID) {
                        context.getString(positiveButtonTextResId)
                    } else {
                        positiveButtonText
                    }
                )
                putCharSequence(
                    ARG_NEGATIVE_BUTTON_TEXT,
                    if (negativeButtonTextResId != View.NO_ID) {
                        context.getString(negativeButtonTextResId)
                    } else {
                        negativeButtonText
                    }
                )
                putString(ARG_POSITIVE_LISTENER_FRAGMENT_TAG, positiveListenerFragmentTag)
                putInt(ARG_POSITIVE_LISTENER_FRAGMENT_ID, positiveListenerFragmentId)
                putString(ARG_POSITIVE_LISTENER_ACTIVITY_NAME, positiveListenerActivityName)
                putString(ARG_NEGATIVE_LISTENER_FRAGMENT_TAG, negativeListenerFragmentTag)
                putInt(ARG_NEGATIVE_LISTENER_FRAGMENT_ID, negativeListenerFragmentId)
                putString(ARG_NEGATIVE_LISTENER_ACTIVITY_NAME, negativeListenerActivityName)
            }
            appDialogFragment.isCancelable = isCancelable
        }

        @Suppress("unused")
        fun <T> setPositiveButton(
            positiveButtonTextResId: Int,
            listener: T? = null
        ): Builder where T : OnPositiveClickListener, T : Fragment = copy(
            positiveButtonTextResId = positiveButtonTextResId,
            positiveListenerFragmentTag = listener?.tag,
            positiveListenerFragmentId = listener?.id ?: positiveListenerFragmentId
        )

        @Suppress("unused")
        fun <T> setPositiveButton(
            positiveButtonTextResId: Int,
            listener: T? = null
        ): Builder where T : OnPositiveClickListener, T : AppCompatActivity = copy(
            positiveButtonTextResId = positiveButtonTextResId,
            positiveListenerActivityName = listener?.javaClass?.name
        )

        @Suppress("unused")
        fun <T> setPositiveButton(
            positiveButtonText: String,
            listener: T? = null
        ): Builder where T : OnPositiveClickListener, T : Fragment = copy(
            positiveButtonText = positiveButtonText,
            positiveListenerFragmentTag = listener?.tag,
            positiveListenerFragmentId = listener?.id ?: positiveListenerFragmentId
        )

        @Suppress("unused")
        fun <T> setPositiveButton(
            positiveButtonText: String,
            listener: T? = null
        ): Builder where T : OnPositiveClickListener, T : AppCompatActivity = copy(
            positiveButtonText = positiveButtonText,
            positiveListenerActivityName = listener?.javaClass?.name
        )

        @Suppress("unused")
        fun <T> setNegativeButton(
            negativeButtonTextResId: Int,
            listener: T? = null
        ): Builder where T : OnNegativeClickListener, T : Fragment = copy(
            negativeButtonTextResId = negativeButtonTextResId,
            negativeListenerFragmentTag = listener?.tag,
            negativeListenerFragmentId = listener?.id ?: negativeListenerFragmentId
        )

        @Suppress("unused")
        fun <T> setNegativeButton(
            negativeButtonTextResId: Int,
            listener: T? = null
        ): Builder where T : OnNegativeClickListener, T : AppCompatActivity = copy(
            negativeButtonTextResId = negativeButtonTextResId,
            negativeListenerActivityName = listener?.javaClass?.name
        )

        @Suppress("unused")
        fun <T> setNegativeButton(
            negativeButtonText: String,
            listener: T? = null
        ): Builder where T : OnNegativeClickListener, T : Fragment = copy(
            negativeButtonText = negativeButtonText,
            negativeListenerFragmentTag = listener?.tag,
            negativeListenerFragmentId = listener?.id ?: negativeListenerFragmentId
        )

        @Suppress("unused")
        fun <T> setNegativeButton(
            negativeButtonText: String,
            listener: T? = null
        ): Builder where T : OnNegativeClickListener, T : AppCompatActivity = copy(
            negativeButtonText = negativeButtonText,
            negativeListenerActivityName = listener?.javaClass?.name
        )
    }
}

interface OnPositiveClickListener {
    fun onClickPositive(dialogId: String)
}

interface OnNegativeClickListener {
    fun onClickNegative(dialogId: String)
}

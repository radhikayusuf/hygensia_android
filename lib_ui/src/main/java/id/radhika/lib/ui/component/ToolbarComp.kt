package id.radhika.lib.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import id.radhika.lib.ui.R
import id.radhika.lib.ui.databinding.ToolbarCompLayoutBinding

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 20/Jun/2020
 **/
class ToolbarComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding by lazy { ToolbarCompLayoutBinding.inflate(LayoutInflater.from(context)) }
    private var onBackClickListener: (() -> Unit)? = null
    private var onNotificationClickListener: (() -> Unit)? = null

    var title: String
        get() {
            return binding.label.text.toString()
        }
        set(value) {
            binding.label.text = value
        }

    init {
        renderComponent()
    }

    fun showIcon(show: Boolean) {
        binding.icon.visibility = View.INVISIBLE
        binding.icon.isEnabled = false
    }

    fun showBackIcon(show: Boolean, onBackClickCallback: (() -> Unit)? = null) {
        onBackClickListener = onBackClickCallback
        binding.icon.isEnabled = show
        binding.icon.setOnClickListener(null)
        if (!show) {
            binding.icon.setImageResource(0)
            binding.icon.visibility = View.INVISIBLE
        } else {
            binding.icon.setImageResource(R.drawable.ic_baseline_chevron_left_24)
            binding.icon.visibility = View.VISIBLE
            binding.icon.setOnClickListener { onBackClickListener?.invoke() }
        }
    }

    fun showNotification(show: Boolean,  @ColorRes color: Int? = null, onNotificationCallback: (() -> Unit)? = null) {
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_notifications_24)?.let { drawable ->
            drawable.setTint(ContextCompat.getColor(context, color ?: R.color.colorTextPrimary))
            binding.notification.setImageDrawable(drawable)
        }
        onNotificationClickListener = onNotificationCallback
        binding.notification.visibility = if (show) View.VISIBLE else View.INVISIBLE
        binding.notification.isEnabled = show
        binding.notification.setOnClickListener(null)
        if (show) binding.notification.setOnClickListener {
            onNotificationClickListener?.invoke()
        }
    }

    fun showProfile(show: Boolean, @ColorRes color: Int? = null, onProfileClick: (() -> Unit)? = null) {
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_person_24)?.let { drawable ->
            drawable.setTint(ContextCompat.getColor(context, color ?: R.color.colorTextPrimary))
            binding.notification.setImageDrawable(drawable)
        }
        binding.notification.visibility = if (show) View.VISIBLE else View.INVISIBLE
        binding.notification.isEnabled = show
        binding.notification.setOnClickListener(null)
        if (show) binding.notification.setOnClickListener {
            onProfileClick?.invoke()
        }
    }

    fun setNotificationCount(count: Int) {
        // TODO
    }

    fun forceGoneBack() {
        binding.icon.visibility = View.GONE
    }

    fun changeBackgroundColor(@ColorInt color: Int) {
        binding.rootToolbar.setBackgroundColor(color)
    }

    fun changeBackgroundColorRes(@ColorRes color: Int) {
        binding.rootToolbar.setBackgroundColor(ContextCompat.getColor(context, color))
    }

    fun changeTitleColor(@ColorInt color: Int) {
        binding.label.setTextColor(color)
    }

    fun changeTitleColorRes(@ColorRes color: Int) {
        binding.label.setTextColor(ContextCompat.getColor(context, color))
    }

    fun setLabelGravity(gravity: Int) {
        binding.label.gravity = gravity
    }

    private fun renderComponent() {
        addView(binding.root)
    }
}
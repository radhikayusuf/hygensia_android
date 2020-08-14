package id.radhika.lib.ui.component

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
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

//    var logo: Drawable
//        get() {
//            return binding.logo.drawable
//        }
//        set(value) {
//            binding.label.visibility = View.GONE
////            binding.frameLogo.visibility = View.VISIBLE
////            binding.logo.setImageDrawable(value)
//            binding.icon.visibility = View.GONE
//        }

    var title: String
        get() {
            return binding.label.text.toString()
        }
        set(value) {
            if (value.trim().isNotEmpty()) {
//                binding.frameLogo.visibility = View.GONE
                binding.label.visibility = View.VISIBLE
            }
            binding.label.text = value
        }

    init {
        renderComponent()
    }

    fun showIcon(show: Boolean) {
        binding.icon.visibility = View.VISIBLE
        binding.icon.isEnabled = false
    }

//    fun setLogoRes(res: Int) {
//        ContextCompat.getDrawable(context, res)?.let { drawable ->
//            logo = drawable
//        }
//    }

    fun showBackIcon(show: Boolean, onBackClickCallback: (() -> Unit)? = null) {
        onBackClickListener = onBackClickCallback
        binding.icon.isEnabled = show
        binding.icon.setOnClickListener(null)
        if (!show) {
            binding.icon.setImageResource(0)
            binding.icon.visibility = View.INVISIBLE
        } else {
//            binding.frameLogo.visibility = View.GONE
            binding.icon.setImageResource(R.drawable.ic_baseline_chevron_left_24)
            binding.icon.visibility = View.VISIBLE
            binding.icon.setOnClickListener { onBackClickListener?.invoke() }
        }
    }

    fun showNotification(show: Boolean, fromWebView: Boolean = false, onNotificationCallback: (() -> Unit)? = null) {
        onNotificationClickListener = onNotificationCallback
        binding.notification.visibility = if (show) View.VISIBLE else View.INVISIBLE
        binding.notification.isEnabled = show
        binding.notification.setOnClickListener(null)
        if (fromWebView) {
            binding.notification.setImageResource(R.drawable.ic_baseline_language_24)
        }

        binding.notification.setOnClickListener {
            onNotificationCallback?.invoke()
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
        showNotification(false)
        addView(binding.root)
        binding.label.maxLines = 1
        binding.label.ellipsize = TextUtils.TruncateAt.END
    }

    companion object {
        var forceNotificationActivityTarget: (() -> Class<*>)? = null
    }
}
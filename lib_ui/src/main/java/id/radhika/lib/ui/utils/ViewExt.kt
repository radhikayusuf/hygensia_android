package id.radhika.lib.ui.utils

import android.animation.Animator
import android.util.TypedValue
import android.view.View
import android.view.View.*
import androidx.viewpager.widget.ViewPager

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Jul/2020
 **/

fun View.slideUpAnim(hide: Boolean, listener: (() -> Unit)? = null) {
    if (!hide) visibility = VISIBLE
    animate()
        .translationY(if (hide) ((0 - height).toFloat() * 1.3f) else 0f)
        .setDuration(500L)
        .setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                visibility = if (hide) INVISIBLE else VISIBLE
                listener?.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        .start()
}

fun ViewPager.addNegativeMargin(value: Float) {
    val margin = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    ).toInt()
    pageMargin = -margin
}
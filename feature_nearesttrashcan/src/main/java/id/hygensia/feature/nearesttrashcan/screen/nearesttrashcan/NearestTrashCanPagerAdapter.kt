package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import id.hygensia.feature.nearesttrashcan.databinding.ItemNearestTrashCanBinding
import id.hygensia.feature.nearesttrashcan.screen.data.model.TrashCanModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanPagerAdapter : PagerAdapter() {

    private val locations = mutableListOf<TrashCanModel>()
    private var onClickItem: ((Int, TrashCanModel) -> Unit)? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemNearestTrashCanBinding.inflate(LayoutInflater.from(container.context))
        container.addView(binding.root, 0)

        binding.labelTitle.text = locations[position].title
        binding.labelDesc.text = locations[position].desc
        binding.cardContent.setOnClickListener {
            onClickItem?.invoke(position, locations[position])
        }

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View?)
    }

    fun replaceAll(data: List<TrashCanModel>) {
        locations.clear()
        locations.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnClickItem(click: ((Int, TrashCanModel) -> Unit)?) {
        onClickItem = click
    }

    override fun isViewFromObject(view: View, any: Any) = view == any

    override fun getCount() = locations.size
}
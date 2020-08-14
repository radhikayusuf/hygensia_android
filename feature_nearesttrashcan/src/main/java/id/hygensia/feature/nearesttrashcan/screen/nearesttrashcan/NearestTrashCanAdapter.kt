package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.hygensia.feature.nearesttrashcan.databinding.ItemNearestTrashCanBinding
import id.hygensia.feature.nearesttrashcan.data.model.TrashCanModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanAdapter : RecyclerView.Adapter<NearestTrashCanAdapter.VH>() {

    private val locations = mutableListOf<TrashCanModel>()
    private var onClickItem: ((Int, TrashCanModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemNearestTrashCanBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.also {  binding ->
            binding.labelTitle.text = locations[position].title
            binding.labelDesc.text = locations[position].desc
            binding.root.setOnClickListener { onClickItem?.invoke(position, locations[position]) }
        }
    }

    fun replaceAll(data: List<TrashCanModel>) {
        locations.clear()
        locations.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnClickItem(click: ((Int, TrashCanModel) -> Unit)?) {
        onClickItem = click
    }

    class VH(val binding: ItemNearestTrashCanBinding) : RecyclerView.ViewHolder(binding.root)
}
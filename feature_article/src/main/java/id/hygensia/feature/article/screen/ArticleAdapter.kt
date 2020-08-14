package id.hygensia.feature.article.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.hygensia.feature.article.data.model.ArticleApiModel
import id.hygensia.feature.article.databinding.ItemArticleBinding

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.VH>() {

    private val articles = mutableListOf<ArticleApiModel>()
    var onClickArticle: ((ArticleApiModel) -> Unit)? = null

    class VH(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.let { binding ->
            binding.title.text = articles[position].title
            binding.desc.setHtmlText(articles[position].content.orEmpty())
            binding.image.setImageUrl(articles[position].imageUrl.orEmpty())
            if (articles[position].imageUrl.orEmpty().isBlank()) {
                binding.image.visibility = View.GONE
            } else {
                binding.image.visibility = View.VISIBLE
            }
            binding.root.setOnClickListener { onClickArticle?.invoke(articles[position]) }
        }
    }

    fun setData(products: List<ArticleApiModel>) {
        this@ArticleAdapter.articles.clear()
        this@ArticleAdapter.articles.addAll(products)
        notifyDataSetChanged()
    }
}
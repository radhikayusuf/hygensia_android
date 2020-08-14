package id.hygensia.feature.article.screen

import androidx.recyclerview.widget.LinearLayoutManager
import id.hygensia.feature.article.databinding.ScreenArticlesBinding
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.sheet.webview.WebViewSheetScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
class ArticlesScreen : BaseScreen<ScreenArticlesBinding, ArticleVM, ArticleDao>(
    ScreenArticlesBinding::inflate
) {

    private val adapter by lazy { ArticleAdapter() }

    override fun onViewReady() {
        renderToolbar()
        renderInitiateList()
    }

    private fun renderToolbar() {
        binding.toolbarArticle.title = "Artikel Untukmu"
        binding.toolbarArticle.showBackIcon(false)
        binding.toolbarArticle.showNotification(false)
    }

    private fun renderInitiateList() {
        binding.recyclerArticle.adapter = adapter
        binding.recyclerArticle.layoutManager = LinearLayoutManager(requireContext())
        binding.refreshLayout.setOnRefreshListener {
            vm.refreshContent()
        }
        adapter.onClickArticle = { data ->
            WebViewSheetScreen.openSheet(
                this,
                data.title.orEmpty(),
                data.content.orEmpty(),
                false,
                "",
                data.imageUrl.orEmpty()
            )
        }
    }

    override fun getViewModel() = ArticleVM::class.java

    override fun render() = { data: ArticleDao ->
        adapter.setData(data.articles)
        binding.refreshLayout.isRefreshing = data.isLoading
    }
}
package id.hygensia.feature.article.screen

import id.hygensia.feature.article.data.ArticleUseCase
import id.radhika.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
class ArticleVM(
    private val articleUseCase: ArticleUseCase = ArticleUseCase()
) : BaseVM<ArticleDao>() {

    override suspend fun onCreate() {
        refreshContent()
    }

    override fun onCreateDao() = ArticleDao()

    fun refreshContent()  = launch {
        dao.isLoading = true
        dao.articles.clear()
        dao.articles.addAll(articleUseCase.getArticle())
        dao.isLoading = false
    }
}
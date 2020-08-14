package id.hygensia.feature.article.data

import id.hygensia.feature.article.data.repository.ArticleRepositoryImpl
import id.radhika.lib.data.source.UseCase

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
class ArticleUseCase(
    private val repository: ArticleRepository = ArticleRepositoryImpl()
) : UseCase {

    suspend fun getArticle() = repository.getArticles().data.orEmpty()

}
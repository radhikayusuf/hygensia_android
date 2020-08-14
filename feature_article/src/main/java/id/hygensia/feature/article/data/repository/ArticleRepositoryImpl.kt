package id.hygensia.feature.article.data.repository

import id.hygensia.feature.article.data.ArticleRepository
import id.hygensia.feature.article.data.model.ArticleApiModel
import id.hygensia.feature.article.data.service.ArticleService
import id.radhika.lib.data.api.ApiService
import id.radhika.lib.data.model.SimpleResult
import id.radhika.lib.data.model.getExceptionResponse

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
class ArticleRepositoryImpl(
    private val articleService: ArticleService = ApiService.createService(ArticleService::class.java, ARTICLE_BASE_URL)
) : ArticleRepository {

    override suspend fun getArticles(): SimpleResult<List<ArticleApiModel>> {
        val result = articleService.getNearestTrashCan().orEmpty()
        return try {
            return SimpleResult(
                true,
                result,
                "Success"
            )
        } catch (e: Exception) {
            getExceptionResponse<List<ArticleApiModel>>(e).let {
                SimpleResult(
                    it.isSuccess,
                    it.data,
                    it.message
                )
            }
        }
    }

    companion object {
        const val ARTICLE_BASE_URL = "https://script.google.com/macros/s/"
    }
}
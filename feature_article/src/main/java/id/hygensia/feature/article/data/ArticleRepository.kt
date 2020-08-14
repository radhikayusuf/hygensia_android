package id.hygensia.feature.article.data

import id.hygensia.feature.article.data.model.ArticleApiModel
import id.radhika.lib.data.model.SimpleResult

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
interface ArticleRepository {

    suspend fun getArticles(): SimpleResult<List<ArticleApiModel>>
}
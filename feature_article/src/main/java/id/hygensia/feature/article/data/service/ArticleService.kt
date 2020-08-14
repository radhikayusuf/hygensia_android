package id.hygensia.feature.article.data.service

import id.hygensia.feature.article.data.model.ArticleApiModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Aug/2020
 **/
interface ArticleService {

    @GET("AKfycbyLexGjfYuxgdcRQQ7Uy7xjpoj6HPv1TYvcilzjUxWTmh297tqA/exec")
    suspend fun getNearestTrashCan(
        @Query("type") type: String = "article"
    ): List<ArticleApiModel>?
}
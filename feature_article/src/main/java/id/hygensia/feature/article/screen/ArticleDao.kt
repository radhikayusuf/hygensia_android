package id.hygensia.feature.article.screen

import id.hygensia.feature.article.data.model.ArticleApiModel
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.LiveListDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Aug/2020
 **/
class ArticleDao : BaseDao() {

    val articles = LiveListDao<ArticleApiModel>()

    private val _isLoading = LiveDao(false)
    var isLoading by _isLoading::content

}
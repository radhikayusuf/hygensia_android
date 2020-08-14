package id.hygensia.feature.article.data.model


import com.google.gson.annotations.SerializedName

data class ArticleApiModel(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("image")
    val imageUrl: String? = null
)
package id.hygensia.feature.nearesttrashcan.data.model


import com.google.gson.annotations.SerializedName

data class TrashCanApiModel(
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("lang")
    val lang: Double? = null,
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("size")
    val size: String? = null,
    @SerializedName("valid")
    val valid: Boolean? = null
)
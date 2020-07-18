package id.radhika.lib.data.model.user


import com.google.gson.annotations.SerializedName

data class RoleModel(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
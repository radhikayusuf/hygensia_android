package id.radhika.lib.data.model.user


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("address")
    val address: Any? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("dob")
    val dob: Any? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("is_active")
    val isActive: Boolean? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("role")
    val role: RoleModel? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
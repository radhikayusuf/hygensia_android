package id.radhika.lib.data.model.user


import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("data")
    val userModel: UserModel? = null,
    @SerializedName("token")
    val token: String? = null
)
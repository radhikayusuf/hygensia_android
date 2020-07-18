package id.ethicstech.feature.user.data.service

import id.radhika.lib.data.model.BaseResponse
import id.radhika.lib.data.model.user.UserResponseModel
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
interface UserServices {

    @POST("auth/register")
    @Multipart
    suspend fun registerUser(
        @Part("name") name: RequestBody,
        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("phone") phone: RequestBody
    ) : BaseResponse<Any>

    @POST("auth/login")
    @Multipart
    suspend fun loginUser(
        @Part("username") email: RequestBody, // requested by backend, expected username but actually email
        @Part("password") password: RequestBody
    ) : BaseResponse<UserResponseModel>

    @POST("auth/reset")
    @Multipart
    suspend fun forgotPassword(
        @Part("email") email: RequestBody
    ) : BaseResponse<Any>

    @POST("users/api/{userId}/change-password")
    @Multipart
    suspend fun changePassword(
        @Path("userId") userId: String,
        @Header("Authorization") authHeader: String,
        @Part("old_password") oldPassword: RequestBody,
        @Part("new_password") newPassword: RequestBody
    ) : BaseResponse<Any>
}
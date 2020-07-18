package id.ethicstech.feature.user.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import id.ethicstech.feature.user.FeatureUserModule
import id.ethicstech.feature.user.data.UserRepository
import id.ethicstech.feature.user.data.service.UserServices
import id.radhika.lib.data.api.ApiService
import id.radhika.lib.data.model.SimpleResult
import id.radhika.lib.data.model.getExceptionResponse
import id.radhika.lib.data.model.user.UserResponseModel
import id.radhika.lib.mvvm.util.StringConst.KEY_TOKEN
import id.radhika.lib.mvvm.util.StringConst.KEY_USER_DATA
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class UserRepositoryImpl(
    private val apiService: UserServices = ApiService.createService(UserServices::class.java, FeatureUserModule.get().baseUrl.invoke()),
    private val pref: SharedPreferences = FeatureUserModule.get().createPref(),
    private val featureConfig: FeatureUserModule = FeatureUserModule.get(),
    private val gson: Gson = FeatureUserModule.get().gson
) : UserRepository {

    override suspend fun register(
        name: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ): SimpleResult<Boolean> {
        delay(2000)
        return try {
//            val result = apiService.registerUser(
//                name.toRequestBody(), username.toRequestBody(), email.toRequestBody(), password.toRequestBody(), phoneNumber.toRequestBody()
//            )
            SimpleResult(true, true, "Success")
        } catch (e: Exception) {
            getExceptionResponse<Boolean>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }

    override suspend fun login(email: String, password: String): SimpleResult<UserResponseModel> {
        delay(2000)
        return try {
//            val result = apiService.loginUser(
//                email.toRequestBody(), password.toRequestBody()
//            )
            pref.edit().apply {
                putString(KEY_USER_DATA, gson.toJson(UserResponseModel()))
                putString(KEY_TOKEN, gson.toJson("dummy-token"))
            }.apply()

            SimpleResult(false, UserResponseModel(), "Success")
        } catch (e: Exception) {
            getExceptionResponse<UserResponseModel>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }

    override suspend fun forgotPassword(email: String): SimpleResult<Boolean> {
        delay(2000)
        return try {
//            val result = apiService.forgotPassword(email.toRequestBody())
            SimpleResult(true, true, "Success")
        } catch (e: Exception) {
            getExceptionResponse<Boolean>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }

    override suspend fun changePassword(
        oldPassword: String,
        password: String,
        userId: String
    ): SimpleResult<Boolean> {
        delay(2000)
        return try {
//            val auth = featureConfig.token.toHeaderAuth()
//            val result = apiService.changePassword(userId, auth, oldPassword.toRequestBody(), password.toRequestBody())
            SimpleResult(true, true, "Success")
        } catch (e: Exception) {
            getExceptionResponse<Boolean>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }

    override suspend fun logout() {
        pref.edit().apply {
            putString(KEY_USER_DATA, null)
            putString(KEY_TOKEN, null)
        }.apply()
    }
}
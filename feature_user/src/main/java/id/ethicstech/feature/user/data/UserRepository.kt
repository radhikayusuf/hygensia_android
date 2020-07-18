package id.ethicstech.feature.user.data

import id.radhika.lib.data.model.SimpleResult
import id.radhika.lib.data.model.user.UserResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
interface UserRepository {

    suspend fun register(name: String, username: String, email: String, phoneNumber: String, password: String): SimpleResult<Boolean>

    suspend fun login(email: String, password: String): SimpleResult<UserResponseModel>

    suspend fun forgotPassword(email: String): SimpleResult<Boolean>

    suspend fun logout()

    suspend fun changePassword(
        oldPassword: String,
        password: String,
        userId: String
    ): SimpleResult<Boolean>

}
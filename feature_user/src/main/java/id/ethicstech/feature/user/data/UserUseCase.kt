package id.ethicstech.feature.user.data

import id.ethicstech.feature.user.data.repository.UserRepositoryImpl
import id.radhika.lib.data.model.SimpleResult
import id.radhika.lib.data.model.user.UserResponseModel
import id.radhika.lib.data.source.UseCase

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 01/Jul/2020
 **/
class UserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl()
) : UseCase {

    private var isUserLoggedIn = false

    suspend fun doLogin(email: String, password: String): SimpleResult<UserResponseModel> {
        return userRepository.login(email, password)
    }

    suspend fun logout() {
        userRepository.logout()
    }

    suspend fun registerUser(
        name: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ): SimpleResult<Boolean> {
        return userRepository.register(name, username, email, phoneNumber, password)
    }

    suspend fun forgotPassword(email: String): SimpleResult<Boolean> {
        return userRepository.forgotPassword(email)
    }

    suspend fun changePassword(userId: String, oldPassword: String, password: String): SimpleResult<Boolean> {
        return userRepository.changePassword(oldPassword, password, userId)
    }

    companion object {

        private val INSTANCE by lazy { UserUseCase() }

        fun getInstance() = INSTANCE
    }
}
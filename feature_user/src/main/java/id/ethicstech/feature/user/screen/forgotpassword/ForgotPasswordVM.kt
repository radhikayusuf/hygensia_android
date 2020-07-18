package id.ethicstech.feature.user.screen.forgotpassword

import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.data.UserUseCase
import id.radhika.lib.mvvm.BaseVM
import id.radhika.lib.mvvm.util.isEmail
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 20/Jun/2020
 **/

class ForgotPasswordVM(
    private val userUseCase: UserUseCase = UserUseCase.getInstance()
) : BaseVM<ForgotPasswordDao>() {

    override suspend fun onCreate() {

    }

    fun forgotPassword(email: String) = launch {
        dao.isLoading = true
        val result = userUseCase.forgotPassword(email)
        dao.isLoading = false
        if (result.isSuccess) {
            delay(500)
            dao.openMain = true
        } else {
            showToast(result.message)
        }
    }

    fun validateFields(pos: Int, text: String, email: String) {
        dao.isValid = false
        if (pos == 0) dao.errorEmail = null

        if (text.isEmpty()) {
            if (pos == 0) {
                dao.errorEmail = R.string.text_harap_masukan_email; return
            }
        }

        if (pos == 0 && !text.isEmail()) {
            dao.errorEmail = R.string.text_format_email_tidak_sesuai
        }

        dao.isValid = dao.errorEmail == null && email.isNotEmpty()
    }

    override fun onCreateDao() = ForgotPasswordDao()
}
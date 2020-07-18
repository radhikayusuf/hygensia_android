package id.ethicstech.feature.user.screen.login

import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.data.UserUseCase
import id.radhika.lib.mvvm.BaseVM
import id.radhika.lib.mvvm.util.isEmail
import id.radhika.lib.mvvm.util.isPassword
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 20/Jun/2020
 **/
class LoginVM(
    private val userUseCase: UserUseCase = UserUseCase.getInstance()
) : BaseVM<LoginDao>() {

    override suspend fun onCreate() {

    }

    fun doLogin(email: String, password: String) = launch {
        dao.isLoading = true
        val result = userUseCase.doLogin(email, password)
        dao.isLoading = false
        if (result.isSuccess) {
            delay(500)
            dao.openMain = true
        } else {
            showToast(result.message)
        }
    }

    fun validateFields(pos: Int, text: String, email: String, password: String) {
        dao.isValid = false
        if (pos == 0) dao.errorEmail = null
        if (pos == 1) dao.errorPassword = null

        if (text.isEmpty()) {
            if (pos == 0) {
                dao.errorEmail = R.string.text_harap_masukan_email; return
            }
            if (pos == 1) {
                dao.errorPassword = R.string.text_harap_masukan_password; return
            }
        }

        if (pos == 0 && !text.isEmail()) {
            dao.errorEmail = R.string.text_format_email_tidak_sesuai
        }

        if (pos == 1 && !text.isPassword()) {
            dao.errorPassword = R.string.text_harap_masukan_password_6_karakter
        }

        dao.isValid = dao.errorEmail == null && email.isNotEmpty() &&
                dao.errorPassword == null && password.isNotEmpty()
    }

    override fun onCreateDao() = LoginDao()
}
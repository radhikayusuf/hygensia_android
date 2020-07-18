package id.ethicstech.feature.user.screen.register

import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.data.UserUseCase
import id.radhika.lib.mvvm.BaseVM
import id.radhika.lib.mvvm.util.isEmail
import id.radhika.lib.mvvm.util.isPassword
import kotlinx.coroutines.delay

/**
 * Created by Yongki Agustin
 * on 26/Jun/2020
 **/
class RegisterVM(
    private val userUseCase: UserUseCase = UserUseCase.getInstance()
) : BaseVM<RegisterDao>() {
    override suspend fun onCreate() {
    }

    override fun onCreateDao() = RegisterDao()

    fun onFieldTyping(
        pos: Int,
        text: String,
        name: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String,
        passwordConf: String
    ) {
        dao.isValid = false
        if (pos == 0) dao.errorName = null
        if (pos == 1) dao.errorUsername = null
        if (pos == 2) dao.errorEmail = null
        if (pos == 3) dao.errorPhoneNumber = null
        if (pos in 4..5) {
            dao.errorPassword = null
            dao.errorPasswordConf = null
        }

        if (text.isEmpty()) {
            if (pos == 0) { dao.errorName = R.string.text_harap_masukan_nama; return }
//            if (pos == 1) { dao.errorUsername = R.string.text_harap_masukan_username; return }
            if (pos == 2) { dao.errorEmail = R.string.text_harap_masukan_email; return }
            if (pos == 3) { dao.errorPhoneNumber = R.string.text_harap_masukan_nomor_telepon; return }
            if (pos in 4..5) {
                if (password.isEmpty()) dao.errorPassword = R.string.text_harap_masukan_password
                if (passwordConf.isEmpty()) dao.errorPasswordConf = R.string.text_password_dan_password_konfirmasi_tidak_sesuai; return
            }
        }

        if (pos == 0 && name.length < 3) {
            dao.errorName = R.string.text_harap_isi_nama_dengan_minimal_3_karakter
        }

        if (pos == 2 && !text.isEmail()) {
            dao.errorEmail = R.string.text_format_email_tidak_sesuai
        }

        if (pos == 3 && text.length < 10) {
            dao.errorPhoneNumber = R.string.text_phonenumber_minimal_10_karakter
        }

        if (pos in 4..5) {
            if (!password.isPassword()) {
                dao.errorPassword = R.string.text_harap_masukan_password_6_karakter
            }
            if (password != passwordConf) {
                dao.errorPasswordConf = R.string.text_password_dan_password_konfirmasi_tidak_sesuai
            }
        }

        dao.isValid =
                dao.errorName == null && name.isNotEmpty() &&
                dao.errorUsername == null &&
                dao.errorEmail == null && email.isNotEmpty() &&
                dao.errorPhoneNumber == null && phoneNumber.isNotEmpty() &&
                dao.errorPassword == null && password.isNotEmpty() &&
                dao.errorPasswordConf == null && passwordConf.isNotEmpty()
    }

    fun doRegister(name: String, username: String, email: String, phoneNumber: String, password: String) = launch {
        dao.isLoading = true
        val result = userUseCase.registerUser(
            name, username, email, reformatPhoneNumber(phoneNumber), password
        )
        dao.isLoading = false
        if (result.data == true) {
            delay(300)
            dao.closePage = true
        } else {
            showToast(result.message)
        }
    }

    fun reformatPhoneNumber(phoneNumber: String): String {
        return when  {
            phoneNumber.startsWith("62") -> {
                "0${phoneNumber.substring(2, phoneNumber.length)}"
            }
            phoneNumber.startsWith("+62") -> {
                "0${phoneNumber.substring(3, phoneNumber.length)}"
            }
            else -> phoneNumber
        }
    }
}
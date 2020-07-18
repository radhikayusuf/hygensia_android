package id.ethicstech.feature.user.screen.changepassword

import id.ethicstech.feature.user.FeatureUserModule
import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.data.UserUseCase
import id.radhika.lib.mvvm.BaseVM
import id.radhika.lib.mvvm.util.isEmail
import id.radhika.lib.mvvm.util.isPassword
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 19/Jun/2020
 **/
class ChangePasswordVM(
    private val userUseCase: UserUseCase = UserUseCase.getInstance(),
    private val featureConfig: FeatureUserModule = FeatureUserModule.get()
) : BaseVM<ChangePasswordDao>() {

    private val userData get() = featureConfig.userData

    override suspend fun onCreate() {

    }

    override fun onCreateDao() = ChangePasswordDao()

    fun onFieldTyping(
        pos: Int,
        text: String,
        oldPassword: String,
        password: String,
        passwordConf: String
    ) {
        dao.isValid = false
        if (pos == 0) dao.errorOldPassword = null
        if (pos == 1) dao.errorNewPassword = null
        if (pos == 2) dao.errorRetypePassword = null

        if (text.isEmpty()) {
            if (pos == 0) {
                dao.errorOldPassword = R.string.text_harap_masukan_password; return
            }
            if (pos == 1) {
                dao.errorNewPassword = R.string.text_harap_masukan_password_baru; return
            }
            if (pos == 2) {
                dao.errorRetypePassword =
                    R.string.text_password_dan_password_baru_konfirmasi_tidak_sesuai; return
            }
        }


        if (pos == 1 && !text.isPassword() && dao.errorNewPassword == null) {
            dao.errorNewPassword = R.string.text_harap_masukan_password_6_karakter
        }

        if (pos == 1 && text == oldPassword && dao.errorNewPassword == null) {
            dao.errorNewPassword = R.string.text_kata_sandi_baru_tidak_boleh_sama
        }

        if (pos == 2 && password != passwordConf && dao.errorRetypePassword == null) {
            dao.errorRetypePassword = R.string.text_password_dan_password_konfirmasi_tidak_sesuai
        }

        dao.isValid = dao.errorOldPassword == null && oldPassword.isNotEmpty() &&
                dao.errorNewPassword == null && password.isNotEmpty() &&
                dao.errorRetypePassword == null && passwordConf.isNotEmpty()
    }

    fun onClickChangePassword(oldPassword: String, password: String) = launch {
        dao.isLoading = true
        val result = userUseCase.changePassword(userData?.userModel?.id?.toString().orEmpty(), oldPassword, password)
        dao.isLoading = false
        if (result.data == true) {
            delay(800)
            dao.closePage = true
        } else {
            showToast(result.message)
        }
    }
}
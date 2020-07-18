package id.ethicstech.feature.user.screen.changepassword

import id.ethicstech.feature.user.databinding.ScreenChangePasswordBinding
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.util.showToast

class ChangePasswordScreen : BaseScreen<ScreenChangePasswordBinding, ChangePasswordVM, ChangePasswordDao>(
    ScreenChangePasswordBinding::inflate
) {

    private val oldPassword get() = binding.fieldOldPassword.text.toString()
    private val password get() = binding.fieldNewPassword.text.toString()
    private val passwordConf get() = binding.fieldRetypePassword.text.toString()

    override fun onViewReady() {
        binding.toolbar.title = "Change Password"
        binding.buttonSubmit.setOnClickListener { vm.onClickChangePassword(oldPassword, password) }
        binding.toolbar.showBackIcon(true) { finishScreen() }
        EditTextHelper.addTypingListener(binding.fieldOldPassword, binding.fieldNewPassword, binding.fieldRetypePassword) { pos, text ->
            vm.onFieldTyping(pos, text, oldPassword, password, passwordConf)
        }
    }

    override fun render() = { data: ChangePasswordDao ->
        renderErrorFields(data)
        renderLoading(data)
        if (data.closePage) {
            showToast("Selamat! Kata sandi berhasil diubah")
            finishScreen()
        }
    }

    private fun renderLoading(data: ChangePasswordDao) {
        binding.buttonSubmit.showLoading(data.isLoading)
        binding.fieldNewPassword.isEnabled = !data.isLoading
        binding.fieldOldPassword.isEnabled = !data.isLoading
        binding.fieldRetypePassword.isEnabled = !data.isLoading
    }

    private fun renderErrorFields(data: ChangePasswordDao) {
        binding.buttonSubmit.isEnabled = data.isValid
        data.errorOldPassword?.let { getString(it) }.let {
            binding.inputOldPassword.error = it
            binding.inputOldPassword.isErrorEnabled = it != null
        }

        data.errorNewPassword?.let { getString(it) }.let {
            binding.inputNewPassword.error = it
            binding.inputNewPassword.isErrorEnabled = it != null
        }

        data.errorRetypePassword?.let { getString(it) }.let {
            binding.inputRetypePassword.error = it
            binding.inputRetypePassword.isErrorEnabled = it != null
        }
    }

    override fun getViewModel() = ChangePasswordVM::class.java
}
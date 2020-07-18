package id.ethicstech.feature.user.screen.register

import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.databinding.ScreenRegisterBinding
import id.ethicstech.feature.user.screen.login.LoginScreen
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.util.showToast

/**
 * Created by Yongki Agustin
 * on 26/Jun/2020
 **/
class RegisterScreen : BaseScreen<ScreenRegisterBinding, RegisterVM, RegisterDao>(
    ScreenRegisterBinding::inflate
) {

    private val name get() = binding.fieldName.text.toString()
    private val username get() = binding.fieldUsername.text.toString()
    private val email get() = binding.fieldEmail.text.toString()
    private val phoneNumber get() = binding.fieldNumberPhone.text.toString()
    private val password get() = binding.fieldPassword.text.toString()
    private val passwordConf get() = binding.fieldConfirmPassword.text.toString()

    override fun onViewReady() {
        renderToolbar()
        renderFieldValidator()
        renderClickListener()
    }

    override fun render() = { data: RegisterDao ->
        renderErrorField(data)
        renderButtonIndicator(data)
        renderLoadingField(data)
    }

    private fun renderToolbar() {
        binding.toolbar.title = "Pendaftaran"
        binding.toolbar.showBackIcon(true) {
            finishScreen()
        }
        binding.toolbar.showNotification(false)
    }

    private fun renderFieldValidator() {
        EditTextHelper.addTypingListener(
            binding.fieldName, binding.fieldUsername, binding.fieldEmail, binding.fieldNumberPhone, binding.fieldPassword, binding.fieldConfirmPassword
        ) { pos, text ->
            vm.onFieldTyping(
                pos, text, name, username, email, phoneNumber, password, passwordConf
            )
        }
    }

    private fun renderErrorField(data: RegisterDao) {
        data.errorName?.let { getString(it) }.let {
            binding.inputName.error = it
            binding.inputName.isErrorEnabled = it != null
        }

        data.errorUsername?.let { getString(it) }.let {
            binding.inputUsername.error = it
            binding.inputUsername.isErrorEnabled = it != null
        }

        data.errorEmail?.let { getString(it) }.let {
            binding.inputEmail.error = it
            binding.inputEmail.isErrorEnabled = it != null
        }

        data.errorPhoneNumber?.let { getString(it) }.let {
            binding.inputPhoneNumber.error = it
            binding.inputPhoneNumber.isErrorEnabled = it != null
        }

        data.errorPassword?.let { getString(it) }.let {
            binding.inputPassword.error = it
            binding.inputPassword.isErrorEnabled = it != null
        }

        data.errorPasswordConf?.let { getString(it) }.let {
            binding.inputPasswordConf.error = it
            binding.inputPasswordConf.isErrorEnabled = it != null
        }
    }

    private fun renderLoadingField(data: RegisterDao) {
        binding.fieldName.isEnabled = !data.isLoading
        binding.fieldUsername.isEnabled = !data.isLoading
        binding.fieldEmail.isEnabled = !data.isLoading
        binding.fieldNumberPhone.isEnabled = !data.isLoading
        binding.fieldPassword.isEnabled = !data.isLoading
        binding.fieldConfirmPassword.isEnabled = !data.isLoading
        binding.labelLogin.isEnabled = !data.isLoading
        binding.labelLogin.isClickable = !data.isLoading
        binding.buttonRegister.showLoading(data.isLoading)
    }

    private fun renderButtonIndicator(data: RegisterDao) {
        binding.buttonRegister.isEnabled = data.isValid
        if (data.closePage) {
            showToast(getString(R.string.text_pendaftaran_berhasil_desc_register))
            finishScreen()
        }
    }

    private fun renderClickListener() {
        binding.buttonRegister.setOnClickListener {
            vm.doRegister(
                name, username, email, phoneNumber, password
            )
        }
        binding.labelLogin.setClickWordsRes(
            arrayListOf(R.string.text_masuk)
        ) {
            openScreen(LoginScreen()) }
    }

    override fun getViewModel() = RegisterVM::class.java
}
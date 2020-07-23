package id.ethicstech.feature.user.screen.login

import id.ethicstech.feature.user.FeatureUserModule
import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.databinding.ScreenLoginBinding
import id.ethicstech.feature.user.screen.forgotpassword.ForgotPasswordScreen
import id.ethicstech.feature.user.screen.register.RegisterScreen
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 20/Jun/2020
 **/
class LoginScreen : BaseScreen<ScreenLoginBinding, LoginVM, LoginDao>(
    ScreenLoginBinding::inflate
) {

    private val email get() = binding.fieldEmail.text.toString()
    private val password get() = binding.fieldPassword.text.toString()
    private var hasEditValue = false

    override fun onViewReady() {
        renderInitiateComponent()
    }

    override fun render() = { data: LoginDao ->
        if (hasEditValue) {
            if (data.openMain) {
                openActivity(this, FeatureUserModule.get().screenAfterLogin.invoke(), true)
            }
            renderLoading(data)
            renderErrors(data)
        }
    }

    private fun renderLoading(data: LoginDao) {
        binding.fieldEmail.isEnabled = !data.isLoading
        binding.fieldPassword.isEnabled = !data.isLoading
        binding.forgotPassword.isEnabled = !data.isLoading
        binding.forgotPassword.isFocusable = !data.isLoading
        binding.labelRegister.isEnabled = !data.isLoading
        binding.labelRegister.isFocusable = !data.isLoading
        binding.buttonLogin.showLoading(data.isLoading)
    }

    private fun renderErrors(data: LoginDao) {
        binding.buttonLogin.isEnabled = data.isValid
        data.errorEmail?.let { getString(it) }.let {
            binding.inputEmail.error = it
            binding.inputEmail.isErrorEnabled = it != null
        }

        data.errorPassword?.let { getString(it) }.let {
            binding.inputPassword.error = it
            binding.inputPassword.isErrorEnabled = it != null
        }
    }

    private fun renderInitiateComponent() {
        binding.forgotPassword.setOnClickListener {
            val email = binding.fieldEmail.text.toString()
            openScreen(ForgotPasswordScreen(), ForgotPasswordScreen.argument(email))
        }
        binding.buttonLogin.setOnClickListener {
            vm.doLogin(email, password)
        }
        binding.labelRegister.setClickWordsRes(
            arrayListOf(R.string.text_signup_here)
        ) {
            openScreen(RegisterScreen())
        }
        binding.forgotPassword.setClickWordsRes(
            arrayListOf(R.string.text_clickhere)
        ) {
            openScreen(RegisterScreen())
        }

        EditTextHelper.addTypingListener(binding.fieldEmail, binding.fieldPassword) { pos, text ->
            if (text.isNotEmpty()) hasEditValue = true
            vm.validateFields(pos, text, email, password)
        }
    }

    override fun getViewModel() = LoginVM::class.java
}
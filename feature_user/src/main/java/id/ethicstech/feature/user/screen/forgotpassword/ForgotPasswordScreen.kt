package id.ethicstech.feature.user.screen.forgotpassword

import android.os.Bundle
import android.view.View
import id.ethicstech.feature.user.databinding.ScreenForgotPasswordBinding
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.util.showToast

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 20/Jun/2020
 **/
class ForgotPasswordScreen :
    BaseScreen<ScreenForgotPasswordBinding, ForgotPasswordVM, ForgotPasswordDao>(
        ScreenForgotPasswordBinding::inflate
    ), View.OnClickListener {

    private val email get() = binding.fieldEmail.text.toString()

    override fun onViewReady() {
        renderInitiateComponent()
    }

    override fun onReceivedData(dataResult: Bundle?) {
        super.onReceivedData(dataResult)
        dataResult?.getString(PARAM_EMAIL).takeIf { !it.isNullOrEmpty() }?.let {
            binding.fieldEmail.setText(it)
        }
    }

    override fun render() = { data: ForgotPasswordDao ->
        renderError(data)
        renderLoading(data)

        if (data.openMain) {
            showToast("Link untuk reset password sudah kami kirimkan ke email terdaftar kamu, tolong di cek ya!")
            finishScreen()
        }
    }

    private fun renderInitiateComponent() {
        binding.toolbar.title = "Lupa Password"
        binding.toolbar.showBackIcon(true) {
            finishScreen()
        }
        binding.buttonForgotPassword.setOnClickListener(this@ForgotPasswordScreen)
        EditTextHelper.addTypingListener(binding.fieldEmail) { pos, text ->
            vm.validateFields(pos, text, email)
        }
    }

    private fun renderLoading(data: ForgotPasswordDao) {
        binding.fieldEmail.isEnabled = !data.isLoading
        binding.buttonForgotPassword.showLoading(data.isLoading)
    }

    private fun renderError(data: ForgotPasswordDao) {
        binding.buttonForgotPassword.isEnabled = data.isValid
        data.errorEmail?.let { getString(it) }.let {
            binding.inputEmail.error = it
            binding.inputEmail.isErrorEnabled = it != null
        }
    }

    override fun onClick(v: View?) {
        vm.forgotPassword(email)
    }

    override fun getViewModel() = ForgotPasswordVM::class.java

    companion object {

        const val PARAM_EMAIL = "param_email"

        fun argument(email: String) = Bundle().also { b ->
            b.putString(PARAM_EMAIL, email)
        }
    }
}
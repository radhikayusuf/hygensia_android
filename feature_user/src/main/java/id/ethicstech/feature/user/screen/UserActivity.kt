package id.ethicstech.feature.user.screen

import android.os.Bundle
import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.screen.changepassword.ChangePasswordScreen
import id.ethicstech.feature.user.screen.forgotpassword.ForgotPasswordScreen
import id.ethicstech.feature.user.screen.login.LoginScreen
import id.ethicstech.feature.user.screen.register.RegisterScreen
import id.radhika.lib.mvvm.BaseActivity

class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        intent.extras?.let { b ->
            if (b.getBoolean("openRegister", false)) {
                replaceScreen(RegisterScreen())
            } else {
                replaceScreen(LoginScreen())
            }
        } ?: replaceScreen(LoginScreen())

    }

    override fun frameLayoutId() = R.id.frame_user
}
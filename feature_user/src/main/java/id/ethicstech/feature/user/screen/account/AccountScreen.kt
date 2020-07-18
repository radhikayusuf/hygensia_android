package id.ethicstech.feature.user.screen.account

import android.view.View
import id.ethicstech.feature.user.R
import id.ethicstech.feature.user.databinding.ScreenAccountBinding
import id.ethicstech.feature.user.screen.UserActivity
//import id.ethicstech.feature.user.screen.biodata.BiodataScreen
import id.ethicstech.feature.user.screen.changepassword.ChangePasswordScreen
import id.ethicstech.feature.user.screen.login.LoginScreen
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Yongki Agustin
 * on 26/Jun/2020
 **/
class AccountScreen : BaseScreen<ScreenAccountBinding, AccountVM, AccountDao>(
    ScreenAccountBinding::inflate
), View.OnClickListener {

    override fun onViewReady() {
        binding.labelKuota.setOnClickListener(this)
        binding.labelPersonalData.setOnClickListener(this)
        binding.labelChangePassword.setOnClickListener(this)
        binding.labelTermAndCondition.setOnClickListener(this)
        binding.labelPrivacyPolicy.setOnClickListener(this)
        binding.labelFaq.setOnClickListener(this)
        binding.labelCareCenter.setOnClickListener(this)
        binding.labelLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.labelKuota -> {
//                WebViewSheetScreen().show(childFragmentManager, "")
            }
            R.id.labelPersonalData -> {
//                openScreen(BiodataScreen())
            }
            R.id.labelChangePassword -> {
               openScreen(ChangePasswordScreen())
            }
//            R.id.labelTermAndCondition -> {
//                WebViewSheetScreen().show(childFragmentManager, "")
//            }
//            R.id.labelPrivacyPolicy -> {
//                WebViewSheetScreen().show(childFragmentManager, "")
//            }
//            R.id.labelFaq -> {
//                WebViewSheetScreen().show(childFragmentManager, "")
//            }
//            R.id.labelCareCenter -> {
//                WebViewSheetScreen().show(childFragmentManager, "")
//            }
            R.id.labelLogout -> {
                vm.logout()
                activity.finish()
                openActivity(requireContext(), UserActivity::class.java, true)
            }
            else -> {
            }
        }
    }


    override fun render() = { data: AccountDao ->
        binding.labelFullName.text = data.userName
        binding.labelNumberPhone.text = data.phoneNumber
    }

    override fun getViewModel() = AccountVM::class.java

}
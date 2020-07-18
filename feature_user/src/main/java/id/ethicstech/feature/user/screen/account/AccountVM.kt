package id.ethicstech.feature.user.screen.account

import id.ethicstech.feature.user.FeatureUserModule
import id.ethicstech.feature.user.data.UserUseCase
import id.radhika.lib.mvvm.BaseVM
/**
 * Created by Yongki Agustin
 * on 26/Jun/2020
 **/
class AccountVM(
    private val userUseCase: UserUseCase = UserUseCase.getInstance(),
    private val featureUserConfig: FeatureUserModule = FeatureUserModule.get()
) : BaseVM<AccountDao>() {

    private val userData get() = featureUserConfig.userData

    override suspend fun onCreate() {
        dao.phoneNumber = userData?.userModel?.phone.orEmpty()
        dao.userName = userData?.userModel?.name.orEmpty()
    }

    override fun onCreateDao() = AccountDao()

    fun logout() = launch {
        userUseCase.logout()
    }

}
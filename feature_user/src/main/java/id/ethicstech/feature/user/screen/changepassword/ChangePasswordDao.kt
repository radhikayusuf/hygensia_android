package id.ethicstech.feature.user.screen.changepassword

import id.lesprivate.lib.ui.utils.NullableLiveDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 19/Jun/2020
 **/
class ChangePasswordDao : BaseDao() {

    private val _isValid = LiveDao(false)
    var isValid by _isValid::content

    private val _isLoading = LiveDao(false)
    var isLoading by _isLoading::content

    private val _closePage = LiveDao(false)
    var closePage by _closePage::content

    private val errorOldPasswordData = NullableLiveDao<Int>(null)
    var errorOldPassword by errorOldPasswordData::content

    private val errorNewPasswordData = NullableLiveDao<Int>(null)
    var errorNewPassword by errorNewPasswordData::content

    private val errorRetypePasswordData = NullableLiveDao<Int>(null)
    var errorRetypePassword by errorRetypePasswordData::content
}
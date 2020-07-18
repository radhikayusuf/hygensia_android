package id.ethicstech.feature.user.screen.login

import id.lesprivate.lib.ui.utils.NullableLiveDao
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 20/Jun/2020
 **/
class LoginDao : BaseDao() {

    private val _isValid = LiveDao(false)
    var isValid by _isValid::content

    private val _openMain = LiveDao(false)
    var openMain by _openMain::content

    private val _isLoading = LiveDao(false)
    var isLoading by _isLoading::content

    private val _errorEmail = NullableLiveDao<Int>(null)
    var errorEmail by _errorEmail::content

    private val _errorPassword = NullableLiveDao<Int>(null)
    var errorPassword by _errorPassword::content

}
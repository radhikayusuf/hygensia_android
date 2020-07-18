package id.ethicstech.feature.user.screen.register

import id.lesprivate.lib.ui.utils.NullableLiveDao
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Yongki Agustin
 * on 26/Jun/2020
 **/
class RegisterDao : BaseDao() {

    private val _isValid = LiveDao(false)
    var isValid by _isValid::content

    private val _isLoading = LiveDao(false)
    var isLoading by _isLoading::content

    private val _closePage = LiveDao(false)
    var closePage by _closePage::content

    private val _errorName = NullableLiveDao<Int>(null)
    var errorName by _errorName::content

    private val _errorUsername = NullableLiveDao<Int>(null)
    var errorUsername by _errorUsername::content

    private val _errorEmail = NullableLiveDao<Int>(null)
    var errorEmail by _errorEmail::content

    private val _errorPhoneNumber = NullableLiveDao<Int>(null)
    var errorPhoneNumber by _errorPhoneNumber::content

    private val _errorPassword = NullableLiveDao<Int>(null)
    var errorPassword by _errorPassword::content

    private val _errorPasswordConf = NullableLiveDao<Int>(null)
    var errorPasswordConf by _errorPasswordConf::content

}
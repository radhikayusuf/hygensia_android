package id.ethicstech.feature.user.screen.account

import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Yongki Agustin
 * on 26/Jun/2020
 **/
class AccountDao : BaseDao() {

    private val _userName = LiveDao("")
    var userName by _userName::content

    private val _phoneNumber = LiveDao("")
    var phoneNumber by _phoneNumber::content

}
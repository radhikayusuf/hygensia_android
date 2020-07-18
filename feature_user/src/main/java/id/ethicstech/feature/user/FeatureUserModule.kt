package id.ethicstech.feature.user

import id.radhika.lib.mvvm.BaseModule

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 18/Jun/2020
 **/
class FeatureUserModule
private constructor(): BaseModule() {

    lateinit var screenAfterLogin: (() -> Class<*>)

    companion object {
        private val INSTANCE by lazy { FeatureUserModule() }
        fun get() = INSTANCE
    }
}
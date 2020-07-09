package id.hygensia.feature.home

import id.radhika.lib.mvvm.BaseModule

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class FeatureHomeModule : BaseModule() {

    companion object {
        private val INSTANCE by lazy { FeatureHomeModule() }
        fun get() = INSTANCE
    }
}
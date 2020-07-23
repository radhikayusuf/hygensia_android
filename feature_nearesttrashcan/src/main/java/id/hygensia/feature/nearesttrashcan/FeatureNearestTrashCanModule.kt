package id.hygensia.feature.nearesttrashcan

import id.radhika.lib.mvvm.BaseModule

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class FeatureNearestTrashCanModule : BaseModule() {

    lateinit var screenOpenLogin: (() -> Class<*>)

    companion object {
        private val INSTANCE by lazy { FeatureNearestTrashCanModule() }
        fun get() = INSTANCE
    }
}
package id.radhika.app

import id.ethicstech.feature.user.FeatureUserModule
import id.ethicstech.feature.user.screen.UserActivity
import id.hygensia.feature.home.FeatureHomeModule
import id.hygensia.feature.home.screen.HomeActivity
import id.hygensia.feature.nearesttrashcan.FeatureNearestTrashCanModule
import id.radhika.lib.mvvm.BaseApplication

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Apr/2020
 **/
class MainApplication : BaseApplication() {

    override fun appendModule() = arrayListOf(
        FeatureUserModule.get(), FeatureHomeModule.get(), FeatureNearestTrashCanModule.get()
    ).also {
        it.forEach { it.baseUrl = { "https://hygensia.com/api/" } }
    }

    override fun onCreate() {
        super.onCreate()

        FeatureUserModule.get().apply {
            screenAfterLogin = { HomeActivity::class.java }
        }

        FeatureNearestTrashCanModule.get().apply {
            screenOpenLogin = { UserActivity::class.java }
        }
    }
}
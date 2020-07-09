package id.hygensia.feature.home.screen.home

import id.radhika.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class HomeVM : BaseVM<HomeDao>() {

    override suspend fun onCreate() {

    }

    override fun onCreateDao() = HomeDao()
}
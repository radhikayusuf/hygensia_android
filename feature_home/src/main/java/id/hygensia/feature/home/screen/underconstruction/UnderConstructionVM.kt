package id.hygensia.feature.home.screen.underconstruction


import id.radhika.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Aug/2020
 **/
class UnderConstructionVM : BaseVM<UnderConstructionDao>() {

    override suspend fun onCreate() {

    }

    override fun onCreateDao() = UnderConstructionDao()
}
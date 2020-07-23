package id.hygensia.feature.home.screen.home

import id.hygensia.feature.home.R
import id.hygensia.feature.home.databinding.ScreenHomeBinding
import id.hygensia.feature.nearesttrashcan.FeatureNearestTrashCanModule
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class HomeScreen : BaseScreen<ScreenHomeBinding, HomeVM, HomeDao>(
    ScreenHomeBinding::inflate
) {
    override fun onViewReady() {
        renderBottombar()
    }

    private fun renderBottombar() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.menu_account_home) {
                openActivity(this, FeatureNearestTrashCanModule.get().screenOpenLogin.invoke())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun render() = { data: HomeDao ->

    }

    override fun getViewModel() = HomeVM::class.java
}
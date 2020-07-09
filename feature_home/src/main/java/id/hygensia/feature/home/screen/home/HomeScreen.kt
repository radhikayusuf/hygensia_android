package id.hygensia.feature.home.screen.home

import id.hygensia.feature.home.databinding.ScreenHomeBinding
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class HomeScreen : BaseScreen<ScreenHomeBinding, HomeVM, HomeDao>(
    ScreenHomeBinding::inflate
) {
    override fun onViewReady() {

    }

    override fun render() = { data: HomeDao ->

    }

    override fun getViewModel() = HomeVM::class.java
}
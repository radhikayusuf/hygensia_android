package id.hygensia.feature.home.screen.underconstruction

import id.hygensia.feature.home.databinding.ScreenUnderconstructionBinding
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Aug/2020
 **/
class UnderConstructionScreen : BaseScreen<ScreenUnderconstructionBinding, UnderConstructionVM, UnderConstructionDao>(
    ScreenUnderconstructionBinding::inflate
) {
    override fun onViewReady() {

    }

    override fun getViewModel() = UnderConstructionVM::class.java

    override fun render() = { data: UnderConstructionDao ->

    }
}
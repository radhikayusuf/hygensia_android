package id.hygensia.feature.home.screen.home

import androidx.fragment.app.Fragment
import id.hygensia.feature.home.R
import id.hygensia.feature.home.databinding.ScreenHomeBinding
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.sheet.webview.WebViewSheetScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class HomeScreen : BaseScreen<ScreenHomeBinding, HomeVM, HomeDao>(
    ScreenHomeBinding::inflate
) {
    private val screenNearestTrash by lazy { parentFragmentManager.findFragmentById(R.id.nearestTrashFragment) }
    private val screenNewsLetter by lazy { parentFragmentManager.findFragmentById(R.id.articleFragment) }

    private var lastScreen: Fragment? = null

    override fun onViewReady() {
        if (lastScreen == null) lastScreen = screenNearestTrash
        openInnerScreen(lastScreen)
        renderBottombar()
    }

    override fun onResume() {
        super.onResume()
        openInnerScreen(lastScreen)
    }

    private fun renderBottombar() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_news_letter -> {
                    openInnerScreen(screenNewsLetter)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_upload_home -> {
                    WebViewSheetScreen.openSheet(this, isWebview = true, content = "https://docs.google.com/forms/d/e/1FAIpQLSeXop--4-9z7am0ta3X-RYvEs85x0D__OUCBFOOSc7OMi4RGA/viewform?usp=sf_link", title = "Daftarkan Tempat sampah")
                    return@setOnNavigationItemSelectedListener false
                }
                else -> {
                    openInnerScreen(screenNearestTrash)
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun render() = { data: HomeDao ->

    }

    override fun getViewModel() = HomeVM::class.java


    private fun openInnerScreen(screen: Fragment?) {
        lastScreen = screen
        parentFragmentManager.beginTransaction().also { frag ->
            screenNearestTrash?.let { frag.hide(it)}
            screenNewsLetter?.let { frag.hide(it) }
            screen?.let { frag.show(it) }
        }.commit()
    }
}
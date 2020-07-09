package id.hygensia.feature.home.screen

import android.os.Bundle
import id.hygensia.feature.home.R
import id.hygensia.feature.home.screen.home.HomeScreen
import id.radhika.lib.mvvm.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceScreen(HomeScreen())
    }

    override fun frameLayoutId() = R.id.frameHome
}

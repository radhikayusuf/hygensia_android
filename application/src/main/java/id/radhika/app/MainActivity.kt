package id.radhika.app

import android.content.Intent
import android.os.Bundle
import id.ethicstech.feature.user.screen.UserActivity
import id.hygensia.feature.home.screen.HomeActivity
import id.radhika.lib.mvvm.BaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    var job: Job? = null

    override fun frameLayoutId() = R.id.frameMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = GlobalScope.launch {
            delay(800)
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}

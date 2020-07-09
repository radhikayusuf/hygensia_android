package id.hygensia.feature.nearesttrashcan.screen

import android.os.Bundle
import id.hygensia.feature.nearesttrashcan.R
import id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan.NearestTrashCanScreen
import id.radhika.lib.mvvm.BaseActivity

class NearestTrashCanActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearest_trash_can)
        replaceScreen(NearestTrashCanScreen())
    }

    override fun frameLayoutId() = R.id.frameNearestTrashCan
}
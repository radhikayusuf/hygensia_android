package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.hygensia.feature.nearesttrashcan.R
import id.hygensia.feature.nearesttrashcan.databinding.ScreenNearestTrashCanBinding
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.util.showToast
import id.radhika.lib.ui.utils.addNegativeMargin
import id.radhika.lib.ui.utils.slideUpAnim
import id.radhika.lib.ui.R as Rlib

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanScreen :
    BaseScreen<ScreenNearestTrashCanBinding, NearestTrashCanVM, NearestTrashCanDao>(
        ScreenNearestTrashCanBinding::inflate
    ), OnMapReadyCallback, ViewPager.OnPageChangeListener {

    private var mGoogleMap: GoogleMap? = null
    private val nearestTrashPagerAdapter by lazy { NearestTrashCanPagerAdapter() }
    private val bottomBehavior by lazy { BottomSheetBehavior.from(binding.parentOfSheet) }

    override fun onViewReady() {
        renderMap()
        renderToolbar()
        renderBottomSheet()
        renderPagerLocation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState ?: Bundle())
    }

    override fun render() = { data: NearestTrashCanDao ->
        renderMarkers(data)
        renderListLocation(data)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-6.9041212, 107.6007542), 15f))
        vm.notifyMapIsReady()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    private fun renderMap() {
        MapsInitializer.initialize(requireContext())
        binding.mapView.getMapAsync(this@NearestTrashCanScreen)
    }

    private fun renderToolbar() {
        binding.toolbarNearest.showBackIcon(false)
        binding.toolbarNearest.showNotification(false)
        binding.toolbarNearest.title = "Hygensia"
        binding.toolbarNearest.setLabelGravity(Gravity.START)
        binding.toolbarNearest.forceGoneBack()
        binding.toolbarNearest.changeBackgroundColorRes(Rlib.color.colorWhite)
        binding.toolbarNearest.changeTitleColorRes(Rlib.color.colorTextPrimary)
        binding.toolbarNearest.showNotification(true) {
            showToast("Feature belum siap")
        }
    }

    private fun renderPagerLocation() {
        binding.pagerLocation.adapter = nearestTrashPagerAdapter
        binding.pagerLocation.clipChildren = false
        binding.pagerLocation.addNegativeMargin((18 * 3).toFloat())
        binding.pagerLocation.addOnPageChangeListener(this@NearestTrashCanScreen)
        nearestTrashPagerAdapter.setOnClickItem { i, trashCanModel ->
            mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(trashCanModel.location, 15f))
        }
    }

    private fun renderBottomSheet() {
        bottomBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.appBar.slideUpAnim(hide = true)
                        binding.mapView.isEnabled = false
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.appBar.slideUpAnim(hide = false)
                        binding.mapView.isEnabled = true
                        binding.scrollView.smoothScrollTo(0, 0)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }
        })
    }

    private fun renderListLocation(data: NearestTrashCanDao) {
        nearestTrashPagerAdapter.replaceAll(data.markers)
        binding.pagerLocation.offscreenPageLimit = data.markers.size
    }

    private fun renderMarkers(data: NearestTrashCanDao) {
        mGoogleMap?.clear()
        data.markers.forEach {
            val marker = MarkerOptions()
                .position(it.location)
                .title(it.title)
            mGoogleMap?.addMarker(marker)
        }
    }

    override fun getViewModel() = NearestTrashCanVM::class.java

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(vm.getSelectedLatLng(position), 15f))
    }
}
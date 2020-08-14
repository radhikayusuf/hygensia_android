package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.hygensia.feature.article.data.model.ArticleApiModel
import id.hygensia.feature.article.utils.ArticleStatics
import id.hygensia.feature.nearesttrashcan.databinding.ScreenNearestTrashCanBinding
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.sheet.webview.WebViewSheetScreen
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
        renderClickListener()
        renderCardContent()
    }

    private fun renderCardContent() {
        binding.cardGreen.setOnClickListener { onClickCardContent(ArticleStatics.CONTENT_HYGENSIA_ONBOARDING) }
        binding.cardRed.setOnClickListener { onClickCardContent(ArticleStatics.CONTENT_OUR_WORLD) }
        binding.cardYellow.setOnClickListener { onClickCardContent(ArticleStatics.CONTENT_JENIS_SAMPAH) }
        binding.cardBlue.setOnClickListener { onClickCardContent(ArticleStatics.CONTENT_LAUT_DAN_PLASTIK) }
    }

    private fun onClickCardContent(data: ArticleApiModel) {
        WebViewSheetScreen.openSheet(
            this,
            data.title.orEmpty(),
            data.content.orEmpty(),
            false,
            "",
            data.imageUrl.orEmpty()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState ?: Bundle())
    }

    override fun render() = { data: NearestTrashCanDao ->
        renderMarkers(data)
        renderListLocation(data)
        relocatingMapCamera(data)
        renderLoading(data)
    }

    private fun renderLoading(data: NearestTrashCanDao) {
        binding.mapView.visibility = if (data.isLoading) View.INVISIBLE else View.VISIBLE
        binding.pagerLocation.visibility = if (data.isLoading) View.GONE else View.VISIBLE
        binding.refreshButton.visibility = if (data.isLoading) View.GONE else View.VISIBLE

        binding.progressPager.visibility = if (!data.isLoading) View.GONE else View.VISIBLE
        binding.progressMap.visibility = if (!data.isLoading) View.GONE else View.VISIBLE
        binding.progressRetry.visibility = if (!data.isLoading) View.GONE else View.VISIBLE
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        vm.fetchNearestTrashCan()
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
            mGoogleMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    trashCanModel.location,
                    15f
                )
            )
        }
    }

    private fun renderClickListener() {
        binding.refreshButton.setOnClickListener { vm.fetchNearestTrashCan() }
        binding.cardAddTrash.setOnClickListener {
            WebViewSheetScreen.openSheet(
                this,
                isWebview = true,
                content = "https://docs.google.com/forms/d/e/1FAIpQLSeXop--4-9z7am0ta3X-RYvEs85x0D__OUCBFOOSc7OMi4RGA/viewform?usp=sf_link",
                title = "Daftarkan Tempat sampah"
            )
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
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {

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

    private fun relocatingMapCamera(data: NearestTrashCanDao) {
        if (mGoogleMap != null && data.initiateContent != null) {
            data.initiateContent?.location.let {
                mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15f))
            }
        }
    }

    override fun getViewModel() = NearestTrashCanVM::class.java

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        mGoogleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                vm.getSelectedLatLng(position),
                15f
            )
        )
    }
}
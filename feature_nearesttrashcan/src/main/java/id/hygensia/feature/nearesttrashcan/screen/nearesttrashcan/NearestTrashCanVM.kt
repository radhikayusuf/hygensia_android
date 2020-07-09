package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import id.hygensia.feature.nearesttrashcan.screen.data.TrashUseCase
import id.radhika.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanVM(
    private val trashUseCase: TrashUseCase = TrashUseCase.getInstance()
) : BaseVM<NearestTrashCanDao>() {

    private var mapIsReady = false

    override suspend fun onCreate() {
        fetchNearestTrashCan()
    }

    private suspend fun fetchNearestTrashCan() {
        dao.markers.clear()
        dao.markers.addAll(trashUseCase.getNearestTrashCan())
    }

    fun notifyMapIsReady() {
        mapIsReady = true
    }

    fun getSelectedLatLng(position: Int) = dao.markers[position].location

    override fun onCreateDao() = NearestTrashCanDao()
}
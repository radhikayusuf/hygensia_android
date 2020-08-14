package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import id.hygensia.feature.nearesttrashcan.data.TrashUseCase
import id.radhika.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanVM(
    private val trashUseCase: TrashUseCase = TrashUseCase.getInstance()
) : BaseVM<NearestTrashCanDao>() {

    override suspend fun onCreate() {

    }

    fun fetchNearestTrashCan() = launch {
        dao.isLoading = true
        dao.markers.clear()
        trashUseCase.getNearestTrashCan().let { result ->
            dao.markers.addAll(result)
            dao.initiateContent = result.firstOrNull()
        }
        dao.isLoading = false
    }

    fun getSelectedLatLng(position: Int) = dao.markers[position].location

    override fun onCreateDao() = NearestTrashCanDao()
}
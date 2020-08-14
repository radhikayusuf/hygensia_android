package id.hygensia.feature.nearesttrashcan.data.repository

import com.google.android.gms.maps.model.LatLng
import id.hygensia.feature.nearesttrashcan.data.TrashRepository
import id.hygensia.feature.nearesttrashcan.data.model.TrashCanModel
import id.hygensia.feature.nearesttrashcan.data.service.TrashService
import id.radhika.lib.data.api.ApiService
import id.radhika.lib.data.model.SimpleResult
import id.radhika.lib.data.model.getExceptionResponse

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Aug/2020
 **/
class TrashRepositoryImpl(
    private val trashService: TrashService = ApiService.createService(TrashService::class.java, "https://script.google.com/macros/s/")
) : TrashRepository {

    override suspend fun getNearestTrashCan(): SimpleResult<List<TrashCanModel>> {
        return try {
            return SimpleResult(
                true,
                trashService.getNearestTrashCan().map {
                    TrashCanModel(
                        it.name.orEmpty(),
                        it.desc.orEmpty(),
                        it.image.orEmpty(),
                        "",
                        LatLng(it.lat ?: 0.0, it.lang ?: 0.0)
                    )
                },
                "Success"
            )
        } catch (e: Exception) {
            getExceptionResponse<List<TrashCanModel>>(e).let {
                SimpleResult(
                    it.isSuccess,
                    it.data,
                    it.message
                )
            }
        }
    }

}
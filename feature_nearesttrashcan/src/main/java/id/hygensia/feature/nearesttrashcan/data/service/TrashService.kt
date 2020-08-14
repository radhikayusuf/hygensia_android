package id.hygensia.feature.nearesttrashcan.data.service

import id.hygensia.feature.nearesttrashcan.data.model.TrashCanApiModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Aug/2020
 **/
interface TrashService {

    @GET("AKfycbyLexGjfYuxgdcRQQ7Uy7xjpoj6HPv1TYvcilzjUxWTmh297tqA/exec")
    suspend fun getNearestTrashCan(
        @Query("type") type: String = "nearest"
    ): List<TrashCanApiModel>
}
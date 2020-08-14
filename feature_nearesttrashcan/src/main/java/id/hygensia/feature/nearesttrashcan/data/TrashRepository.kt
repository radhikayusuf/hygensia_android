package id.hygensia.feature.nearesttrashcan.data

import id.hygensia.feature.nearesttrashcan.data.model.TrashCanModel
import id.radhika.lib.data.model.SimpleResult

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Aug/2020
 **/
interface TrashRepository {

    suspend fun getNearestTrashCan(): SimpleResult<List<TrashCanModel>>
}
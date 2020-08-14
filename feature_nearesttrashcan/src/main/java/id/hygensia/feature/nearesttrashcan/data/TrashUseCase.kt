package id.hygensia.feature.nearesttrashcan.data

import id.hygensia.feature.nearesttrashcan.data.model.TrashCanModel
import id.hygensia.feature.nearesttrashcan.data.repository.TrashRepositoryImpl
import id.radhika.lib.data.source.UseCase
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Jul/2020
 **/
class TrashUseCase(
    private val trashRepository: TrashRepository = TrashRepositoryImpl()
) : UseCase {

    suspend fun getNearestTrashCan(): List<TrashCanModel> {
        val result = trashRepository.getNearestTrashCan()
        delay(1000)
        return result.data.orEmpty()
    }

    companion object {
        private val INSTANCE by lazy { TrashUseCase() }

        fun getInstance() = INSTANCE
    }
}
package id.hygensia.feature.nearesttrashcan.screen.data

import com.google.android.gms.maps.model.LatLng
import id.hygensia.feature.nearesttrashcan.screen.data.model.TrashCanModel
import id.radhika.lib.data.source.UseCase
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Jul/2020
 **/
class TrashUseCase : UseCase {

    suspend fun getNearestTrashCan(): List<TrashCanModel> {
        delay(1500)
        return listOf(
            TrashCanModel(
                "Tempat Sampah DAGO 1",
                "Berada di dekat Dago park",
                "",
                "",
                LatLng(-6.8973138,107.6134762)
            ),
            TrashCanModel(
                "Tempat Sampah DAGO 2",
                "Berada di dekat rumah sakit, pertigaan ITB",
                "",
                "",
                LatLng(-6.892658, 107.612775)
            ),
            TrashCanModel(
                "Tempat Sampah DAGO 3",
                "Berada di dekat MCD Dago",
                "",
                "",
                LatLng(-6.885558, 107.613619)
            ),
            TrashCanModel(
                "Tempat Sampah Dipatiukur 1",
                "Berada di Universitas Komputer Indonesia",
                "",
                "",
                LatLng(-6.886995, 107.615176)
            ),
            TrashCanModel(
                "Tempat Sampah Dipatiukur 2",
                "Berada di dekat SPBU dan cititrans",
                "",
                "",
                LatLng(-6.890545, 107.616650)
            )
        )
    }

    companion object {
        private val INSTANCE by lazy { TrashUseCase() }

        fun getInstance() = INSTANCE
    }
}
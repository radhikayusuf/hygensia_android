package id.hygensia.feature.nearesttrashcan.screen.data.model

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Jul/2020
 **/
data class TrashCanModel(
    val title: String,
    val desc: String,
    val image: String,
    val address: String,
    val location: LatLng
)
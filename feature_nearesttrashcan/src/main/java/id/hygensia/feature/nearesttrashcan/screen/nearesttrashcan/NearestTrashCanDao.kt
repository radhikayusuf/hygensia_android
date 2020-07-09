package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import id.hygensia.feature.nearesttrashcan.screen.data.model.TrashCanModel
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveListDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanDao : BaseDao() {

    val markers = LiveListDao<TrashCanModel>()

}
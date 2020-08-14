package id.hygensia.feature.nearesttrashcan.screen.nearesttrashcan

import id.hygensia.feature.nearesttrashcan.data.model.TrashCanModel
import id.lesprivate.lib.ui.utils.NullableLiveDao
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.LiveListDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 26/Jun/2020
 **/
class NearestTrashCanDao : BaseDao() {

    private val _isLoading = LiveDao(true)
    var isLoading by _isLoading::content

    val markers = LiveListDao<TrashCanModel>()

    private val _initiateContent = NullableLiveDao<TrashCanModel>(null)
    var initiateContent by _initiateContent::content

}
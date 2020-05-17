package com.miladheydari.interview.s780androidtest.ui.commodities

import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import com.miladheydari.interview.s780androidtest.utils.domain.Status

class CommodityViewState(
    val status: Status,
    val error: String? = null,
    val data: List<Commodity>? = null
) {
    fun isLoading() = status == Status.LOADING
}

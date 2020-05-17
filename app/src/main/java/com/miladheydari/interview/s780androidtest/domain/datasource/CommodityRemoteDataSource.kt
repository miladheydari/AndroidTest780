package com.miladheydari.interview.s780androidtest.domain.datasource

import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import com.miladheydari.interview.s780androidtest.domain.Api
import com.miladheydari.interview.s780androidtest.utils.extensions.iomain
import javax.inject.Inject

class CommodityRemoteDataSource @Inject constructor(private val api: Api) {

    fun getAllCommodity(body: List<Commodity>) = api.sendData(body).iomain()

}

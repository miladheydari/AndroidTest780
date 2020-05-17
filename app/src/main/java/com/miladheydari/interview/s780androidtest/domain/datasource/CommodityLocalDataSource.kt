package com.miladheydari.interview.s780androidtest.domain.datasource

import com.miladheydari.interview.s780androidtest.db.dao.CommodityDao
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import javax.inject.Inject

class CommodityLocalDataSource @Inject constructor(private val commodityDao: CommodityDao) {

    fun getAllCommodity() = commodityDao.getAllCommodity()
    fun insertAll(commodities: List<Commodity>) = commodityDao.insert(commodities)
    fun insert(commodity: Commodity) = commodityDao.insert(commodity)
    fun getCommodityById(id:Long) = commodityDao.getCommodityById(id)

}

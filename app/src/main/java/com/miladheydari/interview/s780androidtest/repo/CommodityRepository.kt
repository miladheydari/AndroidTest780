package com.miladheydari.interview.s780androidtest.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.miladheydari.interview.s780androidtest.core.Constants
import com.miladheydari.interview.s780androidtest.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import com.miladheydari.interview.s780androidtest.db.entity.FetchState
import com.miladheydari.interview.s780androidtest.domain.BaseApiResponse
import com.miladheydari.interview.s780androidtest.domain.datasource.CommodityLocalDataSource
import com.miladheydari.interview.s780androidtest.domain.datasource.CommodityRemoteDataSource
import com.miladheydari.interview.s780androidtest.utils.domain.RateLimiter
import com.miladheydari.interview.s780androidtest.utils.domain.Resource
import com.miladheydari.interview.s780androidtest.utils.extensions.iomain
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CommodityRepository @Inject constructor(
    private val commodityLocalDataSource: CommodityLocalDataSource,
    private val commodityRemoteDataSource: CommodityRemoteDataSource
) {

    private val rateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCommodity(
        fetchRequired: Boolean,
        body: List<Commodity>
    ): LiveData<Resource<List<Commodity>>> {
        return object :
            NetworkBoundResource<List<Commodity>, BaseApiResponse<List<Commodity>>>() {
            override fun saveCallResult(item: BaseApiResponse<List<Commodity>>) {


                item.response?.let {
                    commodityLocalDataSource.insertAll(it)
                }
            }

            override fun shouldFetch(data: List<Commodity>?): Boolean {
                return (fetchRequired)
            }

            override fun loadFromDb(): LiveData<List<Commodity>> {
                return commodityLocalDataSource.getAllCommodity()
            }

            override fun createCall(): Single<BaseApiResponse<List<Commodity>>> {
                return commodityRemoteDataSource.getAllCommodity(body)
            }

            override fun onFetchFailed() {
                rateLimit.reset(RATE_LIMITER_TYPE)
            }

        }.asLiveData
    }

    fun updateCommodityCategory(
        id: Long,
        categoryId: Long,
        categoryColor: String
    ): LiveData<Resource<Commodity>> {

        var commodity: Commodity? = null

        return object : NetworkBoundResource<Commodity, BaseApiResponse<Commodity>>() {
            override fun saveCallResult(item: BaseApiResponse<Commodity>) {
                item.response?.let {
                    it.fetchState = FetchState.SUCCESS
                    commodityLocalDataSource.insert(it)
                }
            }

            override fun shouldFetch(data: Commodity?): Boolean {
                data?.let { c ->

                    Single.fromCallable {

                        c.fetchState = FetchState.IN_PROGRESS
                        commodity = c

                        commodityLocalDataSource.insert(data)
                    }.iomain().subscribe { _ -> }
                }

                return true
            }

            override fun loadFromDb(): LiveData<Commodity> {
                return commodityLocalDataSource.getCommodityById(id)
            }

            override fun createCall(): Single<BaseApiResponse<Commodity>> {
                return Single.fromCallable {
                    val res = BaseApiResponse<Commodity>()
                    commodity?.categoryColor = categoryColor
                    res.response = commodity
                    return@fromCallable res
                }.delay(10, TimeUnit.SECONDS)
            }

            override fun onFetchFailed() {
                rateLimit.reset(RATE_LIMITER_TYPE)

            }
        }.asLiveData
    }
}

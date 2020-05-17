package com.miladheydari.interview.s780androidtest.repo

import androidx.lifecycle.LiveData
import com.miladheydari.interview.s780androidtest.core.Constants
import com.miladheydari.interview.s780androidtest.db.dao.CategoryDao
import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.domain.datasource.CategoryLocalDataSource
import com.miladheydari.interview.s780androidtest.utils.domain.RateLimiter
import com.miladheydari.interview.s780androidtest.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryLocalDataSource: CategoryLocalDataSource) {


    private val rateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCategory(): LiveData<Resource<List<Category>>> {
        return object :
            NetworkBoundResource<List<Category>, Unit>() {
            override fun saveCallResult(item: Unit) {


            }

            override fun shouldFetch(data: List<Category>?): Boolean {
                return false
            }

            override fun loadFromDb(): LiveData<List<Category>> {
                return categoryLocalDataSource.getAllCategory()
            }

            override fun createCall(): Single<Unit> {
                return Single.create<Unit> { }
            }

            override fun onFetchFailed() {
                rateLimit.reset(Constants.NetworkService.RATE_LIMITER_TYPE)
            }

        }.asLiveData
    }

}
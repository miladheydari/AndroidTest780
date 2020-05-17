package com.miladheydari.interview.s780androidtest.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import com.miladheydari.interview.s780androidtest.repo.CommodityRepository
import com.miladheydari.interview.s780androidtest.ui.categories.CategoryUpdateViewState
import com.miladheydari.interview.s780androidtest.ui.commodities.CommodityViewState
import com.miladheydari.interview.s780androidtest.utils.UseCaseLiveData
import com.miladheydari.interview.s780androidtest.utils.domain.Resource
import javax.inject.Inject

class CommodityUseCase @Inject internal constructor(
    private val repository: CommodityRepository
) : UseCaseLiveData<CommodityViewState, CommodityUseCase.CommodityParams,
        CommodityRepository>() {


    override fun buildUseCaseObservable(params: CommodityParams?): LiveData<CommodityViewState> {
        return repository.loadCommodity(
            body = params?.body ?: ArrayList(),
            fetchRequired = params?.fetchRequired ?: true
        ).map {
            onCommodityResultReady(it)
        }
    }

    fun updateCommodity(commodityUpdateParams: CommodityUpdateParams): LiveData<CategoryUpdateViewState> {
        return repository.updateCommodityCategory(
            id = commodityUpdateParams.commodityId,
            categoryId = commodityUpdateParams.categoryId,
            categoryColor = commodityUpdateParams.categoryColor
        ).map {
            onCategoryUpdateResultReady(it)
        }
    }

    private fun onCategoryUpdateResultReady(resource: Resource<Commodity>) =
        CategoryUpdateViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )

    private fun onCommodityResultReady(resource: Resource<List<Commodity>>): CommodityViewState {
        return CommodityViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class CommodityUpdateParams(val commodityId: Long, val categoryId: Long,val categoryColor:String) : Params()
    class CommodityParams(
        val body: List<Commodity>,
        val fetchRequired: Boolean
    ) : Params()

    override fun getRepository(): CommodityRepository {
        return repository
    }
}

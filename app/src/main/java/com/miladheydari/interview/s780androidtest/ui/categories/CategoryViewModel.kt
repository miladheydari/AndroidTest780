package com.miladheydari.interview.s780androidtest.ui.categories

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.miladheydari.interview.s780androidtest.core.BaseViewModel
import com.miladheydari.interview.s780androidtest.domain.usecase.CategoryUseCase
import com.miladheydari.interview.s780androidtest.domain.usecase.CommodityUseCase
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    commodityUseCase: CommodityUseCase,
    categoryUseCase: CategoryUseCase
) : BaseViewModel() {
    var commodityTitle: ObservableField<String> = ObservableField()
    var commodityBackground: ObservableField<String> = ObservableField()


    fun getCategoryViewState() = currentCategoryViewState

    private val currentCategoryViewState: LiveData<CategoryViewState> =
        categoryUseCase.execute(null)


    private val _commodityUpdateParams: MutableLiveData<CommodityUseCase.CommodityUpdateParams> =
        MutableLiveData()

    fun getCategoryUpdateViewState() = currentCategoryUpdateViewState

    private val currentCategoryUpdateViewState: LiveData<CategoryUpdateViewState> =
        _commodityUpdateParams.switchMap { params ->
            commodityUseCase.updateCommodity(params)
        }

    fun setCategoryUpdateParams(params: CommodityUseCase.CommodityUpdateParams) {
        if (_commodityUpdateParams.value == params)
            return
        _commodityUpdateParams.postValue(params)
    }


}
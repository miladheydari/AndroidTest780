package com.miladheydari.interview.s780androidtest.ui.commodities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.miladheydari.interview.s780androidtest.core.BaseViewModel
import com.miladheydari.interview.s780androidtest.domain.usecase.CommodityUseCase
import javax.inject.Inject

class CommodityViewModel @Inject constructor(commodityUseCase: CommodityUseCase) : BaseViewModel() {


    private val _commodityParams: MutableLiveData<CommodityUseCase.CommodityParams> = MutableLiveData()

    fun getCommodityViewState() = currentCommodityViewState

    private val currentCommodityViewState: LiveData<CommodityViewState> =
        _commodityParams.switchMap { params ->
            commodityUseCase.execute(params)
        }

    fun setCommodityParams(params: CommodityUseCase.CommodityParams) {
        if (_commodityParams.value == params)
            return
        _commodityParams.postValue(params)
    }



}
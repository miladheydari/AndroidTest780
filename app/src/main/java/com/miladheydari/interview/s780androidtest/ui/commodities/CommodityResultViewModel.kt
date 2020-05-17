package com.miladheydari.interview.s780androidtest.ui.commodities

import androidx.databinding.ObservableField
import com.miladheydari.interview.s780androidtest.core.BaseViewModel
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import javax.inject.Inject

class CommodityResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<Commodity>()
}
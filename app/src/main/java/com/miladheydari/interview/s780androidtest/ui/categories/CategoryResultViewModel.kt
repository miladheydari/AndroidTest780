package com.miladheydari.interview.s780androidtest.ui.categories

import androidx.databinding.ObservableField
import com.miladheydari.interview.s780androidtest.core.BaseViewModel
import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import javax.inject.Inject

class CategoryResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<Category>()
}
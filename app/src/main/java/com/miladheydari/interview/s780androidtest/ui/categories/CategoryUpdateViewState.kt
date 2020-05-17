package com.miladheydari.interview.s780androidtest.ui.categories

import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import com.miladheydari.interview.s780androidtest.utils.domain.Status

class CategoryUpdateViewState(
    val status: Status,
    val error: String? = null,
    val data: Commodity? = null
) {
    fun isLoading() = status == Status.LOADING
}
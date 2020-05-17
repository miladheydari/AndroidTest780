package com.miladheydari.interview.s780androidtest.ui.categories

import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.utils.domain.Status

class CategoryViewState(
    val status: Status,
    val error: String? = null,
    val data: List<Category>? = null
) {
    fun isLoading() = status == Status.LOADING
}
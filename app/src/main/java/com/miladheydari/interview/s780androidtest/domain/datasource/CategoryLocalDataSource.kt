package com.miladheydari.interview.s780androidtest.domain.datasource

import com.miladheydari.interview.s780androidtest.db.dao.CategoryDao
import javax.inject.Inject

class CategoryLocalDataSource @Inject constructor(private val categoryDao: CategoryDao) {

    fun getAllCategory() = categoryDao.getAllCategory()
}

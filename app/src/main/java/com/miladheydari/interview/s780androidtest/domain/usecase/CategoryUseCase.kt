package com.miladheydari.interview.s780androidtest.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.repo.CategoryRepository

import com.miladheydari.interview.s780androidtest.ui.categories.CategoryViewState

import com.miladheydari.interview.s780androidtest.utils.UseCaseLiveData
import com.miladheydari.interview.s780androidtest.utils.domain.Resource
import javax.inject.Inject

class CategoryUseCase @Inject internal constructor(
    private val repository: CategoryRepository
) : UseCaseLiveData<CategoryViewState, Unit,
        CategoryRepository>() {


    override fun buildUseCaseObservable(params: Unit?): LiveData<CategoryViewState> {
        return repository.loadCategory().map {
            onCategoryResultReady(it)
        }
    }

    private fun onCategoryResultReady(resource: Resource<List<Category>>): CategoryViewState {
        return CategoryViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }


    override fun getRepository(): CategoryRepository {
        return repository
    }

}

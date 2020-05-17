package com.miladheydari.interview.s780androidtest.ui.categories

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miladheydari.interview.s780androidtest.R
import com.miladheydari.interview.s780androidtest.core.BaseFragment
import com.miladheydari.interview.s780androidtest.databinding.FragmentCategoriesBinding
import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.di.Injectable
import com.miladheydari.interview.s780androidtest.domain.usecase.CommodityUseCase
import com.miladheydari.interview.s780androidtest.utils.extensions.observeWith
import com.miladheydari.interview.s780androidtest.utils.extensions.popBackStack

class CategoriesFragment :
    BaseFragment<CategoryViewModel, FragmentCategoriesBinding>(CategoryViewModel::class.java),
    Injectable {


    private val categoriesFragmentArgs: CategoriesFragmentArgs by navArgs()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_categories
    }

    override fun init() {
        super.init()
        initAdapter()
        setViewModelObserver()

    }


    private fun initCategoriesResultAdapter(results: List<Category>) {

        (mBinding.recyclerView.adapter as CategoryResultAdapter).submitList(results)

    }


    private fun setViewModelObserver() {


        viewModel.getCategoryViewState().observeWith(viewLifecycleOwner) {
            mBinding.viewState = it
            it.data?.let { results ->
                initCategoriesResultAdapter(results)
            }
        }

        viewModel.getCategoryUpdateViewState().observeWith(viewLifecycleOwner) {
            it.data?.let {
                popBackStack()
            }
        }

        viewModel.commodityBackground.set(categoriesFragmentArgs.commodityColor)
        viewModel.commodityTitle.set(categoriesFragmentArgs.commodityName)
    }

    private fun initAdapter() {


        val adapter = CategoryResultAdapter { item ->
            viewModel.setCategoryUpdateParams(
                CommodityUseCase.CommodityUpdateParams(
                    categoriesFragmentArgs.commodityId,
                    item.id, item.color
                )
            )
        }

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = layoutManager


    }

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }
}

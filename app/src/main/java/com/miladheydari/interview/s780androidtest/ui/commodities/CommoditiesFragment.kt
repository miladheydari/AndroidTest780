package com.miladheydari.interview.s780androidtest.ui.commodities

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.miladheydari.interview.s780androidtest.R
import com.miladheydari.interview.s780androidtest.core.BaseFragment
import com.miladheydari.interview.s780androidtest.core.Constants
import com.miladheydari.interview.s780androidtest.databinding.FragmentCommoditiesBinding
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import com.miladheydari.interview.s780androidtest.di.Injectable
import com.miladheydari.interview.s780androidtest.domain.usecase.CommodityUseCase
import com.miladheydari.interview.s780androidtest.utils.extensions.isNetworkAvailable
import com.miladheydari.interview.s780androidtest.utils.extensions.observeWith

class CommoditiesFragment :
    BaseFragment<CommodityViewModel, FragmentCommoditiesBinding>(CommodityViewModel::class.java),
    Injectable {

    var firstTime = true

    override fun init() {
        super.init()
        initAdapter()
        setViewModelObserver()
    }

    private fun setViewModelObserver() {

        viewModel.setCommodityParams(getParams())

        viewModel.getCommodityViewState().observeWith(viewLifecycleOwner) {
            mBinding.viewState = it
            it.data?.let { results ->
                initCommoditiesResultAdapter(results)
            }
        }
    }

    private fun getParams(): CommodityUseCase.CommodityParams {
        return CommodityUseCase.CommodityParams(
            body = Gson().fromJson(
                Constants.NetworkService.SERVER_RESPONSE_STRING,
                object : TypeToken<List<Commodity>>() {}.type
            ),

            fetchRequired = requireContext().isNetworkAvailable() && firstTime
        )
    }

    private fun initCommoditiesResultAdapter(results: List<Commodity>) {

        (mBinding.recyclerView.adapter as CommodityResultAdapter).submitList(results)
        firstTime = false
        viewModel.setCommodityParams(getParams())

    }

    private fun initAdapter() {

        val adapter = CommodityResultAdapter { item ->
            findNavController().navigate(
                CommoditiesFragmentDirections.actionCommoditiesFragmentToCategoriesFragment2(
                    item.id,
                    item.title,
                    item.categoryColor
                )
            )
        }

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = layoutManager

    }

    override fun getLayoutRes() = R.layout.fragment_commodities


    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }
}

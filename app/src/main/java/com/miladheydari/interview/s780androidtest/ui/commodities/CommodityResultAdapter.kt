package com.miladheydari.interview.s780androidtest.ui.commodities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.miladheydari.interview.s780androidtest.core.BaseAdapter
import com.miladheydari.interview.s780androidtest.databinding.ItemCommodityBinding
import com.miladheydari.interview.s780androidtest.db.entity.Commodity

class CommodityResultAdapter(
    private val callBack: (Commodity) -> Unit
) : BaseAdapter<Commodity>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemCommodityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = CommodityResultViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootItemView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemCommodityBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Commodity>() {
    override fun areContentsTheSame(oldItem: Commodity, newItem: Commodity): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: Commodity, newItem: Commodity): Boolean =
        oldItem.id == newItem.id
}

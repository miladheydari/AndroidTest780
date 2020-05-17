package com.miladheydari.interview.s780androidtest.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.miladheydari.interview.s780androidtest.core.BaseAdapter
import com.miladheydari.interview.s780androidtest.databinding.ItemCategoryBinding
import com.miladheydari.interview.s780androidtest.db.entity.Category

class CategoryResultAdapter(
    private val callBack: (Category) -> Unit
) : BaseAdapter<Category>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = CategoryResultViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootItemView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemCategoryBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Category>() {
    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.id == newItem.id
}

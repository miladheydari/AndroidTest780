package com.miladheydari.interview.s780androidtest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miladheydari.interview.s780androidtest.di.ViewModelFactory
import com.miladheydari.interview.s780androidtest.di.key.ViewModelKey
import com.miladheydari.interview.s780androidtest.ui.categories.CategoryResultViewModel
import com.miladheydari.interview.s780androidtest.ui.categories.CategoryViewModel
import com.miladheydari.interview.s780androidtest.ui.commodities.CommodityResultViewModel
import com.miladheydari.interview.s780androidtest.ui.commodities.CommodityViewModel
import com.miladheydari.interview.s780androidtest.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CommodityViewModel::class)
    abstract fun provideCommodityFragmentViewModel(splashCommodityViewModel: CommodityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CommodityResultViewModel::class)
    abstract fun provideCommodityResultViewModel(commodityResultViewModel: CommodityResultViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(CategoryResultViewModel::class)
    abstract fun provideCategoryResultViewModel(categoryResultViewModel: CategoryResultViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(CategoryViewModel::class)
    abstract fun provideCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel


}

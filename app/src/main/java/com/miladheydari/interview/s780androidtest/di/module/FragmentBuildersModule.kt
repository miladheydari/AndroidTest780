package com.miladheydari.interview.s780androidtest.di.module


import com.miladheydari.interview.s780androidtest.ui.categories.CategoriesFragment
import com.miladheydari.interview.s780androidtest.ui.commodities.CommoditiesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeCommoditiesFragment(): CommoditiesFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoriesFragment(): CategoriesFragment
}

package com.miladheydari.interview.s780androidtest.di.module


import com.miladheydari.interview.s780androidtest.di.scope.PerActivity
import com.miladheydari.interview.s780androidtest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {


    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity
}

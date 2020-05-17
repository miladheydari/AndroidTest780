package com.miladheydari.interview.s780androidtest.di.module

import android.content.Context

import com.miladheydari.interview.s780androidtest.db.AppDatabase
import com.miladheydari.interview.s780androidtest.db.dao.CategoryDao
import com.miladheydari.interview.s780androidtest.db.dao.CommodityDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
    @Provides
    @Singleton
    fun providesCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    @Singleton
    fun providesCommodityDao(db: AppDatabase): CommodityDao = db.commodityDao()
}

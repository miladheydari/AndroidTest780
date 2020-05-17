package com.miladheydari.interview.s780androidtest.di.component

import android.app.Application
import com.miladheydari.interview.s780androidtest.MyApplication
import com.miladheydari.interview.s780androidtest.di.module.*

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        NetModule::class,
        DatabaseModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(weatherApp: MyApplication)
}

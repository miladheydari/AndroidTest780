package com.miladheydari.interview.s780androidtest.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.miladheydari.interview.s780androidtest.R
import com.miladheydari.interview.s780androidtest.core.BaseActivity
import com.miladheydari.interview.s780androidtest.databinding.ActivityMainBinding
import com.miladheydari.interview.s780androidtest.utils.extensions.hide
import com.miladheydari.interview.s780androidtest.utils.extensions.show
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>
    (MainActivityViewModel::class.java) {


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector() = androidInjector

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.container_fragment)
        setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.CommoditiesFragment -> {
                    binding.appBarLayout.hide()
                }

                R.id.categoriesFragment -> {
                    setupToolbarTitleAndAction(getString(R.string.category), true)
                }
            }
        }
    }

    private fun setupToolbarTitleAndAction(title: String, isDisplayHomeAsUp: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeAsUp)
        viewModel.toolbarTitle.set(title)
        binding.appBarLayout.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container_fragment).navigateUp()
    }

}

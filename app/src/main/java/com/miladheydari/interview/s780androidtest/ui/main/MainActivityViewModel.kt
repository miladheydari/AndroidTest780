package com.miladheydari.interview.s780androidtest.ui.main

import androidx.databinding.ObservableField
import com.miladheydari.interview.s780androidtest.core.BaseViewModel

import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}

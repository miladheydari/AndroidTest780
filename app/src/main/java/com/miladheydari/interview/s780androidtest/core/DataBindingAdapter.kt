package com.miladheydari.interview.s780androidtest.core

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import com.miladheydari.interview.s780androidtest.utils.extensions.hide
import com.miladheydari.interview.s780androidtest.utils.extensions.show


@BindingAdapter("app:visibility")
fun setVisibilty(view: View, isVisible: Boolean) {
    view.hide()
    if (isVisible) {
        view.show()
    } else {
        view.hide()
    }

}


@BindingAdapter("app:setBackgroundColor")
fun setDrawableLink(view: View, color: String?) {
    if (color.isNullOrEmpty())
        return
    try {


        val intColor: Int = Color.parseColor(color)
        view.setBackgroundColor(intColor)
    }catch (t:Throwable){
        t.printStackTrace()
    }
}

package com.miladheydari.interview.s780androidtest.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.miladheydari.interview.s780androidtest.utils.extensions.alertDialog
import com.miladheydari.interview.s780androidtest.utils.extensions.toast

fun androidx.fragment.app.Fragment.toast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_SHORT
) = activity?.toast(message, duration)

fun Fragment.popBackStack() {
    NavHostFragment.findNavController(this).popBackStack()
}

inline fun androidx.fragment.app.Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) =
    activity?.alertDialog(body)

package com.miladheydari.interview.s780androidtest.utils

import android.content.Intent

interface ActivityResultCallback {
    fun onResult(requestCode: Int, resultCode: Int, data: Intent?)
}
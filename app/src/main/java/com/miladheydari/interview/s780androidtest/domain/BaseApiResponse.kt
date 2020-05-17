package com.miladheydari.interview.s780androidtest.domain

import com.google.gson.annotations.SerializedName

class BaseApiResponse<T> {
    @SerializedName("json")
    var response: T? = null
}
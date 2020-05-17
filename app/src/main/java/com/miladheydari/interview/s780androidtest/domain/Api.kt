package com.miladheydari.interview.s780androidtest.domain

import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("anything")
    fun sendData(@Body body: List<Commodity>): Single<BaseApiResponse<List<Commodity>>>

}
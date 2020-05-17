package com.miladheydari.interview.s780androidtest.utils.extensions

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> schedulers(): FlowableTransformer<T, T> = FlowableTransformer {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.iomain(): Flowable<T> = this.compose(schedulers())


fun <T> Single<T>.iomain(): Single<T> = this.compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}


fun <T> Observable<T>.iomain(): Observable<T> = this.compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.iomain(): Completable = this.compose {
    it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
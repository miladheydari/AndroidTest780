package com.miladheydari.interview.s780androidtest.db

import androidx.room.TypeConverter
import com.miladheydari.interview.s780androidtest.db.entity.FetchState

class Converters {
    @TypeConverter
    fun intToFetchState(value: Int?): FetchState? {
        if(value == null)
            return  null
        return FetchState.values()[value]
    }

    @TypeConverter
    fun fetchStateToInt(value: FetchState?): Int? {
        if(value == null)
            return  null

        return value.ordinal
    }
}
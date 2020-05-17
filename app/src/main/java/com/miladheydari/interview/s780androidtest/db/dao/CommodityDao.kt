package com.miladheydari.interview.s780androidtest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miladheydari.interview.s780androidtest.db.entity.Commodity


@Dao
interface CommodityDao {
    @Query("SELECT  * FROM commodity")
    fun getAllCommodity(): LiveData<List<Commodity>>


    @Query("SELECT  * FROM commodity WHERE id=:id")
    fun getCommodityById(id: Long): LiveData<Commodity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Commodity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<Commodity>)

    @Update
    fun update(obj: Commodity)


    @Update
    fun update(obj: List<Commodity>)

    @Delete
    fun delete(obj: Commodity)
}
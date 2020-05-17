package com.miladheydari.interview.s780androidtest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.db.entity.Commodity


@Dao
interface CategoryDao {
    @Query("SELECT  * FROM category")
    fun getAllCategory(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<Category>)

    @Update
    fun update(obj: Category)


    @Update
    fun update(obj: List<Category>)

    @Delete
    fun delete(obj: Category)

}
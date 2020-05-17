package com.miladheydari.interview.s780androidtest.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long, val title: String,
    val color: String
)

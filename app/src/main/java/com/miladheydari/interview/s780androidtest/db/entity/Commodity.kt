package com.miladheydari.interview.s780androidtest.db.entity

import androidx.appcompat.widget.DialogTitle
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "commodity")
data class Commodity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("categoryColor")
    var categoryColor: String,
    @Expose(serialize = false)
    var fetchState: FetchState?
)
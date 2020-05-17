package com.miladheydari.interview.s780androidtest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.miladheydari.interview.s780androidtest.db.dao.CategoryDao
import com.miladheydari.interview.s780androidtest.db.dao.CommodityDao
import com.miladheydari.interview.s780androidtest.db.entity.Category
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import java.util.*
import java.util.concurrent.Executors


@TypeConverters(Converters::class)
@Database(
    entities = [Category::class, Commodity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun commodityDao(): CommodityDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }

        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "star780.db"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //pre-populate data
                    Executors.newSingleThreadExecutor().execute {
                        INSTANCE?.let {
                            saveData(it)
                        }
                    }
                }
            })

                .build()

        private fun saveData(it: AppDatabase) {

            it.categoryDao().insert(
                listOf(
                    Category(0, "ورزشی", "#6abf69"),
                    Category(0, "پوشاک", "#f57f17"),
                    Category(0, "وسایل آشپزخانه", "#fdd835"),
                    Category(0, "موسیقی", "#1976d2")
                )
            )
        }

    }
}
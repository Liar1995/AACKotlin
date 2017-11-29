package com.sunmeng.aackotlin.data.local.db

import android.arch.persistence.room.*
import com.sunmeng.aackotlin.data.local.db.dao.GirlDao
import com.sunmeng.aackotlin.model.entity.Girl

/**
 * Created by sunmeng on 2017/11/28.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Database(entities = arrayOf(Girl::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun girlDao(): GirlDao
}
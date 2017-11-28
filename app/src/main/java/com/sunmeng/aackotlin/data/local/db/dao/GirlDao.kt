package com.sunmeng.aackotlin.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sunmeng.aackotlin.model.entity.Girl

/**
 * Created by sunmeng on 2017/11/28.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Dao
interface GirlDao {

    @Query("SELECT * FROM girls")
    fun loadAllGirls():List<Girl>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGirls(girls:List<Girl>)


}
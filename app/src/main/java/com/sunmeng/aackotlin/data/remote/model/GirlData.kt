package com.sunmeng.aackotlin.data.remote.model

import com.sunmeng.aackotlin.model.entity.Girl

/**
 * Created by sunmeng on 2017/11/27.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GirlData<T>(var error: Boolean, var results: T) {

    companion object {
        fun <T> content(data :T) : GirlData<T>{
            return GirlData(false,data)
        }
    }
}
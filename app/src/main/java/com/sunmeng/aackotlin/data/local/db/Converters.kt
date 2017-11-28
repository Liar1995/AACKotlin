package com.sunmeng.aackotlin.data.local.db

import android.arch.persistence.room.TypeConverter

/**
 * Created by sunmeng on 2017/11/28.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class Converters {

    companion object {
        @TypeConverter
        fun fromTimestamp(arrays: Array<String>): String {
            var stringBuilder = StringBuilder()
            for (s in arrays) {
                stringBuilder.append(s).append(",")
            }
            val length = stringBuilder.toString().length
            if (length > 0) {
                stringBuilder = stringBuilder.deleteCharAt(length - 1)
            }
            return stringBuilder.toString()
        }

        @TypeConverter
        fun dateToTimestamp(string: String): Array<String> {
            return string.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }
    }

}
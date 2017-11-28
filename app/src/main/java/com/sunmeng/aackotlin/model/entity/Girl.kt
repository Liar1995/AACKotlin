package com.sunmeng.aackotlin.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@Entity(tableName = "girls")
class Girl {

    @NonNull
    @PrimaryKey
    var id: String? = null

    var createdAt: String? = null

    var desc: String? = null

    var publishedAt: String? = null

    var source: String? = null

    var type: String? = null

    var url: String? = null

    var used: Boolean = false

    var who: String? = null

    var mAge: Int = 0



    constructor(_id: String?, createdAt: String?, desc: String?, publishedAt: String?, source: String?, type: String?, url: String?, used: Boolean, who: String?, mAge: Int) {
        this.id = _id
        this.createdAt = createdAt
        this.desc = desc
        this.publishedAt = publishedAt
        this.source = source
        this.type = type
        this.url = url
        this.used = used
        this.who = who
        this.mAge = mAge
    }

    constructor()
}
package com.sunmeng.aackotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sunmeng.aackotlin.R
import com.sunmeng.aackotlin.model.entity.Girl
import com.sunmeng.aackotlin.ui.listener.OnItemClickListener

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GirlListAdapter(private var mItemClick: OnItemClickListener<Girl>?, var mHeights: ArrayList<Int>) : RecyclerView.Adapter<GirlListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    private var mList: MutableList<Girl>? = null

    init {
        mList = ArrayList()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val girl: Girl = mList?.get(position) ?: return
        if (holder != null) {
            if (mHeights.size <= holder.position) {
                mHeights.add((200 + Math.random() * 300).toInt())
            }
            val lp = holder.mIVGirlAvatar?.layoutParams
            lp?.height = mHeights[holder.position]
            holder.mIVGirlAvatar?.layoutParams = lp
        }
        Glide.with(holder?.mIVGirlAvatar?.context).load(girl.url).into(holder?.mIVGirlAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.fragment_girl_list_item, parent, false))
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var mIVGirlAvatar: ImageView? = null
        init {
            mIVGirlAvatar = itemView?.findViewById(R.id.iv_girl_avatar)
        }
    }

    fun setGirlList(girlList: List<Girl>) {
        mList?.addAll(girlList)
        notifyDataSetChanged()
    }

    fun clearGirlList() {
        mList?.clear()
        notifyDataSetChanged()
    }


}
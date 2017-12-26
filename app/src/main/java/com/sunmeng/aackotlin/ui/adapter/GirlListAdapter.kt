package com.sunmeng.aackotlin.ui.adapter

import android.content.Context
import com.sunmeng.aackotlin.R
import com.sunmeng.aackotlin.databinding.FragmentGirlListItemBinding
import com.sunmeng.aackotlin.model.entity.Girl

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GirlListAdapter(private var mHeights: ArrayList<Int>, context: Context)
    : BaseViewAdapterImplBindItem<Girl, FragmentGirlListItemBinding>(context,R.layout.fragment_girl_list_item) {

    override fun onBindItem(binding: FragmentGirlListItemBinding, item: Girl, position: Int) {
        if (mHeights.size <= position) {
            mHeights.add((500 + Math.random() * 300).toInt())
        }
        val lp = binding.ivGirlAvatar.layoutParams
        lp?.height = mHeights[position]
        binding.ivGirlAvatar.layoutParams = lp
    }

}
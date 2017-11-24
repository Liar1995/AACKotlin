package com.sunmeng.aackotlin.ui.fragment

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.sunmeng.aackotlin.R
import com.sunmeng.aackotlin.app.App
import com.sunmeng.aackotlin.model.entity.Girl
import com.sunmeng.aackotlin.ui.activity.GirlActivity
import com.sunmeng.aackotlin.ui.adapter.GirlListAdapter
import com.sunmeng.aackotlin.ui.listener.OnItemClickListener
import com.sunmeng.aackotlin.utils.Util

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GirlListFragment : Fragment() {

    private var mRootView: RelativeLayout? = null
    private var mRefreshLayout: SwipeRefreshLayout? = null
    private var mLoadMore: ProgressBar? = null
    private var adapter: GirlListAdapter? = null

    //使用对象表达式创建匿名内部类实例
    private val mGirlClickListener = object : OnItemClickListener<Girl> {
        override fun onClick(t: Girl) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {//主动查询生命周期
                if (Util.isNetworkConnected(App.instance!!.applicationContext)) {
                    GirlActivity.startGirlActivity(activity, t.url!!)
                } else {
                    if (mRootView != null) {
                        Util.showSnackbar(mRootView!!, getString(R.string.network_error))
                    }
                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_girl_list, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        val mContext = view.context
        val mRecView: RecyclerView = view.findViewById(R.id.rv_girl_list)
        mRecView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        adapter = GirlListAdapter(mGirlClickListener)
        mRecView.adapter = adapter
        mRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastPosition : Int=layoutManager.findLastVisibleItemPosition()
                if (lastPosition==adapter!!.itemCount-1) {
                    //load next page
                }
            }
        })
        mRefreshLayout=view.findViewById(R.id.srl)
        mRefreshLayout?.setOnRefreshListener({
            adapter?.clearGirlList()
            mRefreshLayout?.isRefreshing=true
            //view model refresh data
        })
        mRefreshLayout?.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        mLoadMore=view.findViewById(R.id.load_more_bar)
        mRootView=view.findViewById(R.id.rl_girl_root)
    }

}
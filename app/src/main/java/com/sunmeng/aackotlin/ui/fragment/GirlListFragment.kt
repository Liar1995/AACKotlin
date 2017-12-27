package com.sunmeng.aackotlin.ui.fragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.sunmeng.aackotlin.R
import com.sunmeng.aackotlin.app.App
import com.sunmeng.aackotlin.data.Injection
import com.sunmeng.aackotlin.data.remote.model.GirlData
import com.sunmeng.aackotlin.model.entity.Girl
import com.sunmeng.aackotlin.ui.activity.GirlActivity
import com.sunmeng.aackotlin.ui.adapter.GirlListAdapter
import com.sunmeng.aackotlin.ui.listener.ItemClickPresenter
import com.sunmeng.aackotlin.ui.startGirlActivity
import com.sunmeng.aackotlin.utils.SpaceDecoration
import com.sunmeng.aackotlin.utils.Util
import com.sunmeng.aackotlin.viewmodel.GirlListViewModel

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class GirlListFragment : Fragment(), ItemClickPresenter<Girl> {

    override fun onItemClick(v: View?, item: Girl) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {//主动查询生命周期
            if (Util.isNetworkConnected(App.instance!!.applicationContext)) {
                startGirlActivity(activity!!, item.url!!,item._id!!,v!!)
            } else {
                if (mRootView != null) {
                    Util.showSnackbar(mRootView!!, getString(R.string.network_error))
                }
            }
        }
    }

    private val adapter: GirlListAdapter by lazy {
        GirlListAdapter(ArrayList(), context!!).apply {
            itemPresenter = this@GirlListFragment
        }
    }

    private var mRootView: RelativeLayout? = null
    private var mRefreshLayout: SwipeRefreshLayout? = null
    private var mLoadMore: ProgressBar? = null
    private var mGirlListViewModel: GirlListViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_girl_list, container, false)
        initView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        if (!isAdded) return
        val modelFactory = GirlListViewModel.Factory(App.instance!!, Injection.getDataRepository(App.instance!!)!!)
        mGirlListViewModel = ViewModelProviders.of(this, modelFactory).get(GirlListViewModel::class.java)
        mGirlListViewModel?.mGirls?.observe(this, Observer<GirlData<List<Girl>>> { t ->
            if (t == null || t.results!!.isEmpty()) {
                return@Observer
            }
            adapter.items.addAll(t.results!!)
        })

        mGirlListViewModel?.getLoadMoreState()?.observe(this, Observer<Boolean> { t ->
            if (t == null) return@Observer
            if (mRefreshLayout?.isRefreshing!!) {
                mRefreshLayout?.isRefreshing = false
            } else {
                mLoadMore?.visibility = if (t) View.VISIBLE else View.INVISIBLE
            }
        })

        mGirlListViewModel?.refreshGrilsData()
        mRefreshLayout?.isRefreshing = true
    }

    private fun initView(view: View) {
        val mRecView: RecyclerView = view.findViewById(R.id.rv_girl_list)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mRecView.layoutManager = staggeredGridLayoutManager

        val itemDecoration = SpaceDecoration(15)
        itemDecoration.setPaddingEdgeSide(true)
        mRecView.addItemDecoration(itemDecoration)
        mRecView.adapter = adapter
        mRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: StaggeredGridLayoutManager = recyclerView!!.layoutManager as StaggeredGridLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPositions(null)
                if (lastPosition[0] == adapter.itemCount - 1 || lastPosition[1] == adapter.itemCount - 1) {
                    mGirlListViewModel?.loadNextPageGirls()
                }

            }
        })
        mRefreshLayout = view.findViewById(R.id.srl)
        mRefreshLayout?.setOnRefreshListener({
            adapter.items.clear()
            mRefreshLayout?.isRefreshing = true
            mGirlListViewModel?.refreshGrilsData()
        })
        mLoadMore = view.findViewById(R.id.load_more_bar)
        mRootView = view.findViewById(R.id.rl_girl_root)
    }

}
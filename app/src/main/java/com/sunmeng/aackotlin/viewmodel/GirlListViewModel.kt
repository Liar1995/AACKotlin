package com.sunmeng.aackotlin.viewmodel

import android.arch.lifecycle.AndroidViewModel

/**
 * Created by sunmeng on 2017/11/24.
 * Email:sunmeng995@gmail.com
 * Description:因为ViewModel的生命周期是和Activity或Fragment分开的，
 * 所以在ViewModel中绝对不能引用任何View对象或者任何引用了Activity的Context的对象。
 * 如果ViewModel中需要Application的Context的话，可以继承AndroidViewModel
 */
class GirlListViewModel  {

}
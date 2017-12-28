package com.sunmeng.aackotlin.ui.annotation;

import android.support.annotation.IntDef;

import static com.sunmeng.aackotlin.ui.annotation.ToastType.ERROR;
import static com.sunmeng.aackotlin.ui.annotation.ToastType.NORMAL;
import static com.sunmeng.aackotlin.ui.annotation.ToastType.SUCCESS;
import static com.sunmeng.aackotlin.ui.annotation.ToastType.WARNING;


/**
 * 页面描述：ToastType
 *
 * Created by ditclear on 2017/10/11.
 */
@IntDef({ERROR,NORMAL,SUCCESS,WARNING})
public @interface ToastType {
    int ERROR=-2;
    int WARNING=-1;
    int NORMAL=0;
    int SUCCESS=1;
}

package com.luojilab.componentservice.share;

import android.app.Activity;

/**
 *  原生方式启动ShareActivity
 * </p>
 * created by OuyangPeng at 2018/1/15 上午 11:36
 */
public interface ShareService {
    void startActivity(Activity context, String bookName , String author);
    void startActivityForResult(Activity context,String bookName , String author , int requestCode);
}

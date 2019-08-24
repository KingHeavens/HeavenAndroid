package com.heaven.android.util;

import android.util.Log;

/**
 * 功能描述
 *
 * @author Aaron
 * @version 1.0
 * @since create time: 2019-08-24
 ***/
public class HLog {
    private static final String TAG = "[HeavenLog] ";

    public static void showLog(String log) {
        Log.e(TAG, log);
    }
}

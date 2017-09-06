package com.gitissue.android.gitissues.networking;

import android.util.Log;

/**
 * Created by Anuja on 9/6/17.
 */

public class NetworkError {
    private String mAppErrorMessage;

    public NetworkError(Throwable e) {
        e.printStackTrace();
        Log.e("error =>", e.toString());
    }

    public String getAppErrorMessage() {
        return mAppErrorMessage;
    }
}

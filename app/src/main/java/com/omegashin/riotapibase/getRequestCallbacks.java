package com.omegashin.riotapibase;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by gdesi on 15-Oct-16.
 */

interface getRequestCallbacks {

    void showResponse(Context context, String response);
    //Log.e(context.getString(R.string.LogTag),response);

    void returnVisual(Context context, Bitmap visual);
    //Log.e(context.getString(R.string.LogTag),response);
}

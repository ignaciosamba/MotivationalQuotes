package com.ignacio.motivationalquotes.Controller;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ignacio on 05/04/2018.
 */

public class QuotesController extends Application {

    public static final String TAG = QuotesController.class.getSimpleName();
    private static QuotesController mInstance;
    private RequestQueue mRequestQueue = null;

    public static synchronized QuotesController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public RequestQueue getToRequestQueue () {
        if(mRequestQueue == null){
            Log.d("SAMBA", "Creando Request de Volley");
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        //Set the default tag if the tag is empty.
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getToRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        //Set the default tag if the tag is empty.
        Log.d("SAMBA", "Into the addToRequestQueue");
        request.setTag(TAG);
        getToRequestQueue().add(request);
    }

    public void cancelPendingRequest (Object tag) {
        if (mRequestQueue != null && tag != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}

package com.app.elixir.brewz_rock_cafe.volly;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    // TAG for Volley
    public static final String TAG = "VolleySingleton";
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public VolleySingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        mImageLoader = new ImageLoader(this.mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(
                            10);

                    public void putBitmap(String url, Bitmap bitmap) {
                        mCache.put(url, bitmap);
                    }

                    public Bitmap getBitmap(String url) {
                        return mCache.get(url);
                    }
                });
    }

    public static VolleySingleton getInstance(Context context) {

        mInstance = new VolleySingleton(context);

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return this.mImageLoader;
    }

    // public <T> void addToRequestQueue(Request<T> req, String tag) {
    //
    // req.setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIMEOUT,
    // DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT));
    // // set the default tag if tag is empty
    // req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
    // VolleyLog.d("Adding request to queue: %s", req.getUrl());
    //
    // getRequestQueue().add(req);
    // }

    public <T> void addToRequestQueue(Request<T> req) {
        int SOCKET_TIMEOUT = 20000;
        int DEFAULT_MAX_RETRIES = 3;
        float DEFAULT_BACKOFF_MULT = 1f;
        // set the default tag if tag is empty
        req.setRetryPolicy(new MyRetryPolicy());
        req.setTag(TAG);
        System.setProperty("http.keepAlive", "false");
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueueForUploadData(Request<T> req) {

        // set the default tag if tag is empty
        // Create speciqal method for upload seenclient data. with below
        // method we can avoid retry of webservcie call
        req.setRetryPolicy(new MyRetryPolicy());
        req.setTag(TAG);
        System.setProperty("http.keepAlive", "false");
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }



    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }




    // Create special class for upload seenclient data. with below
    // method we can avoid retry of webservcie call
    class MyRetryPolicy implements RetryPolicy {
        public static final int SOCKET_TIMEOUT = 60000;
        public static final int DEFAULT_MAX_RETRIES = 0;
        public static final float DEFAULT_BACKOFF_MULT = 0;

        @Override
        public int getCurrentRetryCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getCurrentTimeout() {
            // TODO Auto-generated method stub
            return SOCKET_TIMEOUT;
        }

        @Override
        public void retry(VolleyError error) throws VolleyError {
            throw error;

        }
    }

}
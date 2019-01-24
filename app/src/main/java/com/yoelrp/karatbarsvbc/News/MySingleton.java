package com.yoelrp.karatbarsvbc.News;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by YoelRP on 15/01/2019.
 */

public class MySingleton {
        private static MySingleton sInstance;
        private RequestQueue requestQueue;
        private static Context mCtx;

        public MySingleton(Context context) {
            mCtx=context;
            requestQueue=getRequestQueue();
        }

        public RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return requestQueue;
        }
        public static synchronized MySingleton getsInstance(Context context){
            if(sInstance==null){
                sInstance=new MySingleton(context);
            }
            return sInstance;
        }
        public <T> void addToRequestque(Request<T> request){
            requestQueue.add(request);
        }
}

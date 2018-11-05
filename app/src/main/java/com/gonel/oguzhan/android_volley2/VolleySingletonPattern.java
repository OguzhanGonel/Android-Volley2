package com.gonel.oguzhan.android_volley2;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingletonPattern {

    // Instance of the VolleySingletonPattern class
    private static VolleySingletonPattern mInstance;

    // The Request Queue
    private RequestQueue requestQueue;

    // The Context to be used in this class
    private static Context mContext;

    private VolleySingletonPattern(Context context){

        // We make mContext equal to the context that is passed through during initialization of the constructor
        mContext = context;

        // We call getRequestQueue, if the requestQueue has been already initialized, then it just returns it.
        // Otherwise, it will return us a new requestQueue
        requestQueue = getRequestQueue();
    }



    // Using the same getApplication Context ensures a single Request Queue and that same context will
    // last us the Application Lifetime
    // The below function returns our Request Queue, ensuring only one Request Queue during the lifetime of the application
    public RequestQueue getRequestQueue(){

        // Check if the requestQueue has been initialized
        if (requestQueue == null){

            // This particular instance used below in mContext will last us the lifetime of our application
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    // Gets an instance of the VolleySingletonPattern class
    public static synchronized VolleySingletonPattern getInstance(Context Context){

        // Check if the instance exists, if not initialize it properly
        if(mInstance==null){
            mInstance = new VolleySingletonPattern(Context);
        }
        return mInstance;
    }

    // Adds the request to the request queue
    public<T> void addToRequestQueue(Request request){
        requestQueue.add(request);
    }
}

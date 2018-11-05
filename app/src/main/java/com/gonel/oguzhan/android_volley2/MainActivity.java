package com.gonel.oguzhan.android_volley2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String url = "http://ogwebdesign.ca/ogtesting.php";

    TextView textView;

    RequestQueue requestQueue;
    Cache cache;
    Network network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txtView);

        // Since we are implementing our own Request Queue, we have to make our own cache
        cache = new DiskBasedCache(getCacheDir(), 1024*1024);

        // The Network
        network = new BasicNetwork(new HurlStack());

        // Initialize the Request Queue, takes the cache and network as parameters
        requestQueue = new RequestQueue(cache, network);

        // Start the Request Queue
        requestQueue.start();
    }

    public void serverButtonFunction(View view){

        Log.d("serverButton", "Server Button has been clicked");

        // The below Request Queue is no longer used as I created my own Request Queue
        // requestQueue = Volley.newRequestQueue(MainActivity.this);

        // I make a String Request to get from the server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error in Volley Request: " + error);
                error.printStackTrace();
                requestQueue.stop();
            }
        });

        requestQueue.add(stringRequest);
    }

    public void gotoSingletonActivity(View view){

        Log.d("gotoSingleButton", "The go to Singleton Activity button has been clicked");
        Intent intent = new Intent(MainActivity.this, SingletonActivity.class);
        startActivity(intent);
    }
}

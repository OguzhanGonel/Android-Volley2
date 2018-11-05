package com.gonel.oguzhan.android_volley2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/*
This class uses the Singleton Pattern for Volley
 */


public class SingletonActivity extends AppCompatActivity {

    private String url = "http://ogwebdesign.ca/ogtesting.php";

    TextView textView;

    private final String requestResponse = "REQUEST_RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);


        textView = (TextView) findViewById(R.id.singletonTextView);

    }

    public void singletonRequestFunction(View view){

        Log.d("SingletonButton", "The Singleton Button has been clicked");

        StringRequest stringRequest =  new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(requestResponse, "The request is successfull");

                        textView.setText(response);
                    }
                },

                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(requestResponse, "The request failed, error is: " + error);
                textView.setText("Request Error: " + error);
                error.printStackTrace();
            }
        });

        // Create an instance of the Request Queue using the Singleton Pattern
        VolleySingletonPattern.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}

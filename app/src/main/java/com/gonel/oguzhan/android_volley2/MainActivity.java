package com.gonel.oguzhan.android_volley2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String url = "http://ogwebdesign.ca/ogtesting.php";

    TextView textView;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txtView);
    }

    public void serverButtonFunction(View view){

        Log.d("serverButton", "Server Button has been clicked");

        requestQueue = Volley.newRequestQueue(MainActivity.this);

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
}

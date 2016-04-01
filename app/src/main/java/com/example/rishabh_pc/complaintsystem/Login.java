package com.example.rishabh_pc.complaintsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class Login extends AppCompatActivity {

    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "session_id_com";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void login(View view) {
        String username = ((TextView) findViewById(R.id.usnm)).getText().toString();
        String password = ((TextView) findViewById(R.id.pass)).getText().toString();
        String url = "http://192.168.137.1:8000/com/default/login.json?userid=" + username + "&password="+ password;
        Log.d("url", url);
        final Intent intent = new Intent(this, Overview.class);
        MyJsonRequest request = new MyJsonRequest(url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("success").equals("true")) {
                        JSONObject user = response.getJSONObject("user");
                        intent.putExtra("username", user.getString("username"));
                        intent.putExtra("type", user.getString("abc"));
                        intent.putExtra("level", user.getString("level"));
                        intent.putExtra("id", user.getString("id"));
                        intent.putExtra("full_name", user.getString("full_name"));
                        startActivity(intent);
                    }
                } catch(Exception e) {}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Login", "some error");

            }
        });


        Singleton.getInstance().addToRequestQueue(request);

//        StringRequest req = new StringRequest(Request.Method.GET, url, null, new Response.Listener<String>() {
//
//        @Override
//            public void onResponse(String response) {
//                ((TextView)findViewById(R.id.usnm)).setText(response.toString());
//                Log.d("response", "hua ab");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Login", "some error");
//
//            }
//        });
//        Singleton.getInstance().addToRequestQueue(req);

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }



}

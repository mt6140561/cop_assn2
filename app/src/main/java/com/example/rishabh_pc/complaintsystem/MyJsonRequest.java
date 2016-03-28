package com.example.rishabh_pc.complaintsystem;

import android.app.DownloadManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rishab-pc on 27-Mar-16.
 */
public class MyJsonRequest extends JsonObjectRequest {
    public Map<String, String> params = new HashMap<String, String>();

    public MyJsonRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        // since we don't know which of the two underlying network vehicles
        // will Volley use, we have to handle and store session cookies manually
        Log.d("JsonObj", "netresponse = " + response.toString());
        Singleton.getInstance().checkSessionCookie(response.headers);
        Log.d("JsonObj", "sessioncheck");
        return super.parseNetworkResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        Singleton.getInstance().addSessionCookie(headers);
        Log.d("JsonObj", "add");
        return headers;
    }
}

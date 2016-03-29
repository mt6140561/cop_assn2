package com.example.rishabh_pc.complaintsystem;

import android.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rishab-pc on 29-Mar-16.
 */
public class complainOnClick implements View.OnClickListener {
    public String cid;
    public FragmentManager fm;
    public complainOnClick(String cid, FragmentManager fm) {
        this.cid = cid;
        this.fm = fm;
    }

    public void onClick(View v) {
        String url = "http://192.168.137.1:8000/com/complaints/complaint.json/"+cid;
        Log.d("complainOnClick", "worked");
        MyJsonRequest req = new MyJsonRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject json = response.getJSONObject("complaint");
                    ArrayList<String> send = new ArrayList<String>();
                    send.add(json.getString("user_id"));
                    send.add(json.getString("level"));
                    send.add(json.getString("created_at"));
                    send.add(json.getString("title"));
                    send.add(json.getString("resolve_id"));
                    send.add(json.getString("statement"));
                    send.add(json.getString("resolve_bool"));
                    send.add(json.getString("id"));
                    fm.beginTransaction()
                            .replace(R.id.blanklayout, new compldetail().newInstance(send)).addToBackStack(null)
                            .commit();
                } catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Singleton.getInstance().addToRequestQueue(req);
    }
}

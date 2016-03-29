package com.example.rishabh_pc.complaintsystem;

import android.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
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
                    final ArrayList<String> send = new ArrayList<String>();
                    send.add(json.getString("user_id"));
                    send.add(json.getString("level"));
                    send.add(json.getString("created_at"));
                    send.add(json.getString("title"));
                    send.add(json.getString("resolve_id"));
                    send.add(json.getString("statement"));
                    send.add(json.getString("resolve_bool"));
                    send.add(json.getString("id"));
                    final JSONArray comments = response.getJSONArray("comments");


                    String url2 = "http://192.168.137.1:8000/com/user/user.json/"+json.getString("user_id");
                    Log.d("Userinfo", url2);
                    MyJsonRequest req1 = new MyJsonRequest(url2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                ArrayList<String[]> sendcom = new ArrayList<String[]>();
                                for (int i=0; i<comments.length(); i++) {
                                    String[] comarr = new String[3];
                                    comarr[0] = comments.getJSONObject(i).getString("full_id");
                                    comarr[1] = comments.getJSONObject(i).getString("text");
                                    comarr[2] = comments.getJSONObject(i).getString("created_at");
                                    sendcom.add(comarr);
                                }
                                JSONObject userinfo = response.getJSONObject("user");
                                send.add(userinfo.getString("full_name"));
                                Log.d("send size chu", send.size()+"");
                                fm.beginTransaction()
                                        .replace(R.id.blanklayout, new compldetail().newInstance(send, sendcom)).addToBackStack(null)
                                        .commit();
                            } catch (Exception e) {Log.d("exception userinfo", e.getMessage().toString());}
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    Singleton.getInstance().addToRequestQueue(req1);

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

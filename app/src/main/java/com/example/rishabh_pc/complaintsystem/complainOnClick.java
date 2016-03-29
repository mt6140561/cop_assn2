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
        String url = "http://192.168.56.1:8000/com/complaints/complaint.json/"+cid;
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
                    JSONArray comments = response.getJSONArray("comments");
                    final ArrayList<String[]> comment = new ArrayList<>();
                    for (int i=0; i<comments.length(); i++) {
                        final String[] arr = new String[3];
                        String uid = comments.getJSONObject(i).getString("user_id");
                        String urlu = "http://192.168.137.1:8000/com/user/user.json/"+uid;
                        Log.d("Userinfo", urlu);
                        MyJsonRequest requ = new MyJsonRequest(urlu, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    arr[0] = response.getString("full_name");

                                    } catch (Exception e) {}
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                        Singleton.getInstance().addToRequestQueue(requ);
                        Log.d("commuser", arr[0]);
                        arr[1] = comments.getJSONObject(i).getString("text");
                        arr[2] = comments.getJSONObject(i).getString("created_at");
                        comment.add(arr);
                    }
                    String url2 = "http://192.168.137.1:8000/com/user/user.json/"+json.getString("user_id");
                    Log.d("Userinfo", url2);
                    MyJsonRequest req1 = new MyJsonRequest(url2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject userinfo = response.getJSONObject("user");
                                send.add(userinfo.getString("full_name"));
                                Log.d("send size", send.size()+"");
                                fm.beginTransaction()
                                        .replace(R.id.blanklayout, new compldetail().newInstance(send,comment)).addToBackStack(null)
                                        .commit();
                            } catch (Exception e) {}
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

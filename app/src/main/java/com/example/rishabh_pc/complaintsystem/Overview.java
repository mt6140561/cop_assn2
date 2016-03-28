package com.example.rishabh_pc.complaintsystem;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
public class Overview extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String username;
    public String type;
    public String level;
    public String uid;
    public String full_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        Bundle bundle = getIntent().getExtras();
        String abc =bundle.size()+"";
        Log.d("length", abc);
        username = bundle.getString("username");
        type = bundle.getString("type");
        level = bundle.getString("level");
        uid = bundle.getString("id");
        full_name = bundle.getString("full_name");
        ((TextView)findViewById(R.id.welcome)).setText("Welcome "+full_name);
    }

    @Override


    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }else{
            super.onBackPressed();
        }
    }}


    public void notif(View v) {
        final FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        final noti allg = new noti();
        String url = "http://192.168.56.1:8000/com/default/notifications.json";
        Log.d("frag", "yeh bhi hua");
        MyJsonRequest jobjre = new MyJsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
   @Override
            public void onResponse(JSONObject response) {
                String[][] star = convertfornoti(response);
                Log.d("abc", star.length + "");
                Fragment ret = allg.newInstance(star);


                fm.beginTransaction()
                        .replace(R.id.blanklayout, ret).addToBackStack(null)
                        .commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "yeh hua");
            }
        });

        Singleton.getInstance().addToRequestQueue(jobjre);


    }


    public void allcomplaints(View v) {
        final FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        final allcomplaints allg = new allcomplaints();
        String url = "http://192.168.56.1:8000/com/complaints/list.json";
        Log.d("frag", "yeh bhi hua");
        MyJsonRequest jobjre = new MyJsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String[][] star = convertforcomplaints(response);
                Log.d("abc", star.length + "");
                Fragment ret = allg.newInstance(star);


                fm.beginTransaction()
                        .replace(R.id.blanklayout, ret).addToBackStack(null)
                        .commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "yeh hua");
            }
        });

        Singleton.getInstance().addToRequestQueue(jobjre);


    }


    private String[][] convertforcomplaints(JSONObject json) {
        try {
            JSONArray arr1 = json.getJSONArray("complaintsinsti");
            JSONArray arr2 = json.getJSONArray("complaintssamelevel");
            JSONArray arr3 = json.getJSONArray("complaintsself");
            String[][] ret = new String[arr1.length()+arr2.length()+arr3.length()+1][4];
            if((arr1.length()+arr2.length()+arr3.length())==0){
                String[][] q=new String[1][1];
                q[0][0]="No Complaints";
                return q;
            }
            ret[0][0]="Complaints_id";
            ret[0][1]="Title";
            ret[0][2]="Resolved";
            ret[0][3]="Level";
            for (int i = 1; i < arr1.length()+1; i++) {

                ret[i][0]=arr1.getJSONObject(i-1).getString("id");
                ret[i][1]=arr1.getJSONObject(i-1).getString("title");

                ret[i][2]=arr1.getJSONObject(i-1).getString("resolve_bool");
                ret[i][3]=arr1.getJSONObject(i-1).getString("level");

            }

            for (int i = 0; i < arr2.length(); i++) {

                ret[i+arr1.length()+1][0]=arr2.getJSONObject(i).getString("id");
                ret[i+arr1.length()+1][1]=arr2.getJSONObject(i).getString("title");

                ret[i+arr1.length()+1][2]=arr2.getJSONObject(i).getString("resolve_bool");
                ret[i+arr1.length()+1][3]=arr2.getJSONObject(i).getString("level");


            }

            for (int i = 0; i < arr3.length(); i++) {

                ret[i+arr1.length()+1+ arr2.length()][0]=arr3.getJSONObject(i).getString("id");
                ret[i+arr1.length()+1+ arr2.length()][1]=arr3.getJSONObject(i).getString("title");

                ret[i+arr1.length()+1+ arr2.length()][2]=arr3.getJSONObject(i).getString("resolve_bool");

                ret[i+arr1.length()+1+ arr2.length()][3]=arr3.getJSONObject(i).getString("level");


            }





            return ret;
        } catch (Exception e) {
            Log.d("Excep", e.getMessage().toString());
            return null;
        }
    }




////bbbbb


    private String[][] convertfornoti(JSONObject json) {
        try {
            JSONArray arr = json.getJSONArray("notifications");
            String[][] ret = new String[arr.length()][1];
            if(arr.length()==0){
                String[][] q=new String[1][1];
                q[0][0]="No new Notification";
                return q;
            }


            for (int i = 0; i < arr.length(); i++) {
                String xx = arr.getJSONObject(i).getString("descr_bool");
                String abc= arr.getJSONObject(i).getString("complaint_id");




                    if(xx.equals("0")){
                ret[i][0]= arr.getJSONObject(i).getString("created_at")+"   New Complaint posted   ";}
                else {
                    if (xx.equals("1")) {
                        ret[i][0] = arr.getJSONObject(i).getString("created_at") + "   complaint resolved";
                    }
                }
            }
            return ret;
        } catch (Exception e) {
            Log.d("Excep", e.getMessage().toString());
            return null;
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Notification) {
            // Handle the camera action
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

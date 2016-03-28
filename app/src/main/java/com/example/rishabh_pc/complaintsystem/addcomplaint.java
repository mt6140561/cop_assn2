package com.example.rishabh_pc.complaintsystem;


import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addcomplaint.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addcomplaint#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addcomplaint extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String hostel;
    private String type;

    private OnFragmentInteractionListener mListener;

    public addcomplaint() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment addcomplaint.
     */
    // TODO: Rename and change types and number of parameters
    public static addcomplaint newInstance(String param1, String param2) {
        addcomplaint fragment = new addcomplaint();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hostel = getArguments().getString(ARG_PARAM1);
            type = (getArguments().getString(ARG_PARAM2)).toLowerCase();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View va = inflater.inflate(R.layout.fragment_addcomplaint, container, false);
        final Spinner level = (Spinner)va.findViewById(R.id.spinner);
        List<String> levels = new ArrayList<String>();
        levels.add("Individual");
        levels.add("Institute");
        if (!type.equals("student")) {}
        else {
            String ostel = hostel.substring(1);
            String hoste = hostel.substring(0, 1).toUpperCase()+ostel;
            levels.add(hoste);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, levels);
        level.setAdapter(adapter);
        int pos = adapter.getPosition("Individual");
        level.setSelection(pos);
        Button post = (Button) va.findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = va;
                String title = ((EditText)v.findViewById(R.id.titl)).getText().toString();
                String descr = ((EditText)v.findViewById(R.id.descr)).getText().toString();
                String leve = level.getSelectedItem().toString().toLowerCase();
                if (leve.equals("institute")) {leve = "insti";}
                String url = "http://192.168.137.1:8000/com/complaints/new.json?title="+title+"&description="+descr+"&level="+leve;
                Log.d("addcomp", url);
                MyJsonRequest req = new MyJsonRequest(url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("addcomplaint", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Singleton.getInstance().addToRequestQueue(req);
            }
        });
        return va;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

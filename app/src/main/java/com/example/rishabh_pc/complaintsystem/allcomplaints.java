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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link allcomplaints.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link allcomplaints#newInstance} factory method to
 * create an instance of this fragment.
 */

public class allcomplaints extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public allcomplaints() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment allcomplaints.
     */


    private ArrayList<String[]> Param1;

    // TODO: Rename and change types and number of parameters
    public static allcomplaints newInstance(String[][] param) {

        allcomplaints fragment = new allcomplaints();
        Bundle args = new Bundle();
        String tag;
        for (int i = 0; i < param.length; i++) {
            tag = "c" + i;
            args.putStringArray(tag, param[i]);
        }
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Param1 = new ArrayList<>();
        if (getArguments() != null) {
            int i = 0;
            String tag = "c" + i;
            while (getArguments().getStringArray(tag) != null) {
                Param1.add(getArguments().getStringArray(tag));
                Log.d("each", getArguments().getStringArray(tag).length + "");
                i = i + 1;
                tag = "c" + i;
            }

        }
    }

    public static String[][] swipe(String s[][],int n){
        for(int i=0; i<s[n].length; i++){
            String temp;
            temp=s[n][i];
            s[n][i]=s[n+1][i];
            s[n+1][i]=temp;
        }
        return s;
    }
    public static String[][] sort(String s[][]){
        for(int i=1; i<s.length; i++){
            for(int j=1; j<s.length-1; j++){
                if(Integer.parseInt(s[j][0])>Integer.parseInt(s[j+1][0])){
                    s=swipe(s,j);

                }
            }
        }
        return s;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_allcomplaints, container, false);
        TableLayout table = (TableLayout) v.findViewById(R.id.table2);
        FragmentManager fm = getFragmentManager();
        TableRow.LayoutParams rowparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < Param1.size(); i++) {
            TableRow row = new TableRow(this.getActivity());
            row.setLayoutParams(rowparams);
//            final String cid = Param1.get(i)[0];


            String[] read1 = Param1.get(i);

            if (read1.length == 1) {
                TextView tes = new TextView(this.getActivity());
                tes.setText(read1[0]);
                tes.setTextSize(17);
                row.addView(tes);
                table.addView(row);
            } else {
                row.setOnClickListener(new complainOnClick(Param1.get(i)[4], getFragmentManager()));
                for (int j = 0; j < 5; j++) {
                    TextView tes = new TextView(this.getActivity());
                    String addt = read1[j]+"      ";
                    tes.setText(addt);
                    tes.setTextSize(18);

                    tes.setPaddingRelative(20, 0, 0, 0);
                    row.addView(tes);
                }


                table.addView(row);

            }

        }
        return v;
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

package com.example.rishabh_pc.complaintsystem;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link compldetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link compldetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class compldetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String> send;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public compldetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * //     *
     * //     * @param param1 Parameter 1.
     * //     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment compldetail.
     */
    // TODO: Rename and change types and number of parameters
    public static compldetail newInstance(ArrayList<String> send) {
        compldetail fragment = new compldetail();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, send);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            send = getArguments().getStringArrayList(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View va = inflater.inflate(R.layout.fragment_compldetail, container, false);
        ((TextView) va.findViewById(R.id.compby)).setText(send.get(0));
        ((TextView) va.findViewById(R.id.complevel)).setText(send.get(1));
        ((TextView) va.findViewById(R.id.compcre)).setText(send.get(2));
        ((TextView) va.findViewById(R.id.comptitl)).setText(send.get(3));
        ((TextView) va.findViewById(R.id.compresid)).setText(send.get(4));
        ((TextView) va.findViewById(R.id.compdescr)).setText(send.get(5));
        ((TextView) va.findViewById(R.id.compresbool)).setText(send.get(6));
        ((TextView) va.findViewById(R.id.compid)).setText(send.get(7));

        if (send.get(10).equals("true")) {
            String abc = "upvoted";
            ((TextView) va.findViewById(R.id.compstatus)).setText(abc);
        } else {

            if (send.get(11).equals("true")) {
                String abc = "downvoted";
                ((TextView) va.findViewById(R.id.compstatus)).setText(abc);
            } else {
                String abc = "Neither downvoted nor upvoted";
                ((TextView) va.findViewById(R.id.compstatus)).setText(abc);
            }

        }

        String qwe = "Upvote(" + send.get(8) + ")";
        ((Button) (va.findViewById(R.id.upvote))).setText(qwe);


        String qw = "downvote(" + send.get(9) + ")";
        ((Button) (va.findViewById(R.id.downvote))).setText(qw);

        Button upvo = (Button) va.findViewById(R.id.upvote);
        upvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView abc = ((TextView) (va.findViewById(R.id.compid)));

                String cid = abc.getText().toString();


                String url = "http://192.168.137.1:8000/com/complaints/vote.json/" + cid + "/up";
                MyJsonRequest request = new MyJsonRequest(url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Login", "some error");

                    }
                });


                Singleton.getInstance().addToRequestQueue(request);


                TextView qwer = ((TextView) (va.findViewById(R.id.compstatus)));

                Button upv = (Button) (va.findViewById(R.id.upvote));
                Button downv = (Button) (va.findViewById(R.id.downvote));
                String iniupvtext = upv.getText().toString();
                String inidownvtext = downv.getText().toString();
                int noup = Integer.parseInt(iniupvtext.substring(7, iniupvtext.length() - 1));
                int nodown = Integer.parseInt(inidownvtext.substring(9, inidownvtext.length() - 1));


                String ac = ((TextView) (va.findViewById(R.id.compstatus))).getText().toString();
                if (ac.equals("upvoted")) {

                } else {
                    if (ac.equals("downvoted")) {
                        String aq = "upvoted";

                        String finup = "Upvote(" + (noup + 1) + ")";
                        String findown = "Downvote(" + (nodown - 1) + ")";
                        upv.setText(finup);
                        downv.setText(findown);

                        qwer.setText(aq);

                    } else {
                        String ty = "upvoted";
                        qwer.setText(ty);
                        String finup = "Upvote(" + (noup + 1) + ")";
                        upv.setText(finup);
                    }
                }

            }


        });


        Button downvo = (Button) va.findViewById(R.id.downvote);
        downvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView abc = ((TextView) (va.findViewById(R.id.compid)));

                String cid = abc.getText().toString();


                String url = "http://192.168.137.1:8000/com/complaints/vote.json/" + cid + "/down";
                MyJsonRequest request = new MyJsonRequest(url, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Login", "some error");

                    }
                });


                Singleton.getInstance().addToRequestQueue(request);


                TextView qwer = ((TextView) (va.findViewById(R.id.compstatus)));

                Button upv = (Button) (va.findViewById(R.id.upvote));
                Button downv = (Button) (va.findViewById(R.id.downvote));
                String iniupvtext = upv.getText().toString();
                String inidownvtext = downv.getText().toString();
                int noup = Integer.parseInt(iniupvtext.substring(7, iniupvtext.length() - 1));
                int nodown = Integer.parseInt(inidownvtext.substring(9, inidownvtext.length() - 1));


                String ac = ((TextView) (va.findViewById(R.id.compstatus))).getText().toString();
                Log.d("check down",ac);

                if (ac.equals("downvoted")||ac.equals("changed to downvote")) {

                } else {
                    if (ac.equals("upvoted")) {
                        String aq = "downvoted";

                        String finup = "Upvote(" + (noup - 1) + ")";
                        String findown = "Downvote(" + (nodown + 1) + ")";
                        upv.setText(finup);
                        downv.setText(findown);

                        qwer.setText(aq);

                    } else {
                        String ty = "downvoted";
                        qwer.setText(ty);
                        String finup = "downvote(" + (nodown + 1) + ")";
                        downv.setText(finup);
                    }
                }

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

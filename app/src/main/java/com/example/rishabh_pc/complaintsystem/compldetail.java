package com.example.rishabh_pc.complaintsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
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
        View va = inflater.inflate(R.layout.fragment_compldetail, container, false);
        ((TextView)va.findViewById(R.id.compby)).setText(send.get(0));
        ((TextView)va.findViewById(R.id.complevel)).setText(send.get(1));
        ((TextView)va.findViewById(R.id.compcre)).setText(send.get(2));
        ((TextView)va.findViewById(R.id.comptitl)).setText(send.get(3));
        ((TextView)va.findViewById(R.id.compresid)).setText(send.get(4));
        ((TextView)va.findViewById(R.id.compdescr)).setText(send.get(5));
        ((TextView)va.findViewById(R.id.compresbool)).setText(send.get(6));
        ((TextView)va.findViewById(R.id.compid)).setText(send.get(7));

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

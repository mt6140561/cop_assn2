package com.example.rishabh_pc.complaintsystem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
    private ArrayList<String[]> sendcom = new ArrayList<String[]>();

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
    public static compldetail newInstance(ArrayList<String> send, ArrayList<String[]> comments) {
        compldetail fragment = new compldetail();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, send);
        String tag;
        for (int i=0; i<comments.size(); i++) {
            tag = "c"+i;
            args.putStringArray(tag, comments.get(i));
            Log.d("comments", tag);
        }
        fragment.setArguments(args);
        return fragment;
    }

//    public static compldetail newInstance(ArrayList<String> send, ArrayList<String[]> sendcom) {
//        compldetail fragment = new compldetail();
//        Bundle args = new Bundle();
//        args.putStringArrayList(ARG_PARAM1, send);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            send = getArguments().getStringArrayList(ARG_PARAM1);
            String tag = "c0";
            int i = 0;
            while (getArguments().getStringArray(tag) != null) {

                sendcom.add(getArguments().getStringArray(tag));

                i = i+1;
                tag = "c" + i;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View va = inflater.inflate(R.layout.fragment_compldetail, container, false);
        ((TextView)va.findViewById(R.id.compby)).setText(send.get(8));
        ((TextView)va.findViewById(R.id.complevel)).setText(send.get(1));
        ((TextView)va.findViewById(R.id.compcre)).setText(send.get(2));
        ((TextView)va.findViewById(R.id.comptitl)).setText(send.get(3));
        ((TextView)va.findViewById(R.id.compresid)).setText(send.get(4));
        ((TextView)va.findViewById(R.id.compdescr)).setText(send.get(5));
        ((TextView)va.findViewById(R.id.compresbool)).setText(send.get(6));
        ((TextView)va.findViewById(R.id.compid)).setText(send.get(7));
        TableLayout comtable = (TableLayout) va.findViewById(R.id.commentable);
        TableRow.LayoutParams rowparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        if (sendcom.isEmpty()) {
            TableRow row = new TableRow(this.getActivity());
            row.setLayoutParams(rowparams);
            TextView tex = new TextView(this.getActivity());
            String f = "No comments";
            tex.setText(f);
            tex.setTextSize(18);
            tex.setPaddingRelative(20, 0, 0, 0);
            row.addView(tex);
            comtable.addView(row);
        } else {

            for (int i = 0; i < sendcom.size(); i++) {
                TableRow row = new TableRow(this.getActivity());
                row.setLayoutParams(rowparams);
                Log.d("commenttablerow", i+"");

                for (int j = 0; j < sendcom.get(i).length; j++) {
                    TextView tex = new TextView(this.getActivity());
                    String f = sendcom.get(i)[j];
                    tex.setText(f);
                    tex.setTextSize(22);
                    tex.setPaddingRelative(20, 0, 0, 0);
                    row.addView(tex);


                }
                comtable.addView(row);
            }
        }
        Button postcomm = (Button) va.findViewById(R.id.postcomment);
        String abc = ((TextView)va.findViewById(R.id.commtext)).getText().toString();
        if (abc.equals(null)) {
            String f = "No text";
            (TextView)va.findViewById()
        }




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

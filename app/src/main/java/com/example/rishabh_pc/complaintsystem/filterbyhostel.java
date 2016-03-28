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

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link filterbyhostel.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link filterbyhostel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class filterbyhostel extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public filterbyhostel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *stance of fragment filterbyhostel.
     */
    // TODO: Rename and change types and number of parameters
    public static filterbyhostel newInstance(String[][] param) {

        filterbyhostel fragment = new filterbyhostel();
        Bundle args = new Bundle();
        String tag;
        for (int i = 0; i < param.length; i++) {
            tag = "c" + i;
            args.putStringArray(tag, param[i]);
        }
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<String[]> Param1;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_filterbyhostel, container, false);
        TableLayout table = (TableLayout) v.findViewById(R.id.qwer);

        TableRow.LayoutParams rowparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < Param1.size(); i++) {
            TableRow row = new TableRow(this.getActivity());
            row.setLayoutParams(rowparams);


            String[] read1 = Param1.get(i);

            if (read1.length == 1) {
                TextView tes = new TextView(this.getActivity());
                tes.setText(read1[0]);
                tes.setTextSize(17);
                row.addView(tes);
                table.addView(row);
            } else {
                Log.d("here", read1[0] + "      " + read1[1]);
                for (int j = 0; j < 4; j++) {
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

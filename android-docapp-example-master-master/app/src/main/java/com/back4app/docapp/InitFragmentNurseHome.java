package com.back4app.docapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;



public class InitFragmentNurseHome extends Fragment {

  //  private OnFragmentInteractionListener mListener;

    TextView nursename;
    TextView nurseEmail;
    TextView nurseAddress;
    TextView nurseReportToDoc;
    private static final String ARG_SECTIONNUMBER = "section_1";
    public static InitFragmentNurseHome newInstance(int section_number)
    {
        InitFragmentNurseHome fragment = new InitFragmentNurseHome();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SECTIONNUMBER, section_number);
        fragment.setArguments(args);
        return fragment;
    }
    public InitFragmentNurseHome() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setHasOptionsMenu(true);
        if (getArguments() != null) {
            int sectionNumber = (Integer) getArguments().getSerializable(ARG_SECTIONNUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_init_fragment_nurse_home, container, false);
        nursename = (TextView)rootView.findViewById(R.id.textViewNurseName);
        nurseAddress = (TextView)rootView.findViewById(R.id.textViewNurseHospital);
        nurseEmail = (TextView)rootView.findViewById(R.id.textViewNurseEmail);
        nurseReportToDoc = (TextView)rootView.findViewById(R.id.textViewNurseReportsToDoc);
        ParseUser curUser = ParseUser.getCurrentUser();
        nursename.setText("Name : "+curUser.getString("Fname") + " "+curUser.getString("Lname"));
        nurseEmail.setText("Email :" + curUser.getUsername());
       // nurseReportToDoc.setText(curUser.getString("nurseReportsToDoc"));


        ParseQuery<ParseUser> userquery = ParseUser.getQuery();
        userquery.whereEqualTo("objectId", curUser.getString("nurseReportsToDoc"));
        userquery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    nurseReportToDoc.setText("NurseReportToDoc: "+objects.get(0).getString("Fname") + " "+objects.get(0).getString("Lname"));
                    nurseAddress.setText("Address: "+objects.get(0).getString("Hospital"));
                } else {
                    Log.d("InitFragmentNurseHome", "done: exception caught in doc details from nurse: "+e.getMessage());
                }
            }
        });

        return rootView;
    }

   
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
      //  mListener = null;
    }


}

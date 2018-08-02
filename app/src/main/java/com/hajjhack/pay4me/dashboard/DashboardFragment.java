package com.hajjhack.pay4me.dashboard;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.hajjhack.pay4me.R;


public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ProgressBar progressBar,progressBar2 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

                initViews(view);

                return view ;
    }

    private void initViews(View view) {

        progressBar = view.findViewById(R.id.progressBar);
        progressBar2 = view.findViewById(R.id.progressBar2);

        progressBar.setProgress(85);
        progressBar2.setProgress(50);

        ObjectAnimator anim1 = ObjectAnimator.ofInt(progressBar, "progress", 100, 85);
        anim1.setDuration(1000);
        anim1.setInterpolator(new LinearInterpolator());
        anim1.start();

        ObjectAnimator anim2 = ObjectAnimator.ofInt(progressBar2, "progress", 100, 50);
        anim2.setDuration(1000);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.start();


    }


}

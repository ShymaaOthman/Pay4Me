package com.hajjhack.pay4me.scanItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hajjhack.pay4me.R;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


public class ScanFragment extends Fragment
        implements ZBarScannerView.ResultHandler{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScanFragment newInstance(String param1, String param2) {
        ScanFragment fragment = new ScanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private final int DELAY_LENGTH = 2500;
    private ZBarScannerView mScannerView;
    List<BarcodeFormat> formats ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formats = new ArrayList<BarcodeFormat>();
        formats.add(BarcodeFormat.QRCODE);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


                View view = inflater.inflate(R.layout.fragment_scan, container, false);

                 mScannerView = (ZBarScannerView) view.findViewById(R.id.zBarScannerView);
                 mScannerView.setAutoFocus(true);

                return view ;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.startCamera();          // Start camera on resume
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.setFormats(formats);

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
        mScannerView.setFormats(formats);
        // Stop camera on pause
    }


    @Override
    public void onStop() {
        super.onStop();
        mScannerView.setFormats(formats);
    }

    @Override
    public void handleResult(Result rawResult) {
        final String message = rawResult.getContents();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (message.length() > 0) {

                    Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();

                } else {
                }

            }
        });
    }

}

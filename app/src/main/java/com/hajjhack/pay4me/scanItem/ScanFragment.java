package com.hajjhack.pay4me.scanItem;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

    TextView shopping_cart_items_no ;
    int counter = 0 ;
    Button pay_item_btn ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


                View view = inflater.inflate(R.layout.fragment_scan, container, false);


                 shopping_cart_items_no = view.findViewById(R.id.items_oncart_no);
                 mScannerView = (ZBarScannerView) view.findViewById(R.id.zBarScannerView);
                 mScannerView.setAutoFocus(true);
                 pay_item_btn = view.findViewById(R.id.pay_item_btn );

                 pay_item_btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                  public void onClick(View view) {

                     if(counter >0){

                         new PayAsyncTask().execute();
                    }else{
                         Toast.makeText(getContext(),"No Items in shopping cart",Toast.LENGTH_LONG).show();

                     }
                 }});



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

                    counter++ ;
                    shopping_cart_items_no.setText(counter+"");
                    Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                    mScannerView.startCamera();          // Start camera on resume
                    mScannerView.setResultHandler(ScanFragment.this); // Register ourselves as a handler for scan results.
                    mScannerView.setFormats(formats);

                } else {
                }

            }
        });
    }

    void createBusinessErrorDialog(Context context, String title, final String message) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.confirm_dialog);
        Button yes_btn = (Button) dialog.findViewById(R.id.yes_btn);
        TextView title_txt = (TextView) dialog.findViewById(R.id.title_txt);
        title_txt.setText(title);
        TextView message_txt = (TextView) dialog.findViewById(R.id.message_txt);
        message_txt.setText(message);
        dialog.setCancelable(true);
        dialog.show();
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    ProgressDialog progressDialog ;
    private class PayAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getContext(),"Payment","Please wait ..",true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            createBusinessErrorDialog(getActivity(),"Payment","Successful Payment !");

        }
    }
}

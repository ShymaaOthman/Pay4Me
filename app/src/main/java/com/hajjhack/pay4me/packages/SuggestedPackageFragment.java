package com.hajjhack.pay4me.packages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import View.PayApplication;
import View.UIManager;
import com.hajjhack.pay4me.R;

import java.util.ArrayList;

import model.SugestedListAdapter;

/**
 * Created by HP on 8/2/2018.
 */

public class SuggestedPackageFragment extends Fragment {
    private Context context;
    private SugestedListAdapter adapter;
    private ArrayList<String> arrayList;
    private TextView ok;
    private ArrayList<String> desciptions;
    private TextView d;

    public SuggestedPackageFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View f= inflater.inflate(R.layout.fragment_sugested_list, container, false);
        d=(TextView)f.findViewById(R.id.d);
        if(PayApplication.getInstance().isSugested)
        {
            d.setVisibility(View.VISIBLE);
        }else
        {
            d.setVisibility(View.GONE);

        }
        ok=(TextView)f.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UIManager.startlogin(getActivity(),true);
            }
        });
        return f;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadListView(view);
        onClickEvent(view);
    }

    private void loadListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        arrayList.add("Waffer Package");
        arrayList.add("Mega Package");
        arrayList.add("Super Package");
        desciptions = new ArrayList<>();
        desciptions.add("1000 point");
        desciptions.add("5000 point");
        desciptions.add("10000 point");


        adapter = new SugestedListAdapter(context, arrayList,desciptions);
        listView.setAdapter(adapter);
    }

    private void onClickEvent(View view) {
        view.findViewById(R.id.show_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the selected position
                adapter.getSelectedItem();
            }
        });
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the selected position
                adapter.deleteSelectedPosition();
            }
        });

    }

}

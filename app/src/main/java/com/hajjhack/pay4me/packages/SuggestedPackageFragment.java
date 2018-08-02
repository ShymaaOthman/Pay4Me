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
        ok=(TextView)f.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.StartHome(getActivity(),true);
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
        for (int i = 1; i <= 10; i++)
            arrayList.add("ListView Items " + i);

        adapter = new SugestedListAdapter(context, arrayList);
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

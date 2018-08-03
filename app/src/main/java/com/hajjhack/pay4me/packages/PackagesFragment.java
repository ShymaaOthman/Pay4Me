package com.hajjhack.pay4me.packages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hajjhack.pay4me.R;

import model.ImageAdapter;

public class PackagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HeaderListView packageslist;

    public PackagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PackagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackagesFragment newInstance(String param1, String param2) {
        PackagesFragment fragment = new PackagesFragment();
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

//        HeaderListView packageslist = new HeaderListView(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_packages, container, false);

        GridView gridview = (GridView)rootview.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
//
//        packageslist=(HeaderListView)rootview.findViewById(R.id.packageslist);
//        packageslist.setAdapter(new SectionAdapter() {
//
//            @Override
//            public int numberOfSections() {
//                return 4;
//            }
//
//            @Override
//            public int numberOfRows(int section) {
//                return 35;
//            }
//
//            @Override
//            public Object getRowItem(int section, int row) {
//                return null;
//            }
//
//            @Override
//            public boolean hasSectionHeaderView(int section) {
//                return true;
//            }
//
//            @Override
//            public View getRowView(int section, int row, View convertView, ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = (TextView) getLayoutInflater().inflate(getResources().getLayout(android.R.layout.simple_list_item_1), null);
//                }
//                ((TextView) convertView).setText("Package " + section + " Row " + row);
//                return convertView;
//            }
//
//            @Override
//            public int getSectionHeaderViewTypeCount() {
//                return 2;
//            }
//
//            @Override
//            public int getSectionHeaderItemViewType(int section) {
//                return section % 2;
//            }
//
//            @Override
//            public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
//
//                if (convertView == null) {
//                    if (getSectionHeaderItemViewType(section) == 0) {
//                        convertView = (TextView) getLayoutInflater().inflate(getResources().getLayout(android.R.layout.simple_list_item_1), null);
//                    } else {
//                        convertView = getLayoutInflater().inflate(getResources().getLayout(android.R.layout.simple_list_item_2), null);
//                    }
//                }
//
//                if (getSectionHeaderItemViewType(section) == 0) {
//                    ((TextView) convertView).setText("Provider  " + section);
//                } else {
//                    ((TextView) convertView.findViewById(android.R.id.text1)).setText("Provider " + section);
////                    ((TextView) convertView.findViewById(android.R.id.text2)).setText("Has a detail text field");
//                }
//
//                switch (section) {
//                    case 0:
//                        convertView.setBackgroundColor(getResources().getColor(R.color.holo_red_light));
//                        break;
//                    case 1:
//                        convertView.setBackgroundColor(getResources().getColor(R.color.holo_orange_light));
//                        break;
//                    case 2:
//                        convertView.setBackgroundColor(getResources().getColor(R.color.holo_green_light));
//                        break;
//                    case 3:
//                        convertView.setBackgroundColor(getResources().getColor(R.color.holo_blue_light));
//                        break;
//                }
//                return convertView;
//            }
//
//            @Override
//            public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id) {
//                super.onRowItemClick(parent, view, section, row, id);
//                Toast.makeText(getActivity(), "Section " + section + " Row " + row, Toast.LENGTH_SHORT).show();
//            }
//        });

        return  rootview;
    }


}

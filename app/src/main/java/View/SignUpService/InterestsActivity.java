package View.SignUpService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.hajjhack.pay4me.R;

import java.util.ArrayList;
import java.util.List;
import View.PayApplication;
import model.InterestsListAdapter;
import model.InterestsModel;
import View.UIManager;

public class InterestsActivity extends AppCompatActivity {
    private ListView interests_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        initilize();
    }

    private void initilize() {
        TextView ok_txt=(TextView)findViewById(R.id.ok_txt);
        TextView skip_txt=(TextView)findViewById(R.id.skip_txt);
        ok_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startSugestedPackageActivity(InterestsActivity.this,true);

            }
        });
        skip_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startMainPackageActivity(InterestsActivity.this,true);

            }
        });
        interests_list = (ListView) findViewById(R.id.interests_list);
        final List<InterestsModel> initItemList = this.getInitViewItemDtoList();

        // Create a custom list view adapter with checkbox control.
        final InterestsListAdapter listViewDataAdapter = new InterestsListAdapter(getApplicationContext(), initItemList);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        interests_list.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        interests_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                InterestsModel itemDto = (InterestsModel) itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if (itemDto.isChecked()) {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                } else {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }
            }


        });

        // Click this button to select all listview items with checkbox checked.
        TextView selectAllButton = (TextView) findViewById(R.id.list_select_all);
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                if (!PayApplication.selectAll) {
                    for (int i = 0; i < size; i++) {
                        InterestsModel dto = initItemList.get(i);
                        dto.setChecked(true);
                    }
                    PayApplication.selectAll = true;
                } else {
                    for (int i = 0; i < size; i++) {
                        InterestsModel dto = initItemList.get(i);
                        dto.setChecked(false);
                    }
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });


    }


    // Return an initialize list of ListViewItemDTO.
    private List<InterestsModel> getInitViewItemDtoList() {
        String itemTextArr[] = {"Transportation", "Accomdation", "Health", "Food", "Cloths", "Electronics", "Gifts"};

        List<InterestsModel> ret = new ArrayList<InterestsModel>();

        int length = itemTextArr.length;

        for (int i = 0; i < length; i++) {
            String itemText = itemTextArr[i];

            InterestsModel dto = new InterestsModel();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }


}

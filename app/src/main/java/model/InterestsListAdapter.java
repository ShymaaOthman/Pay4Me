package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hajjhack.pay4me.R;

import java.util.List;

/**
 * Created by HP on 8/2/2018.
 */

public class InterestsListAdapter extends BaseAdapter {

        private List<InterestsModel> listViewItemDtoList = null;

        private Context ctx = null;

    public InterestsListAdapter(Context ctx, List<InterestsModel> listViewItemDtoList) {
            this.ctx = ctx;
            this.listViewItemDtoList = listViewItemDtoList;
        }

        @Override
        public int getCount() {
            int ret = 0;
            if(listViewItemDtoList!=null)
            {
                ret = listViewItemDtoList.size();
            }
            return ret;
        }

        @Override
        public Object getItem(int itemIndex) {
            Object ret = null;
            if(listViewItemDtoList!=null) {
                ret = listViewItemDtoList.get(itemIndex);
            }
            return ret;
        }

        @Override
        public long getItemId(int itemIndex) {
            return itemIndex;
        }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {
        ListViewItemViewHolder viewHolder = null;

        if(convertView!=null)
        {
            viewHolder = (ListViewItemViewHolder) convertView.getTag();
        }else
        {
            convertView = View.inflate(ctx, R.layout.interests_list_item, null);

            CheckBox listItemCheckbox = (CheckBox) convertView.findViewById(R.id.list_view_item_checkbox);

            TextView listItemText = (TextView) convertView.findViewById(R.id.list_view_item_text);

            viewHolder = new ListViewItemViewHolder(convertView);

            viewHolder.setItemCheckbox(listItemCheckbox);

            viewHolder.setItemTextView(listItemText);

            convertView.setTag(viewHolder);
        }

        InterestsModel listViewItemDto = listViewItemDtoList.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getItemTextView().setText(listViewItemDto.getItemText());

        return convertView;    }


    public class ListViewItemViewHolder extends RecyclerView.ViewHolder {

        private CheckBox itemCheckbox;

        private TextView itemTextView;

        public ListViewItemViewHolder(View itemView) {
            super(itemView);
        }

        public CheckBox getItemCheckbox() {
            return itemCheckbox;
        }

        public void setItemCheckbox(CheckBox itemCheckbox) {
            this.itemCheckbox = itemCheckbox;
        }

        public TextView getItemTextView() {
            return itemTextView;
        }

        public void setItemTextView(TextView itemTextView) {
            this.itemTextView = itemTextView;
        }
    }
    }
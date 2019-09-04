package com.quind.quind.Model;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.quind.quind.Claim.DataClaime;
import com.quind.quind.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterExpandableList extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listHeader;
    private List<DataClaime> data = new ArrayList<>();
    private TextView dataNama,dataCategory,dataTotal, dataNumber, date;

    public AdapterExpandableList(Context context, List<String> listHeader, List<DataClaime> data) {
        this.context = context;
        this.listHeader = listHeader;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return data.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }
        TextView listdataHeader = (TextView)convertView.findViewById(R.id.tv_data1);
        listdataHeader.setTypeface(null, Typeface.BOLD);
        listdataHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final String childData = (String)getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);
        }
        dataNama = (TextView)convertView.findViewById(R.id.list1);
        dataTotal = (TextView)convertView.findViewById(R.id.list3);
        dataCategory = (TextView)convertView.findViewById(R.id.list2);
        dataNumber = (TextView)convertView.findViewById(R.id.list5);
        date = (TextView)convertView.findViewById(R.id.list4);

        dataNama.setTypeface(null, Typeface.BOLD);
        dataNama.setText(data.get(childPosition).getNama());

        dataCategory.setTypeface(null, Typeface.BOLD);
        dataCategory.setText(data.get(childPosition).getCategory());
        dataNumber.setTypeface(null, Typeface.BOLD);
        dataNumber.setText(data.get(childPosition).getNumber());

        dataTotal.setTypeface(null, Typeface.BOLD);
        dataTotal.setText(String.valueOf(data.get(childPosition).getTotal()));

        date.setTypeface(null, Typeface.BOLD);
        date.setText(data.get(childPosition).getDate());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

package com.quind.quind.Model;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import android.widget.ImageView;

import android.widget.TextView;

import com.quind.quind.HelpCallBack;
import com.quind.quind.R;


public class AdapterHelpExpandableListView extends BaseExpandableListAdapter {

    String[] GroupNames = {
            "Apa itu asuransi data?",
            "Bagaimana cara mendaftar asuransi data?",
            "Bagaimana cara melihat claim report?",
            "Bagaimana cara membuat reminder?",
            "Apa saja yang bisa diasuransikan?",
            "Bagaimana melakukan cek policy?"
    };

    String[][] ChildData ={
            {"Asuransi data merupakan asuransi yang menjamin  data dan penanganan saat data yang dimiliki nasabah mengalami penyalahgunaan atau pun corrupt. maka asuransi data dapat menanggung semua biaya restorasi yang ada."},
            {"blablabla mendaftar asuransi"},
            {"blablabla melihat claim report"},
            {"Untuk membuat reminder, silahkan sentuh tab Reminder. Sentuh tombol Add new reminder untuk membuat Reminder baru, lalu isi semua kolom yang tersedia, dan tekan tombol save untuk menyimpan reminder anda."},
            {"Perlindungan yang ditawarkan QUIND meliputi:" +
                    "\nCyber and Privacy Risk " +
                    "\nPerlindungan pada saat terjadi pelanggaran akses untuk perangkat hard disk atau server." +
                    "\n\nMobile and Tablet" +
                    "\nPerlindungan ketika terjadi pelanggaran penggunaan data di mobile phone atau tablet." +
                    "\n\nSocial Media Account" +
                    "\nPerlindungan yang ditujukan untuk penyedia layanan social media."},
            {"blablabla cara cek polis"}
    };

    Context context;
    private HelpCallBack helpCallBack;
    int lastExpandedGroupPosition = 0;

    public AdapterHelpExpandableListView(Context context, HelpCallBack helpCallBack){
        this.helpCallBack=helpCallBack;
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return GroupNames.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ChildData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return GroupNames[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ChildData[groupPosition][childPosition];
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
//        TextView textView=new TextView(context);
//        textView.setText(getGroup(groupPosition).toString());
//        textView.setTextColor(Color.BLACK);
//        textView.setTextSize(18);
//        textView.setTypeface(null, Typeface.BOLD);
//        textView.setPadding(90,36,84,36);
//        return textView;
        View v= convertView;
        if(v==null){
            v=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_help_group,parent,false);

        }
        TextView textView= v.findViewById(R.id.help_group_title);
        ImageView imageView=v.findViewById(R.id.help_group_image);
        textView.setText(getGroup(groupPosition).toString());
        if (isExpanded) {
            imageView.setImageResource(R.drawable.up_arrow);
        } else {
            imageView.setImageResource(R.drawable.down_arrow);
        }
        return v;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView=new TextView(context);
        textView.setText(getChild(groupPosition,childPosition).toString());
        textView.setTextSize(12);
        textView.setPadding(100,0,84,16);
        return textView;


    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        if(getGroupCount()!=lastExpandedGroupPosition){
            //callback
            helpCallBack.onExpand(groupPosition);
        }
        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition=groupPosition;
    }
}

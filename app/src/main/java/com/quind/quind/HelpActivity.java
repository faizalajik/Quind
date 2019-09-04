package com.quind.quind;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import com.quind.quind.Model.AdapterHelpExpandableListView;


public class HelpActivity extends AppCompatActivity implements HelpCallBack{
    ExpandableListView expandableListView;
    int lastPosition=0;

    @Override
    public void onExpand(int position) {
        if(position!=lastPosition){
            expandableListView.collapseGroup(lastPosition);
        }
        lastPosition=position;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);
        expandableListView = findViewById(R.id.expandablelistview_help);
        AdapterHelpExpandableListView adapterHelpExpandableListView = new AdapterHelpExpandableListView(this, this);
        expandableListView.setAdapter(adapterHelpExpandableListView);
    }

//kode kalo tiba2 mau diganti lagi jadi fragment
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_help, container, false);
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        expandableListView=view.findViewById(R.id.expandablelistview_help);
//        AdapterHelpExpandableListView adapterHelpExpandableListView=new AdapterHelpExpandableListView(getContext(),this);
//        expandableListView.setAdapter(adapterHelpExpandableListView);
//    }


}

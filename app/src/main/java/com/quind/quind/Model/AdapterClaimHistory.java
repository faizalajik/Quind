package com.quind.quind.Model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.quind.quind.Claim.Claim;
import com.quind.quind.ClaimDetail;
import com.quind.quind.R;

import java.util.ArrayList;

public class AdapterClaimHistory extends RecyclerView.Adapter<AdapterClaimHistory.ViewHolder> {

    private Context context;
    private String num,status;
    private ArrayList<Claim> claim;
    public AdapterClaimHistory (){

    }
    public AdapterClaimHistory (Context context, ArrayList<Claim> claim){
        this.context = context;
        this.claim = claim;
    }
    public AdapterClaimHistory (Context context, String num, String status){
        this.context = context;
        this.num = num;
        this.status = status;
    }

    @NonNull
    @Override
    public AdapterClaimHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v;
        v = layoutInflater.inflate(R.layout.cardview_claim_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterClaimHistory.ViewHolder holder, final int position) {
//        holder.TvNum.setText(claim.get(position).getPolicyNumber());
//        holder.tvProses.setText(claim.get(position).getStatus());
        holder.TvNum.setText(claim.get(position).getPolicyNumber());
        holder.tvProses.setText(claim.get(position).getStatus());
        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.mImageButton,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton mImageButton;
        private TextView TvNum,tvProses;
        public ViewHolder(View itemView) {
            super(itemView);
            tvProses = (TextView) itemView.findViewById(R.id.tv_proses);
            TvNum = (TextView) itemView.findViewById(R.id.tv_number);
            mImageButton =  (ImageButton) itemView.findViewById(R.id.menu);
        }
    }
    private void showPopupMenu(View view,int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.option, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private int position;
        public MyMenuItemClickListener(int position) {
            this.position=position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.detail:
                    Intent intent =  new Intent(context, ClaimDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("posisi", position);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    Toast.makeText(context,"detail",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.remove:
                    Toast.makeText(context,"remove",Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}

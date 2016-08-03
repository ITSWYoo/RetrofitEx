package com.example.yoo.retrofitex.recyclerviewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoo.retrofitex.R;
import com.example.yoo.retrofitex.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yoo on 2016-08-03.
 */
public class MainRecyclerViewAdpater extends RecyclerView.Adapter<MainRecyclerViewAdpater.ViewHolder>{
    Context mContext;
    public List<Repo> items;
    //초기화
    public MainRecyclerViewAdpater(Context mContext , List<Repo> items)
    {
        this.mContext = mContext;
        this.items = items;
    }
    //add item
    public void addItem(Repo item)
    {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAllItem(List<Repo> items)
    {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    //itemView inflater
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_recyclerview_item, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItemData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

     public static class ViewHolder extends RecyclerView.ViewHolder {
         Repo item;
        @BindView(R.id.main_recyclerview_textview_id)
        TextView main_recyclerview_textview_id;
         @OnClick(R.id.main_recyclerview_textview_id) void idClicked(){
             Toast.makeText(itemView.getContext(),"id clicked",Toast.LENGTH_SHORT).show();
         }

        @BindView(R.id.main_recyclerview_textview_name)
        TextView main_recyclerview_textview_name;
         @OnClick(R.id.main_recyclerview_textview_name)void nameClicked(){
             Toast.makeText(itemView.getContext(),"name clicked",Toast.LENGTH_SHORT).show();
         }

        @BindView(R.id.main_recyclerview_textview_full_name)
        TextView main_recyclerview_textview_full_name;
         @OnClick(R.id.main_recyclerview_textview_full_name)void fullNameClicked(){
             Toast.makeText(itemView.getContext(),"fullName clicked",Toast.LENGTH_SHORT).show();
         }
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext()," "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
            ButterKnife.bind(this,itemView);
        }
         public void setItemData(Repo item)
         {
             this.item = item;
             main_recyclerview_textview_id.setText("ID: "+String.valueOf(item.getId()));
             main_recyclerview_textview_name.setText("Name: "+item.getName());
             main_recyclerview_textview_full_name.setText("Full_Name: "+item.getFull_name());
         }
    }
}

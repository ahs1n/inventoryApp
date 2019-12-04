package com.example.tabletsinventory.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tabletsinventory.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {


    List<InventoryAdd> list;
    Context context;


    public ContentAdapter(List<InventoryAdd> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.brand.setText(list.get(position).brand);
        holder.ftag.setText(list.get(position).tag_number);
        holder.imei.setText(String.valueOf(list.get(position).imei));
        holder.received_from.setText(String.valueOf(list.get(position).received_from));
        holder.location.setText(String.valueOf(list.get(position).location));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView brand, imei, received_from, ftag, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            brand = itemView.findViewById(R.id.brand);
            ftag = itemView.findViewById(R.id.ftag);
            imei = itemView.findViewById(R.id.imei);
            location = itemView.findViewById(R.id.location);
            received_from = itemView.findViewById(R.id.receivedFrom);

        }
    }
}

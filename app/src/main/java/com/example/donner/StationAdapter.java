package com.example.donner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.stationViewHolder> {

    List<Stations> stationsList;
    Context context;

    public StationAdapter(Context context, List<Stations> stations) {
        this.context = context;
        stationsList = stations;
    }

    @NonNull
    @Override
    public stationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_station_recycler, parent, false);
        return new stationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stationViewHolder holder, int position) {
        Stations stations = stationsList.get(position);
        holder.t1.setText(stations.shopname);
        holder.t2.setText(stations.shopaddress);
        holder.t3.setText(stations.shopemailaddress);
//        holder.t4.setText(stations.latitude);
//        holder.t5.setText(stations.longitude);
        holder.t6.setText(stations.shopphonenumber);
        holder.t7.setText(stations.weekdayopening);
        holder.t8.setText(stations.weekdayclosing);

    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    public class stationViewHolder extends RecyclerView.ViewHolder {

        TextView t1, t2, t3, t4, t5, t6, t7, t8;

        public stationViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
//            t4 = itemView.findViewById(R.id.t4);
//            t5 = itemView.findViewById(R.id.t5);
            t6 = itemView.findViewById(R.id.t6);
            t7 = itemView.findViewById(R.id.t7);
            t8 = itemView.findViewById(R.id.t8);

        }
    }
}

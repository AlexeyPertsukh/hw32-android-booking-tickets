package com.example.bookingtickets;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {
    private static final String MONEY_SYMBOL = "₴";

    private ArrayList<Bus> listBus;

    public BusAdapter(ArrayList<Bus> listBus) {
        this.listBus = listBus;
    }

    @NonNull
    @NotNull
    @Override
    public BusAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull BusAdapter.ViewHolder holder, int position) {
        Bus bus = listBus.get(position);
        holder.tvCityStart.setText(bus.getCityStart());
        holder.tvCityFinish.setText(bus.getCityFinish());
        holder.tvCityFinish.setText(bus.getCityFinish());

        holder.tvTimeStart.setText(bus.getFormattedTimeStart());
        holder.tvTimeFinish.setText(bus.getFormattedTimeFinish());
        holder.tvWayTime.setText("в пути \n"+ bus.getFormattedWayTime());

        holder.tvFreePlaces.setText(bus.getAmountTickets() + " мест");
        holder.btnBuy.setText(bus.getPrice() + " " + MONEY_SYMBOL);


    }

    @Override
    public int getItemCount() {
        return listBus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCityStart;
        public TextView tvCityFinish;
        public TextView tvTimeStart;
        public TextView tvTimeFinish;
        public TextView tvWayTime;
        public TextView tvFreePlaces;
        public Button btnBuy;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView){
            tvCityStart = itemView.findViewById(R.id.tvCityStart);
            tvCityFinish = itemView.findViewById(R.id.tvCityFinish);
            tvTimeStart = itemView.findViewById(R.id.tvTimeStart);
            tvTimeFinish = itemView.findViewById(R.id.tvTimeFinish);
            tvWayTime = itemView.findViewById(R.id.tvWayTime);
            tvFreePlaces = itemView.findViewById(R.id.tvFreePlaces);
            btnBuy = itemView.findViewById(R.id.btnBuy);
        }
    }
}


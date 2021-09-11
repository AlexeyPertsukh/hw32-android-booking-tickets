package com.example.bookingtickets;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentBus extends Fragment implements IConst {

    private ImageView ivReturn;
    private TextView tvTrip;
    private TextView tvBusDate;
    private RecyclerView rvBus;;
    private ArrayList<Bus> actualBus;
    private RecyclerView.Adapter adapterRvBus;


    public FragmentBus() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bus, container, false);
        if(getArguments() != null) {
            actualBus = (ArrayList<Bus>) getArguments().getSerializable(KEY_BUS);
        }
        initViews(view);
        initListeners();
        initRvBusAdapter();
        showRoute();
        showDate();

        return view;
    }

    private void initViews(View view) {
        ivReturn = view.findViewById(R.id.ivReturn);
        tvTrip = view.findViewById(R.id.tvTrip);
        tvBusDate = view.findViewById(R.id.tvBusDate);
        rvBus = view.findViewById(R.id.rvBus);
    }

    private void initListeners() {
        ivReturn.setOnClickListener(this::clickReturn);
    }

    private void initRvBusAdapter() {
        rvBus.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapterRvBus = new BusAdapter(actualBus);
        rvBus.setLayoutManager(layoutManager);
        rvBus.setAdapter(adapterRvBus);
    }

    private void clickReturn(View view) {
        if(getActivity() != null) {
            MainActivity ma = (MainActivity)getActivity();
            ma.showFragmentChoice();
        }
    }

    private void showRoute() {
        if(actualBus != null && actualBus.size() > 0) {
            tvTrip.setText(actualBus.get(0).getStringRoute());
        }
    }

    private void showDate() {
        if(actualBus != null && actualBus.size() > 0) {
            tvBusDate.setText(actualBus.get(0).getFormattedDateStart());
        }
    }

}
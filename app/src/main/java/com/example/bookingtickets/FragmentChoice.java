package com.example.bookingtickets;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FragmentChoice extends Fragment implements IToast{

    private Spinner spCityStart;
    private Spinner spCityFinish;
    private TextView tvDate;
    private TextView tvAmounrPassengers;
    private Button btnFind;
    private final Calendar calendarDate = Calendar.getInstance();

    private String fromCity;
    private String toCity;

    private Date date;

    private ArrayAdapter<String> adapterCityStart;
    private ArrayAdapter<String> adapterCityFinish;
    private final Amount amountPassengers = new Amount(1);

    ArrayList<Bus> listBus;


    public FragmentChoice() {
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

        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        initViews(view);
        initListeners();
        initSpinners();
        date = new Date(); //Calendar.getInstance().getTime();
        showDateRoute();
        showAmountPassengers();
        return view;
    }

    private void initSpinners() {
        City[] cities = City.values();

        String[] strings = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            strings[i] = cities[i].getNameCity();
        }

        adapterCityStart = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strings);
        spCityStart.setAdapter(adapterCityStart);

        adapterCityFinish = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strings);
        spCityFinish.setAdapter(adapterCityFinish);


        spCityStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromCity = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCityFinish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toCity = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initViews(View view) {
        spCityStart = view.findViewById(R.id.spCityStart);
        spCityFinish = view.findViewById(R.id.spCityFinish);
        tvDate  = view.findViewById(R.id.tvDate);
        tvAmounrPassengers = view.findViewById(R.id.tvAmounrPassengers);
        btnFind = view.findViewById(R.id.btnFind);
    }

    private void initListeners() {
        btnFind.setOnClickListener(this::clickFind);
        tvDate.setOnClickListener(this::clickDate);
        tvAmounrPassengers.setOnClickListener(this::clickNumPassenger);

    }

    private void changeDate(int year, int month, int day) {
//        btnFind.setVisibility(View.VISIBLE);
        date = new Date(year - 1900, month, day);
        showDateRoute();
    }

    private void clickNumPassenger(View view) {
        showDialogAmountPassengers();
    }

    private void clickDate(View view) {
        selectDate();
    }

    private void clickFind(View view) {
        ArrayList<Bus> list = createActualBus();
        if(list.size() == 0) {
            longToast(getContext(), "На указанное направление билетов нет");
        } else {
            showFragmentBus(list);
        }
    }

    private void showFragmentBus(ArrayList<Bus> actualBus) {
        if(getActivity() != null) {
            MainActivity ma = (MainActivity) getActivity();
            ma.showFragmentBus(actualBus);
        }
    }

    private void showDateRoute() {
        tvDate.setText(Util.dateToString(date));
    }

    @SuppressLint("SetTextI18n")
    private void showAmountPassengers() {
        tvAmounrPassengers.setText(amountPassengers.getNum() + " человек");
    }


    private void initAllBus() {
        ArrayList<TripRoute> tripRoutes = new ArrayList<>();
        TripRoute tripRouteZpKiev = TripRoute.of(City.ZP, City.Kiev);
        TripRoute tripRouteZpDnepr = TripRoute.of(City.ZP, City.Dnepr);

        TripRoute tripRouteKievZp = TripRoute.of(City.Kiev, City.ZP);
        TripRoute tripRouteDneprZp = TripRoute.of(City.Dnepr, City.ZP);

        tripRoutes.add(tripRouteZpKiev);
        tripRoutes.add(tripRouteZpDnepr);
        tripRoutes.add(tripRouteKievZp);
        tripRoutes.add(tripRouteDneprZp);

        listBus = new ArrayList<>();
        String stringDate = Util.dateToString(date);
        listBus.add(Bus.of(tripRouteZpKiev, stringDate + ":10.05", stringDate + ":18.05", 530, 15));
        listBus.add(Bus.of(tripRouteZpKiev, stringDate + ":12.15", stringDate + ":20.35", 527, 25));
        listBus.add(Bus.of(tripRouteZpKiev, stringDate + ":14.05", stringDate + ":22.47", 538, 7));

        listBus.add(Bus.of(tripRouteZpDnepr, stringDate + ":10.05", stringDate + ":12.05", 127, 5));
        listBus.add(Bus.of(tripRouteZpDnepr, stringDate + ":12.15", stringDate + ":14.35", 135, 13));
        listBus.add(Bus.of(tripRouteZpDnepr, stringDate + ":14.05", stringDate + ":16.27", 122, 17));

        listBus.add(Bus.of(tripRouteKievZp, stringDate + ":10.05", stringDate + ":18.05", 510, 9));
        listBus.add(Bus.of(tripRouteKievZp, stringDate + ":12.15", stringDate + ":20.35", 522, 20));
        listBus.add(Bus.of(tripRouteKievZp, stringDate + ":14.05", stringDate + ":22.27", 531, 11));

        listBus.add(Bus.of(tripRouteDneprZp, stringDate + ":10.05", stringDate + ":12.05", 122, 15));
        listBus.add(Bus.of(tripRouteDneprZp, stringDate + ":12.15", stringDate + ":14.35", 142, 11));
        listBus.add(Bus.of(tripRouteDneprZp, stringDate + ":14.05", stringDate + ":16.27", 124, 13));

    }

    private ArrayList<Bus> createActualBus() {
        initAllBus();
        ArrayList<Bus> listActualBus = new ArrayList<>();
        for (Bus bus : listBus) {
            if(bus.checkActualForTrip(fromCity, toCity, date)) {
                listActualBus.add(bus);
            }
        }
        return listActualBus;

    }

    private void selectDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view1, year, month, dayOfMonth) -> {
                    calendarDate.set(Calendar.YEAR, year);
                    calendarDate.set(Calendar.MONTH, month);
                    calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    changeDate(year, month, dayOfMonth);
                },
                calendarDate.get(Calendar.YEAR),
                calendarDate.get(Calendar.MONTH),
                calendarDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showDialogAmountPassengers() {
        @SuppressLint("DefaultLocale")
        String message = "Пассажиров: ";
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .create();
        alertDialog.setMessage(message + String.valueOf(amountPassengers.getNum()) );
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "-",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        amountPassengers.dec();
                        alertDialog.setMessage(message + String.valueOf(amountPassengers.getNum()) );
                        alertDialog.show();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "+",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        amountPassengers.inc();
                        alertDialog.setMessage(message + String.valueOf(amountPassengers.getNum()) );
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });


        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                showAmountPassengers();
            }
        });
        //findViewById(R.id.tvNumPassenger);
        alertDialog.setContentView(R.layout.support_simple_spinner_dropdown_item);
        alertDialog.show();
    }

}
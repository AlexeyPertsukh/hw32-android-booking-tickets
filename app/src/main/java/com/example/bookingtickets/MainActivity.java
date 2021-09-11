package com.example.bookingtickets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IConst{

    private ArrayList<Bus> listBus;
    private List<TripRoute> tripRoutes;

    private FragmentBus fragmentBus;
    private FragmentChoice fragmentChoice;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();

        showFragmentChoice();
    }

    private void initFragments() {
        fragmentBus = new FragmentBus();
        fragmentChoice = new FragmentChoice();
    }


    public void showFragmentChoice() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragmentChoice)
                .commit();
    }

    public void showFragmentBus(ArrayList<Bus> actualBus) {
        Bundle args = new Bundle();


        args.putSerializable(KEY_BUS, actualBus);

        fragmentBus.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragmentBus)
                .commit();
    }

    /*
            Bundle args = new Bundle();
        Game game = new Game(listQuestion);

        args.putSerializable(KEY_GAME, game);

        gameFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, gameFragment)
                .commit();
     */

}
package com.rahulm09.android.smartcards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {
    private static final String CARD_KEY = "card" ;
    Card cardId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Log.d("DetailAct1","here");
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
           // Bundle extras = getIntent().getExtras();
            Bundle arguments = new Bundle();
            arguments.putParcelable(CARD_KEY, getIntent().getExtras().getParcelable(CARD_KEY));

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.card_detail_container, fragment)
                    .commit();
        }


        /*Bundle extras = getIntent().getExtras();
        cardId = null;
        if (extras != null) {
            cardId = extras.getParcelable(CARD_KEY);
        }

            Bundle arguments = new Bundle();
            arguments.putParcelable(CARD_KEY, cardId);

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.card_detail_container, fragment)
                    .commit();
            Log.d("DetailActivity","DetailActivity");


        //Log.d("DetailAct1","here"+cardId);*/
    }

}

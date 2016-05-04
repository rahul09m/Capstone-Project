package com.rahulm09.android.smartcards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

private Context mContext;
private static final String CONTENT = "content";
    private static final String FORMAT = "format";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  /*IntentIntegrator scanCard = new IntentIntegrator((Activity) mContext);
                    scanCard.initiateScan();*/

                //TEsting purposes
                Intent showScannedCard = new Intent(getApplicationContext(), AddCard.class);
                showScannedCard.putExtra(CONTENT, "123456789");
                showScannedCard.putExtra(FORMAT, "CODE_128");

                startActivity(showScannedCard);
                //TEsting purpose end
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null){
            Log.d("Main Act", "fomrat: " + scanResult.getFormatName());
            Intent showScannedCard = new Intent(this, AddCard.class);
            showScannedCard.putExtra(CONTENT, scanResult.getContents());
            showScannedCard.putExtra(FORMAT, scanResult.getFormatName());

            startActivity(showScannedCard);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

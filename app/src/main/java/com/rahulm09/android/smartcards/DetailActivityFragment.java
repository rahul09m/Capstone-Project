package com.rahulm09.android.smartcards;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    Card myCard;
    private static final String CARD_KEY = "card" ;
    private static final String SAVED_CARD_KEY = "saved_card" ;
    Bitmap bitmap;
    public DetailActivityFragment() {
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        if ((savedInstanceState == null)) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                myCard = arguments.getParcelable(CARD_KEY);
                Log.d("DetailActF", "here" + myCard);
            }
            Log.d("DetailActFrag", "here" + myCard);
        }else{
            myCard = savedInstanceState.getParcelable(SAVED_CARD_KEY);
            Log.d("DetailActFrag", "here1" + myCard);
        }
            try {
                BarcodeEncoder encode = new BarcodeEncoder();
                bitmap = encode.encodeBitmap(myCard.number, BarcodeFormat.valueOf(myCard.format), 600, 300);
            } catch (WriterException e) {
                e.printStackTrace();
            }

    }*/

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CARD_KEY,myCard);
        Log.d("DetailActFragSave", "here" + myCard);


    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // setRetainInstance(true);
           /* if (savedInstanceState != null){
                myCard = savedInstanceState.getParcelable(CARD_KEY);
                Log.d("DetailActFragrestore", "here" + myCard);
            }else if (savedInstanceState == null){
                Bundle arguments = getArguments();
                if (arguments != null) {
                    myCard = arguments.getParcelable(CARD_KEY);
                    Log.d("DetailActargnull", "here" + myCard);
                }
                Log.d("DetailActFragargex", "here" + myCard);
            }*/
        Bundle arguments = getArguments();
        if(arguments != null){
            myCard = arguments.getParcelable(CARD_KEY);
            Log.d("DetailFrag","here"+myCard);
        }
        Log.d("DetailFragOutside","here"+myCard);
        try {
            BarcodeEncoder encode = new BarcodeEncoder();
            bitmap = encode.encodeBitmap(myCard.number, BarcodeFormat.valueOf(myCard.format), 600, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        View rootView=  inflater.inflate(R.layout.fragment_detail, container, false);

        TextView cardName = (TextView)rootView.findViewById(R.id.card_name) ;
        cardName.setText(myCard.name);

        TextView cardNumber = (TextView)rootView.findViewById(R.id.card_number);
        cardNumber.setText(myCard.number);

        ImageView cardBarcode = (ImageView)rootView.findViewById(R.id.card_barcode);
        cardBarcode.setImageBitmap(bitmap);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editCard = new Intent(getActivity(),EditCard.class);
                editCard.putExtra(CARD_KEY,myCard);
                startActivity(editCard);
            }
        });



        return rootView;
    }
}

package com.rahulm09.android.smartcards;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    Bitmap bitmap;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=  inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle arguments = getArguments();
        if(arguments != null){
            myCard = arguments.getParcelable(CARD_KEY);
        }

        try {
            BarcodeEncoder encode = new BarcodeEncoder();
            bitmap = encode.encodeBitmap(myCard.number, BarcodeFormat.valueOf(myCard.format), 600, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        TextView cardName = (TextView)rootView.findViewById(R.id.card_name) ;
        cardName.setText(myCard.name);

        TextView cardNumber = (TextView)rootView.findViewById(R.id.card_number);
        cardNumber.setText(myCard.number);

        ImageView cardBarcode = (ImageView)rootView.findViewById(R.id.card_barcode);
        cardBarcode.setImageBitmap(bitmap);

        return rootView;
    }
}

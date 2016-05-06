package com.rahulm09.android.smartcards;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rahulm09.android.smartcards.data.CardColumns;

import java.util.List;

/**
 * Created by rmenezes on 5/4/2016.
 */
public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.CardViewHolder> {
    List<Card> listcards;
    Context context;
    Cursor dataCursor;

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView cardName;
        TextView cardNumber;

        CardViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cardName = (TextView)itemView.findViewById(R.id.card_name);
            cardNumber = (TextView)itemView.findViewById(R.id.card_number);
        }

    }


    MyCardAdapter(Context mContext,Cursor cursor, List<Card>cards){
        this.context = mContext;
        this.dataCursor = cursor;
        this.listcards = cards;
    }

    /*public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
}
    Cursor oldCursor = dataCursor;
this.dataCursor = cursor;
        if (cursor != null) {
        this.notifyDataSetChanged();
        }
        return oldCursor;
        }*/
    public void setItems(final List<Card> cards) {
        listcards.addAll(cards);
        notifyDataSetChanged();
    }
    @Override
    public MyCardAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(MyCardAdapter.CardViewHolder holder, int position) {
        dataCursor.moveToPosition(position);
        String name = dataCursor.getString(dataCursor.getColumnIndex(CardColumns.NAME));
        String number = dataCursor.getString(dataCursor.getColumnIndex(CardColumns.NUMBER));

        holder.cardName.setText(name);
        holder.cardNumber.setText(number);
    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }


}

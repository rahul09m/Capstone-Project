package com.rahulm09.android.smartcards;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.rahulm09.android.smartcards.data.CardColumns;
import com.rahulm09.android.smartcards.data.CardProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CARD_LOADER = 0;
    private static final String CARD_KEY = "card" ;
    private ArrayList<Card> cards;
    private CardAdapter cardAdapter;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardAdapter = new CardAdapter(getActivity(), new ArrayList<Card>());
       // getLoaderManager().restartLoader(CARD_LOADER, null, this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().restartLoader(CARD_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(cardAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                detailIntent.putExtra(CARD_KEY, cardAdapter.getItem(i));
                startActivity(detailIntent);
            }
        });



        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), CardProvider.Cards.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            cards = new ArrayList<>(cursor.getCount());
            Log.d("Cursor", String.valueOf(cursor.getCount()));

            while (cursor.moveToNext()) {
                Card card = new Card(cursor.getInt(cursor.getColumnIndex(CardColumns._ID)),
                        cursor.getString(cursor.getColumnIndex(CardColumns.NAME)),
                        cursor.getString(cursor.getColumnIndex(CardColumns.NUMBER)),
                        cursor.getString(cursor.getColumnIndex(CardColumns.FORMAT)));
                cards.add(card);
               // Log.d("CursorID", String.valueOf(cursor.getInt(cursor.getColumnIndex(CardColumns._ID))));
                //Log.d("CursorFormat", String.valueOf(cursor.getString(cursor.getColumnIndex(CardColumns.FORMAT))));
            }
            cursor.close();
            updateAdapter(cards);
        }else{
            Toast.makeText(getContext(), "No cards",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void updateAdapter(List<Card> cardToAdd){
        cardAdapter.clear();
        cardAdapter.addAll(cardToAdd);
        cardAdapter.notifyDataSetChanged();
    }
}

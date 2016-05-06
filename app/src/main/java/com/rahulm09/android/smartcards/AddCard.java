package com.rahulm09.android.smartcards;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rahulm09.android.smartcards.data.CardColumns;
import com.rahulm09.android.smartcards.data.CardProvider;

/**
 * Created by Rahul on 01/05/2016.
 */
public class AddCard extends AppCompatActivity{
    private static final String CONTENT = "content";
    private static final String FORMAT = "format";
    EditText editName;
    EditText editNumber;
    String format;//testing

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);

        Intent intent = getIntent();
        String content = intent.getStringExtra(CONTENT);
        format = intent.getStringExtra(FORMAT);

        editName = (EditText) findViewById(R.id.editNameText);
        editNumber = (EditText) findViewById(R.id.editNumberText);

        editNumber.setText(content);

    }

    public void saveCard(View view) {
        String nameText = editName.getText().toString();
        String numberText = editNumber.getText().toString();
        if ((nameText != null && !nameText.isEmpty()) && (numberText != null && !numberText.isEmpty())){

            ContentValues cv = new ContentValues();
            cv.put(CardColumns.NAME, nameText);
            cv.put(CardColumns.NUMBER, numberText);
            cv.put(CardColumns.FORMAT, format);
            getBaseContext().getContentResolver().insert(CardProvider.Cards.CONTENT_URI, cv);
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show();
            Intent backToMain = new Intent(this, MainActivity.class);
            backToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(backToMain);

        }else{
            Toast.makeText(this, "Missing Fields", Toast.LENGTH_SHORT).show();
        }
    }
}

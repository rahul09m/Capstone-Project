package com.rahulm09.android.smartcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

private static final String CONTENT = "content";
    private static final String FORMAT = "format";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_INVITE = 0;
    private static final int SCAN_CARD = 49374;
    private CoordinatorLayout co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        co = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
                    IntentIntegrator scanCard = new IntentIntegrator(this);
                    scanCard.initiateScan();
                }else if (id == R.id.action_share_app){
                    Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                            .setMessage(getString(R.string.invitation_message))
                           // .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                            .setCallToActionText(getString(R.string.invitation_cta))
                            .build();
                            startActivityForResult(intent, REQUEST_INVITE);
            //Adds a test card, without scanning
                }else if(id == R.id.action_add_test_card){
                    Intent showScannedCard = new Intent(this, AddCard.class);
                    showScannedCard.putExtra(CONTENT, "123456789");
                    showScannedCard.putExtra(FORMAT, "CODE_128");
                    startActivity(showScannedCard);
                }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Check how many invitations were sent and log a message
                // The ids array contains the unique invitation ids for each invitation sent
                // (one for each contact select by the user). You can use these for analytics
                // as the ID will be consistent on the sending and receiving devices.
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                Log.d(TAG, getString(R.string.sent_invitations_fmt, ids.length));
            } else {
                // Sending failed or it was canceled, show failure message to the user
                showMessage(getString(R.string.share_app_cancel_message));
            }
        } else if (requestCode == SCAN_CARD) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult != null) {
                Intent showScannedCard = new Intent(this, AddCard.class);
                showScannedCard.putExtra(CONTENT, scanResult.getContents());
                showScannedCard.putExtra(FORMAT, scanResult.getFormatName());
                startActivity(showScannedCard);
            }
        }
    }

    private void showMessage(String msg) {
        Snackbar snackbar = Snackbar.make(co, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}

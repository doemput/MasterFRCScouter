package com.adithyasairam.android.masterfrcscouter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExportDataActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ExportDataActivity";
    Button exportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_data);
        exportData = (Button)(findViewById(R.id.exportDataButton));
        exportData.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exportDataButton:
                boolean done = sendEmail(getFilesToSend());
                Log.i(TAG, "Export of data via Email complete: " + done);
                if (done) { startActivity(new Intent(this, MainActivity.class)); }
                break;
        }
    }

    public File[] getFilesToSend() {
        File sdcard = Environment.getExternalStorageDirectory();
        return sdcard.listFiles();
    }

    public boolean sendEmail(File[] filesToAttach) {
        try {
            SharedPreferences prefs = getPreferences(0);
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{prefs.getString("email", Constants.DefaultEmail)});
            email.putExtra(Intent.EXTRA_SUBJECT, Scouter.scouterName + "'s scouting data");
            email.putExtra(Intent.EXTRA_TEXT, "From: " + prefs.getString("event_sel", "IRI2015"));
            email.setType("text/plain");
            for (File f : filesToAttach) { email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f)); }
            startActivity(Intent.createChooser(email, "Choose an Email client: "));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

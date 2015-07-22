package com.adithyasairam.android.masterfrcscouter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public ArrayList<File> getFilesToSend() {
        File files = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter" + "/MatchData");
        return new ArrayList<File>(Arrays.asList(files.listFiles()));
    }

    public boolean sendEmail(List<File> filesToAttach) {
        try {
            SharedPreferences prefs = getPreferences(0);

            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("text/plain");
            ArrayList<Uri> arrayUri = new ArrayList<Uri>();
            for (File f : filesToAttach) {
                arrayUri.add(Uri.fromFile(f));
            }
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayUri);
            intent.putExtra(Intent.EXTRA_SUBJECT, Scouter.scouterName + "'s scouting data");
            intent.putExtra(Intent.EXTRA_TEXT, "Data From: " + prefs.getString("event_sel", "IRI2015"));
            startActivity(intent);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

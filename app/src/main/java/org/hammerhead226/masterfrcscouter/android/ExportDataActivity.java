package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.adithyasairam.masterfrcscouter.Scouting.Scouter;

import org.hammerhead226.masterfrcscouter.Utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;

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
                sendEmail(getFilesToSend());
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public List<File> getFilesToSend() {
        addRealmFile();
        File files = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter" + "/MatchData");
        List<File> fileList = Arrays.asList(files.listFiles());
        return fileList;
    }

    private void addRealmFile() {
        Realm realm = Realm.getDefaultInstance();
        File exportRealmFile = null;
        try {
            exportRealmFile = new File(Constants.getMatchDataDir(), "matches.realm");
            exportRealmFile.delete();
            realm.writeCopyTo(exportRealmFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        realm.close();
    }

    //FIXME: Test!!!
    public void sendEmail(List<File> filesToAttach) {
        try {
            SharedPreferences prefs = getPreferences(0);
            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("text/plain");
            for (File f : filesToAttach) {
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
            intent.putExtra(Intent.EXTRA_EMAIL, "Exported Scouting Data");
            intent.putExtra(Intent.EXTRA_SUBJECT, Scouter.scouterName + "'s scouting data");
            intent.putExtra(Intent.EXTRA_TEXT, "Data From: " + prefs.getString("event_sel", "Some FRC Event"));
            startActivity(Intent.createChooser(intent, "Export Data via Email"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

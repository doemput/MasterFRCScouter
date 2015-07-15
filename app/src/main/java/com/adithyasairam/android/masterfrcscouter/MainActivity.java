package com.adithyasairam.android.masterfrcscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = "MainActivity";

    public static Scouter instance;

    Button matchScout, pitScout, info, TBABtn, importData, exportData, takeBreak, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());
        instance = new Scouter();
        setContentView(R.layout.activity_main);
        matchScout = (Button)(findViewById(R.id.matchScout));
        matchScout.setOnClickListener(this);
        pitScout = (Button)(findViewById(R.id.pitScout));
        //pitScout.setOnClickListener(this);
        info = (Button) (findViewById(R.id.info));
        //info.setOnClickListener(this);
        TBABtn = (Button) (findViewById(R.id.TBAbtn));
        //TBABtn.setOnClickListener(this);
        importData = (Button) (findViewById(R.id.dataImport));
        //importData.setOnClickListener(this);
        exportData = (Button) (findViewById(R.id.dataExport));
        //exportData.setOnClickListener(this);
        takeBreak = (Button)(findViewById(R.id.takeBreak));
        takeBreak.setOnClickListener(this);
        logOut = (Button)(findViewById(R.id.logOut));
        logOut.setOnClickListener(this);
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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.matchScout:
                startActivity(new Intent(this, MatchScoutActivity.class));
                break;
            case R.id.pitScout:
                startActivity(new Intent(this, PitScoutActivity.class));
                break;
            case R.id.info:
                startActivity(new Intent(this, InfoActivity.class));
                break;
            case R.id.TBAbtn:
                startActivity(new Intent(this, TheBlueAllianceActivity.class));
                break;
            case R.id.dataImport:
                startActivity(new Intent(this, ImportDataActivity.class));
                break;
            case R.id.dataExport:
                startActivity(new Intent(this, ExportDataActivity.class));
                break;
            case R.id.takeBreak:
                instance.startABreak();
                startActivity(new Intent(this, BreakTimeActivity.class));
                break;
            case (R.id.logOut):
                instance.endSession();
                Log.i(TAG, "Scouting session ended at: " + instance.sessionEndTime + ".");
                Log.i(TAG, "Scouting session lasted: " + Math.abs(TimeUnit.MILLISECONDS.toMinutes(instance.getTotalTimeScouted())) + " minutes.");
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}

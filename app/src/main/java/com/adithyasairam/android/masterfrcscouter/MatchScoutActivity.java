package com.adithyasairam.android.masterfrcscouter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MatchScoutActivity extends AppCompatActivity implements View.OnClickListener{

    Button autonButton;
    Switch redOrBlueAlliance;
    EditText teamNumberET, matchNumberET, scoutingPositonET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_scout);
        autonButton = (Button)(findViewById(R.id.autonButton));
        autonButton.setOnClickListener(this);
        redOrBlueAlliance = (Switch)(findViewById(R.id.scoutingAllianceColorRedToggle));
        redOrBlueAlliance.setOnClickListener(this);
        teamNumberET = (EditText)(findViewById(R.id.teamNumberET));
        matchNumberET = (EditText)(findViewById(R.id.matchNumberET));
        scoutingPositonET = (EditText)(findViewById(R.id.scoutingPositionET));
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
            case R.id.autonButton:
                parseData();
                startActivity(new Intent(this, AutonMatchScoutActivity.class));
                break;
        }
    }

    private void parseData() {
        boolean redAlliance = redOrBlueAlliance.isChecked();
        String allianceColor = "";
        if (redAlliance) { allianceColor = "Red"; }
        else { allianceColor = "Blue"; }
        int teamNum = Integer.parseInt(teamNumberET.getText().toString());
        int matchNum = Integer.parseInt(matchNumberET.getText().toString());
        int scoutingPos = Integer.parseInt(scoutingPositonET.getText().toString());
        DataParsing.setBasicInfo(teamNum, matchNum, scoutingPos, allianceColor);
    }
}

package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;

@Changeable(source = MatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchScoutActivity extends AppCompatActivity implements View.OnClickListener{

    Button autonButton, goHomeButton;
    EditText teamNumberET, matchNumberET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_scout);
        autonButton = (Button)(findViewById(R.id.autonButton));
        autonButton.setOnClickListener(this);
        goHomeButton = (Button) (findViewById(R.id.goHomeButton));
        goHomeButton.setOnClickListener(this);
        teamNumberET = (EditText)(findViewById(R.id.teamNumberET));
        matchNumberET = (EditText)(findViewById(R.id.matchNumberET));
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
            case R.id.goHomeButton:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.autonButton:
                parseData();
                startActivity(new Intent(this, AutonMatchScoutActivity.class));
                break;
        }
    }

    private void parseData() {
        int teamNum = Integer.parseInt(teamNumberET.getText().toString());
        int matchNum = Integer.parseInt(matchNumberET.getText().toString());
        DataParsing.setBasicInfo(teamNum, matchNum);
    }
}

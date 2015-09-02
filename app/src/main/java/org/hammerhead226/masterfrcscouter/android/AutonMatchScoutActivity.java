package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;

@Changeable(source = AutonMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class AutonMatchScoutActivity extends AppCompatActivity implements View.OnClickListener{

    CheckBox driveToAutoZoneCB, roboSetCB, toteSetCB, stackedToteSetCB, binSetCB, didNothingCB, canBurgeledCB;
    EditText cansGrabbed, acquiredStepBins, autoFouls;
    Button goToTeleop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton_match_scout);
        driveToAutoZoneCB = (CheckBox)(findViewById(R.id.driveToAutoZoneBool));
        driveToAutoZoneCB.setOnClickListener(this);
        roboSetCB = (CheckBox)(findViewById(R.id.robotSetBool));
        roboSetCB.setOnClickListener(this);
        toteSetCB = (CheckBox)(findViewById(R.id.toteSetBool));
        toteSetCB.setOnClickListener(this);
        stackedToteSetCB = (CheckBox)(findViewById(R.id.stackedToteSetBool));
        stackedToteSetCB.setOnClickListener(this);
        binSetCB = (CheckBox)(findViewById(R.id.binSetBool));
        binSetCB.setOnClickListener(this);
        didNothingCB = (CheckBox)(findViewById(R.id.didNothingBool));
        didNothingCB.setOnClickListener(this);
        canBurgeledCB = (CheckBox)(findViewById(R.id.canBurgeledBool));
        canBurgeledCB.setOnClickListener(this);
        cansGrabbed = (EditText)(findViewById(R.id.numCansGrabbed));
        acquiredStepBins = (EditText)(findViewById(R.id.acquiredStepBins));
        autoFouls = (EditText)(findViewById(R.id.numAutoFouls));
        goToTeleop = (Button)(findViewById(R.id.goToTeleop));
        goToTeleop.setOnClickListener(this);
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
            case R.id.canBurgeledBool:
                cansGrabbed.setOnClickListener(this);
                break;
            case R.id.goToTeleop:
                parseData();
                startActivity(new Intent(this, TeleopMatchScoutActivity.class));
                break;
        }
    }

    private void parseData() {
        try {
            boolean dTA = driveToAutoZoneCB.isChecked();
            boolean rS = roboSetCB.isChecked();
            boolean tS = toteSetCB.isChecked();
            boolean sTS = stackedToteSetCB.isChecked();
            boolean bS = binSetCB.isChecked();
            boolean dN = didNothingCB.isChecked();
            boolean cG = canBurgeledCB.isChecked();
            int cGN = Integer.parseInt(cansGrabbed.getText().toString());
            int aB = Integer.parseInt(acquiredStepBins.getText().toString());
            int aF = Integer.parseInt(autoFouls.getText().toString());
            DataParsing.setAutonInfo(dTA, rS, tS, sTS, bS, dN, cG, cGN, aB, aF);
        }
        catch (Exception e) { }
    }
}

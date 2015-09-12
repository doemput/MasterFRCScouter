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
import android.widget.Switch;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.RRStack;

import java.util.ArrayList;

@Changeable(source = TeleopMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class TeleopMatchScoutActivity extends AppCompatActivity implements View.OnClickListener{

    Button stackSubmit, nextButton;
    EditText numTeleFouls, stackHeight, numCansCapped, allianceScore;
    Switch coopSet, coopStack, knockedOverStacks, poorlyDrivenRobot, didCapCans;
    CheckBox canOnTopWithLitter, canOnTop, totesFromHF, totesFromLF;
    public static ArrayList<RRStack> rrStackArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rrStackArrayList = new ArrayList<RRStack>();
        setContentView(R.layout.activity_teleop_match_scout);
        numTeleFouls = (EditText)(findViewById(R.id.numTeleFoulsET));
        numCansCapped = (EditText) (findViewById(R.id.numCansCappedET));
        allianceScore = (EditText) (findViewById(R.id.allianceScoreET));
        canOnTopWithLitter = (CheckBox)(findViewById(R.id.canOnTopWithLitterCB));
        canOnTop = (CheckBox)(findViewById(R.id.canOnTopCB));
        coopSet = (Switch)(findViewById(R.id.coopSetSwitch));
        coopStack = (Switch)(findViewById(R.id.coopStackSwitch));
        knockedOverStacks = (Switch) (findViewById(R.id.knockedStackSwitch));
        poorlyDrivenRobot = (Switch) (findViewById(R.id.badDrivingSwitch));
        didCapCans = (Switch) (findViewById(R.id.canCappedSwitch));
        stackHeight = (EditText)(findViewById(R.id.stackHeightET));
        stackSubmit = (Button)(findViewById(R.id.submitStack));
        stackSubmit.setOnClickListener(this);
        totesFromHF = (CheckBox) (findViewById(R.id.totesFromHumanFeeder));
        totesFromLF = (CheckBox) (findViewById(R.id.totesFromLandfill));
        nextButton = (Button) (findViewById(R.id.nextBttn));
        nextButton.setOnClickListener(this);
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
            case R.id.submitStack:
                try {
                    int sH = Integer.parseInt(stackHeight.getText().toString());
                    boolean cOTWL = canOnTopWithLitter.isChecked();
                    boolean cOT = canOnTop.isChecked();
                    rrStackArrayList.add(new RRStack(sH, cOTWL, cOT));
                    stackHeight.setText("");
                    canOnTopWithLitter.setChecked(false);
                    canOnTop.setChecked(false);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.nextBttn:
                try {
                    parseData();
                    startActivity(new Intent(this, MatchScoutSubmitActivity.class));
                    break;
                }
                catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void parseData() {
        try {
            int nTF = Integer.parseInt(numTeleFouls.getText().toString());
            int nCC = Integer.parseInt(numCansCapped.getText().toString());
            int aScore = Integer.parseInt(allianceScore.getText().toString());
            boolean cSet = coopSet.isChecked();
            boolean cStack = coopStack.isChecked();
            boolean stackDown = knockedOverStacks.isChecked();
            boolean badDriving = poorlyDrivenRobot.isChecked();
            boolean didCap = didCapCans.isChecked();
            String toteSource = getToteSource();
            DataParsing.setTeleopInfo(nTF, nCC, aScore, cSet, cStack, stackDown, badDriving, didCap, toteSource, rrStackArrayList);
        }
        catch (Exception e) { }
    }

    public String getToteSource() {
        String source = "";
        boolean HF = totesFromHF.isChecked();
        boolean LF = totesFromLF.isChecked();
        if (HF) {
            return "Human Feeder";
        } else if (LF) {
            return "Land Fill";
        } else {
            return "Both";
        }
    }
}

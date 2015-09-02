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
import android.widget.TextView;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;
import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.RRStack;

import java.util.ArrayList;

@Changeable(source = TeleopMatchScoutActivity.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class TeleopMatchScoutActivity extends AppCompatActivity implements View.OnClickListener{

    Button stackSubmit, submit;
    EditText numHumanLitterThrown, numLitterPushed, numTeleFouls, stackHeight;
    TextView comments;
    Switch coopSet, coopStack;
    CheckBox canOnTopWithLitter, canOnTop;
    public static ArrayList<RRStack> rrStackArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rrStackArrayList = new ArrayList<RRStack>();
        setContentView(R.layout.activity_teleop_match_scout);
        numHumanLitterThrown = (EditText)(findViewById(R.id.numHumanLitterThrownET));
        numLitterPushed = (EditText)(findViewById(R.id.numLitterPushedET));
        numTeleFouls = (EditText)(findViewById(R.id.numTeleFoulsET));
        canOnTopWithLitter = (CheckBox)(findViewById(R.id.canOnTopWithLitterCB));
        canOnTop = (CheckBox)(findViewById(R.id.canOnTopCB));
        coopSet = (Switch)(findViewById(R.id.coopSetSwitch));
        coopStack = (Switch)(findViewById(R.id.coopStackSwitch));
        stackHeight = (EditText)(findViewById(R.id.stackHeightET));
        stackSubmit = (Button)(findViewById(R.id.submitStack));
        stackSubmit.setOnClickListener(this);
        comments = (TextView)(findViewById(R.id.commentsTextArea));
        submit = (Button)(findViewById(R.id.submitBttn));
        submit.setOnClickListener(this);
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
            case R.id.submitBttn:
                try {
                    parseData();
                    DataParsing.saveMatch();
                    startActivity(new Intent(this, MatchScoutActivity.class));
                    finish();
                    break;
                }
                catch (Exception e) { e.printStackTrace(); }
            case R.id.submitStack:
                try {
                    int sH = Integer.parseInt(stackHeight.getText().toString());
                    boolean cOTWL = canOnTopWithLitter.isChecked();
                    boolean cOT = canOnTop.isChecked();
                    rrStackArrayList.add(new RRStack(sH, cOTWL, cOT));
                    stackHeight.setText("");
                    canOnTopWithLitter.setChecked(false);
                    canOnTop.setChecked(false);
                }
                catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void parseData() {
        try {
            int nHLT = Integer.parseInt(numHumanLitterThrown.getText().toString());
            int nLP = Integer.parseInt(numLitterPushed.getText().toString());
            int nTF = Integer.parseInt(numTeleFouls.getText().toString());
            boolean cSet = coopSet.isChecked();
            boolean cStack = coopStack.isChecked();
            DataParsing.setTeleopInfo(nHLT, nLP, nTF, cSet, cStack, rrStackArrayList);
            DataParsing.setExtraInfo(comments.getText().toString());
        }
        catch (Exception e) { }
    }
}

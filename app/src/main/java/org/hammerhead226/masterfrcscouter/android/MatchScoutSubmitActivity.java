package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing;

public class MatchScoutSubmitActivity extends AppCompatActivity implements View.OnClickListener {
    Button submit;
    TextView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_scout_submit);
        submit = (Button) (findViewById(R.id.nextBttn));
        submit.setOnClickListener(this);
        comments = (TextView) (findViewById(R.id.commentsTextArea));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match_scout_submit, menu);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBttn:
                try {
                    parseData();
                    DataParsing.saveMatch();
                    startActivity(new Intent(this, MatchScoutActivity.class));
                    finish();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void parseData() {
        try {
            DataParsing.setExtraInfo(comments.getText().toString());
        } catch (Exception e) {
        }
    }
}

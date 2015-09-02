package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.adithyasairam.masterfrcscouter.Scouting.Scouter;

public class BreakTimeActivity extends AppCompatActivity implements View.OnClickListener {

    Scouter instance;

    Button endBreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = new Scouter();
        setContentView(R.layout.activity_break_time);
        endBreak = (Button)(findViewById(R.id.resume));
        endBreak.setOnClickListener(this);
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
            case R.id.resume:
                instance.endABreak();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

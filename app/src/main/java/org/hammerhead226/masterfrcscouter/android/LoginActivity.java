package org.hammerhead226.masterfrcscouter.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.adithyasairam.masterfrcscouter.Scouting.Scouter;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";
    public static String scouterName;
    public static int scoutingPosition;
    public static boolean isRedScouter;

    EditText Name, Position;
    Button bLogin;
    CheckBox rA, bA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);
        Name = (EditText)findViewById(R.id.scouterName);
        Position = (EditText) findViewById(R.id.editText);
        rA = (CheckBox) findViewById(R.id.rACheckbox);
        rA.setOnClickListener(this);
        bA = (CheckBox) findViewById(R.id.bACheckbox);
        bA.setOnClickListener(this);
        bLogin = (Button)(findViewById(R.id.bLogin));
        bLogin.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            //doesn't work: no access to settings in Login
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.rACheckbox:
                if ((rA.isChecked() && bA.isChecked())) {
                    Toast.makeText(getApplicationContext(), "You have selected to scout both the Red and Blue Alliance.!", Toast.LENGTH_SHORT).show();
                    break;
                }
                //sets the color to red if they select the red checkbox
                if(rA.isChecked()){
                    bLogin.setBackgroundColor(Color.parseColor("#F44336"));
                }else{
                    bLogin.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                isRedScouter = true;
                rA.setActivated(true);
                bA.setActivated(false);
                break;
            case R.id.bACheckbox:
                if ((rA.isChecked() && bA.isChecked())) {
                    Toast.makeText(getApplicationContext(), "You have selected to scout both the Red and Blue Alliance.!", Toast.LENGTH_SHORT).show();
                    break;
                }
                //sets the button color to blue if they select the blue checkbox
                if(bA.isChecked()){
                    bLogin.setBackgroundColor(Color.parseColor("#2196F3"));
                }else{
                    bLogin.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                isRedScouter = false;
                bA.setActivated(true);
                rA.setActivated(false);
                break;
            case R.id.bLogin:
                try {
                    if ((rA.isChecked() && bA.isChecked())) {
                        Toast.makeText(getApplicationContext(), "You have selected to scout both the Red and Blue Alliance!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    scouterName = Name.getText().toString();
                    scoutingPosition = Integer.parseInt(Position.getText().toString());
                    Log.i(TAG, "Login complete.");
                    Scouter.startSession();
                    Log.i(TAG, "Scouting session started at: " + Scouter.sessionStartTime + ".");
                    Log.i(TAG,  "Switching to main activity.");
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;
                }
                catch (Exception e) {e.printStackTrace(); }
        }
    }
}

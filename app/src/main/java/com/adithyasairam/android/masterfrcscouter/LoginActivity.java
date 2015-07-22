package com.adithyasairam.android.masterfrcscouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";
    public static String scouterName;
    EditText Name;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText)findViewById(R.id.scouterName);
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
            case R.id.bLogin:
                try {
                    scouterName = Name.getText().toString();
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

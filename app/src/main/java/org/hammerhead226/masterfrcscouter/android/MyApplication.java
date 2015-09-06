package org.hammerhead226.masterfrcscouter.android;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Adi on 9/6/2015.
 * Source: http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setRealmConfig();
    }


    public void setRealmConfig() {
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).name("matches.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    public synchronized SQLiteDatabase initDB() {
        SQLiteDatabase db;
        synchronized (this) {
            db = openOrCreateDatabase("Matches.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            try {
                final String CREATE_TABLE_CONTAIN = "CREATE TABLE IF NOT EXISTS matches";
                db.execSQL(CREATE_TABLE_CONTAIN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return db;
    }
}

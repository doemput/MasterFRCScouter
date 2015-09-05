package com.adithyasairam.masterfrcscouter.Scouting;

import com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataStorage;

import org.hammerhead226.masterfrcscouter.android.LoginActivity;

import java.util.Calendar;

/**
 * Created by Adi on 7/13/2015.
 */

public class Scouter {
    public static String scouterName = LoginActivity.scouterName;
    public static int scoutingPosition = LoginActivity.scoutingPosition;
    public static boolean isRedScouter = LoginActivity.isRedScouter;
    public static long sessionStartTime;
    public long sessionEndTime;
    public long breakTimeStart;
    public long breakTimeEnd;
    public long breakTime;
    public Calendar c;
    public Scouter() {
        c = Calendar.getInstance();
        sessionStartTime = c.getTimeInMillis();
        sessionEndTime = 0;
        breakTimeStart = 0;
        breakTimeEnd = 0;
        breakTime = 0;
    }
    public static void startSession() { sessionStartTime = Calendar.getInstance().getTimeInMillis(); }

    public void endSession() {
        sessionEndTime = c.getTimeInMillis();
        DataStorage.writeAsSQL();
    }
    public void startABreak() { breakTimeStart = c.getTimeInMillis(); }
    public void endABreak() {
        breakTimeEnd = c.getTimeInMillis();
        breakTime += (breakTimeEnd - breakTimeStart);
        breakTimeStart = 0;
        breakTimeEnd = 0;
    }
    public long getTotalTimeScouted() { return ((sessionEndTime - sessionStartTime) - breakTime); }
}

package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.Context;
import android.widget.Toast;

import com.adithyasairam.Utils.EzCSV.CSVWriter;

import org.hammerhead226.masterfrcscouter.Utils.DataRW;
import org.hammerhead226.masterfrcscouter.android.MainActivity;

import io.realm.Realm;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {

    public DataStorage() {
    }

    public static void addMatch(Context c) {
        appendAMatchToCSVFile();
        //appendAMatchToRealmDB();
        Toast.makeText(c, "Match Saving Complete!", Toast.LENGTH_SHORT).show();
    }

    public static void appendAMatchToCSVFile() {
        Match match = new Match();
        String[] headers = new String[]{
                "MatchNumber",
                "ScouterName",
                "TeamNumber",
                "ScoutingPosition",
                "RandomID",
                "AutonMode",
                "NumberOfAcquiredBinsInAuton",
                "NumberOfAutonFoulPos",
                "NumAutonCansAttemptedToBurgle",
                "NumAutonCansBurgled",
                "CanBurglingSpeed",
                "WasACOOPSetScoredInTeleop",
                "WasACOOPStackScoredInTeleop",
                "AreStacksDown",
                "RobotWasPoorlyDriven",
                "RobotDidCap",
                "NumberOfCaps",
                "NumberOfStacksScoredInTeleop",
                "ToteSource",
                "stack1",
                "stack2",
                "stack3",
                "stack4",
                "stack5",
                "NumberOfTeleopFoulPos",
                "ThisRobotsAproxAutonScore",
                "ThisRobotsAproxTeleopScore",
                "ThisRobotsAproxCOOPScore",
                "ThisRobotsAproxTotalScore",
                "TotalAllianceScore",
                "Comments"
        };
        CSVWriter csvWriter = new CSVWriter(MainActivity.csvFile, headers);
        csvWriter.write(match);
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
    }


    public static void appendAMatchToRealmDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MatchData matchData = realm.createObject(MatchData.class);
        realm.commitTransaction();
    }
}

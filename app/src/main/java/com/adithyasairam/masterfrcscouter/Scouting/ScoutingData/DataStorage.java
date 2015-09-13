package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.Context;
import android.widget.Toast;

import com.adithyasairam.Utils.EzCSV.CSVWriter;
import com.google.common.io.Files;

import org.hammerhead226.masterfrcscouter.Utils.DataRW;
import org.hammerhead226.masterfrcscouter.android.MainActivity;

import java.nio.charset.Charset;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {
    public static int writeCount = 0;

    public DataStorage() {
    }

    public static void addMatch(Context c) {
        appendAMatchToCSVFile();
        appendAMatchToRealmDB();
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
                "NumberOfAutonFoulPoints",
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
                "NumberOfTeleopFoulPoints",
                "ThisRobotsAproxAutonScore",
                "ThisRobotsAproxTeleopScore",
                "ThisRobotsAproxCOOPScore",
                "ThisRobotsAproxTotalScore",
                "TotalAllianceScore",
                "Comments"
        };
        Match thisMatch = new Match();
        String[] data = new String[]{
                Integer.toString(Match.MatchNumber),
                Match.ScouterName,
                Integer.toString(Match.TeamNumber),
                Match.ScoutingPosition,
                Match.RandomID,
                Match.AutonMode,
                Integer.toString(Match.NumberOfAcquiredBinsInAuton),
                Integer.toString(Match.NumberOfAutonFoulPoints),
                Integer.toString(Match.NumAutonCansAttemptedToBurgle),
                Integer.toString(Match.NumAutonCansBurgled),
                Double.toString(Match.CanBurglingSpeed),
                Boolean.toString(Match.WasACOOPSetScoredInTeleop),
                Boolean.toString(Match.WasACOOPStackScoredInTeleop),
                Boolean.toString(Match.AreStacksDown),
                Boolean.toString(Match.RobotWasPoorlyDriven),
                Boolean.toString(Match.RobotDidCap),
                Integer.toString(Match.NumberOfCaps),
                Integer.toString(Match.NumberOfStacksScoredInTeleop),
                Match.ToteSource,
                Integer.toString(Match.NumberOfTeleopFoulPoints),
                Integer.toString(Match.ThisRobotsAproxAutonScore),
                Integer.toString(Match.ThisRobotsAproxTeleopScore),
                Integer.toString(Match.ThisRobotsAproxCOOPScore),
                Integer.toString(Match.ThisRobotsAproxTotalScore),
                Integer.toString(Match.TotalAllianceScore),
                Match.Comments
        };
        CSVWriter csvWriter = new CSVWriter(MainActivity.csvFile);
        if (writeCount < 1) {
            csvWriter.writeHeaders(headers);
            writeCount++;
        }
        csvWriter.write(data);
        writeCount++;
        DataRW.addMapEntry("csvFile", MainActivity.csvFile);
    }

    public static void appendAMatchToRealmDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MatchData matchData = realm.createObject(MatchData.class);
        realm.commitTransaction();
    }

    private static void debugRead() {
        try {
            List<String> lines = Files.readLines(MainActivity.csvFile, Charset.defaultCharset());
            for (String s : lines) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

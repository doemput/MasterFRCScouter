package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.ContentValues;
import android.os.Environment;

import org.hammerhead226.masterfrcscouter.Utils.DataRW;
import org.hammerhead226.masterfrcscouter.android.MainActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adi on 8/30/2015.
 */
public class DataStorage {
    private static List<MatchData> matches;

    public DataStorage() {
        init();
    }

    public static void addMatch(MatchData matchData) {
        if (matches == null) {
            init();
        }
        matches.add(matchData);
        writeListToDisk();
    }

    public MatchData getMatch(int num) {
        return matches.get(num);
    }

    public List<MatchData> getMatches() {
        return matches;
    }

    public static void writeAsSQL() {
        for (int i = 0; i < matches.size(); i++) {
            MatchData match = matches.get(i);
            ContentValues insertValues = new ContentValues();
            try {
                for (Field f : match.getClass().getFields()) {
                    insertValues.put(f.getName(), f.get(null).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //insertValues.put("Match Number", match.MatchNumber);
            //insertValues.put("Scouter Name", match.ScouterName);
            //insertValues.put("Trans", 1);
            //insertValues.put("EntryDate", "04/06/2011");
            MainActivity.database.insert("matches", null, insertValues);
        }
    }

    private static void writeListToDisk() {
        File file = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter" + "/MatchData" + "/matches.obj");
        file.mkdirs();
        DataRW.writeObjectAsFile(file, matches, "matchList");
    }

    private static void init() {
        matches = new ArrayList<MatchData>();
    }
}

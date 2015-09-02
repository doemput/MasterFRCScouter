package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import org.hammerhead226.masterfrcscouter.Utils.DataRW;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
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


    public static void writeAsCSV() {
        File csv = new File("DB.csv");
        try {
            CsvListWriter csvListWriter = new CsvListWriter(new FileWriter(csv), CsvPreference.EXCEL_PREFERENCE);
            csvListWriter.write(matches);
            DataRW.addMapEntry("DBCSV", csv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeAsSQL() {

    }

    private static void writeListToDisk() {
        File file = new File("matches.obj");
        DataRW.writeObjectAsFile(file, matches, "matchList");
    }

    private static void init() {
        matches = new ArrayList<MatchData>();
    }
}

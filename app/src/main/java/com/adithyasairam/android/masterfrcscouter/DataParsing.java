package com.adithyasairam.android.masterfrcscouter;

import android.os.Environment;
import android.util.Log;

import com.cedarsoftware.util.io.JsonWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.List;

/**
 * Created by Adi on 7/14/2015.
 */
public class DataParsing {
    public static String TAG = "DataParsing";
    public static int teamNumber, matchNumber, scoutingPosition; public static String allianceColor; //Basic Info
    public static boolean droveToAuto, roboSet, toteSet, stackedToteSet, binSet, didNothing, canGrabbed; public static int numCansGrabbed, acquiredBins, autoFouls; //Auton Info
    public static int  numHumanLitterThrown, numLitterPushed, numTeleFoulsPoints; public static boolean coopSet, coopStack; public static List<RRStack> rrStackList; //Teleop Info
    public static String comments; //Extra Info
    public static void setBasicInfo(int tN, int mN, int sP, String allianceC) {
        teamNumber = tN;
        matchNumber = mN;
        scoutingPosition = sP;
        allianceColor = allianceC;
    }
    public static void setAutonInfo(boolean dTA, boolean rS, boolean tS, boolean sTS, boolean bS, boolean dN, boolean cG, int nCG, int aB, int aF) {
        droveToAuto = dTA;
        roboSet = rS;
        toteSet = tS;
        stackedToteSet = sTS;
        binSet = bS;
        didNothing = dN;
        canGrabbed = cG;
        numCansGrabbed = nCG;
        acquiredBins = aB;
        autoFouls = aF;
    }
    public static void setTeleopInfo(int nHLT, int nLP, int nTFP, boolean cSet, boolean cStack, List<RRStack> rSL) {
        numHumanLitterThrown = nHLT;
        numLitterPushed = nLP;
        numTeleFoulsPoints = nTFP;
        coopSet = cSet;
        coopStack= cStack;
        rrStackList = rSL;
    }

    public static void setExtraInfo(String c) { comments = c; }

    /**
     * @return a JSON formatted String with all of this matches's scouting data.
     */
    public static String makeString() {
        try {
            String nl = System.lineSeparator();
            String data = "";
            //General Info:
            data += "Scouter Name: " + Scouter.scouterName + nl;
            data += "Scouter ID: " + Scouter.idNum + nl;
            data += "Random ID: " + UUID.randomUUID().toString() + nl;
            data += "Team Number: " + teamNumber + nl;
            data += "Match Number: " + matchNumber + nl;
            data += "Scouting Position: " + allianceColor + scoutingPosition + nl;
            //Auton:
            data += "Robot drove to Auto Zone during Auton: " + droveToAuto + nl;
            data += "Robot did noting during Auton: " + didNothing + nl;
            data += "Robot Set scored during Auton: " + roboSet + nl;
            data += "Robot scored a Tote Set during Auton: " + toteSet + nl;
            data += "Robot scored a Stacked Tote Set during Auton: " + stackedToteSet + nl;
            data += "Robot scored a Bin Set during Auton: " + binSet + nl;
            data += "Robot Can Grabbed during Auton: " + canGrabbed + nl;
            if (canGrabbed) { data += "Number of Cans Grabbed during Auton: " + numCansGrabbed + nl; }
            data += "Number of Acquired Bins during Auton: " + acquiredBins + nl;
            data += "Number of Auton Foul Points: " + autoFouls + nl;
            //Teleop:
            data += "Number of Human Litter Thrown during Teleop: " + numHumanLitterThrown + nl;
            data += "Number of Human Litter Pushed during Teleop: " + numLitterPushed + nl;
            data += "CO-OP Set scored during Teleop: " + coopSet + nl;
            data += "CO-OP Stack scored during Teleop: " + coopStack + nl;
            data += "Number of Stacks scored during Teleop: " + rrStackList.size() + nl;
            for (int i = 0; i < rrStackList.size(); i++) { data += "Stack " + (i + 1) + ": " + rrStackList.toString() + nl; }
            data += "Number of Teleop Foul Points: " + numTeleFoulsPoints + nl;
            //Scoring:
            data += "This Robot's Aprox Auton Score: " + calculateThisRobotsAproxAutonScore() + nl;
            data += "This Robot's Aprox Teleop Score: " + calculateThisRobotsAproxTeleopScore() + nl;
            data += "This Robot's Aprox CO-OP Score: " + calculateThisRobotsAproxCoopScore() + nl;
            data += "This Robot's Aprox Total Score: " + calculateThisRobotsAproxTotalScore() + nl;
            //Other:
            if (comments != null || comments.length() > 0) { data += "Other Remarks: " + comments; }
            else { data += "Other Remarks: None"; }
            Log.i(TAG, "Writing all the data to a string completed sucessfully! :)");
            return data;
        }
        catch (Exception e) {
            Log.i(TAG, "Writing all the data to a string did not complete sucessfully. :(");
            e.printStackTrace();
        }
        return null;
    }

    public static void writeDataAsJSON(String data) {
        String fileName = Constants.OfficialEventCode + "_" + "Match" + matchNumber + "_" + "Team" + teamNumber + ".json";
        try {
            File appDir = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter");
            appDir.mkdirs();
            File dir = new File(appDir.getAbsolutePath() + "/" + Constants.OfficialEventCode );
            dir.mkdirs();
            File file = new File(dir.getAbsolutePath(), fileName);
            JsonWriter jsonWriter = new JsonWriter(new FileOutputStream(file));
            String formatedJSONString = JsonWriter.formatJson(data);
            jsonWriter.write(formatedJSONString);
            jsonWriter.close();
            Log.i(TAG, "Writing JSON data to a file completed sucessfully! :)");
            Log.d(TAG, "JSON File saved to: " + file.getPath());
        }
        catch (Exception e) {
            Log.i(TAG, "Writing JSON data to a file did not complete sucessfully. :(");
            e.printStackTrace();
        }
    }
    public static int calculateThisRobotsAproxAutonScore() {
        int score = 0;
        if (roboSet) { score += 4; }
        if (toteSet) { score += 6; }
        if (stackedToteSet) { score += 20; } //Buzz Buzz Buz
        score -= autoFouls;
        return score;
    }
    public static int calculateThisRobotsAproxTeleopScore() {
        int score = 0;
        for (RRStack r: rrStackList) { score += r.calculateStackScore(); }
        score += numLitterPushed;
        score += numHumanLitterThrown * 4;
        score -= numTeleFoulsPoints;
        return score;
    }
    public static int calculateThisRobotsAproxCoopScore() {
        int score = 0;
        if (coopSet) { score += 20; }
        if (coopStack) { score += 40; }
        return score;
    }
    public static int calculateThisRobotsAproxTotalScore() {
        return calculateThisRobotsAproxAutonScore() +
                calculateThisRobotsAproxTeleopScore() +
                calculateThisRobotsAproxCoopScore();
    }
}

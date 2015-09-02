package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.Scouter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Adi on 7/14/2015.
 */

@Changeable(source = com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class DataParsing {
    public static String TAG = "DataParsing";
    public static int teamNumber, matchNumber, scoutingPosition;
    public static String scouterName, allianceColor; //Basic Info
    public static boolean droveToAuto, roboSet, toteSet, stackedToteSet, binSet, didNothing, canGrabbed;
    public static int numCansGrabbed, acquiredBins, autoFouls; //Auton Info
    public static int numHumanLitterThrown, numLitterPushed, numTeleFoulsPoints;
    public static boolean coopSet, coopStack;
    public static List<RRStack> rrStackList; //Teleop Info
    public static String comments; //Extra Info

    public static void setBasicInfo(int tN, int mN) {
        teamNumber = tN;
        matchNumber = mN;
        scouterName = Scouter.scouterName;
        scoutingPosition = Scouter.scoutingPosition;
        allianceColor = Scouter.isRedScouter ? "Red" : "Blue";
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
        coopStack = cStack;
        rrStackList = rSL;
    }

    public static void setExtraInfo(String c) {
        comments = c;
    }

    public static void saveMatch() {
        MatchData matchData = new MatchData();
        DataStorage.addMatch(matchData);
        clearFields();
    }

    public static void clearFields() {
        for (Field f : com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing.class.getFields()) {
            try {
                f.setAccessible(true);
                f.set(f.getName(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculateThisRobotsAproxAutonScore() {
        int score = 0;
        if (roboSet) {
            score += 4;
        }
        if (toteSet) {
            score += 6;
        }
        if (stackedToteSet) {
            score += 20;
        } //Buzz Buzz Buz
        score -= autoFouls;
        return score;
    }

    public static int calculateThisRobotsAproxTeleopScore() {
        int score = 0;
        for (RRStack r : rrStackList) {
            score += r.calculateStackScore();
        }
        score += numLitterPushed;
        score += numHumanLitterThrown * 4;
        score -= numTeleFoulsPoints;
        return score;
    }

    public static int calculateThisRobotsAproxCoopScore() {
        int score = 0;
        if (coopSet) {
            score += 20;
        }
        if (coopStack) {
            score += 40;
        }
        return score;
    }

    public static int calculateThisRobotsAproxTotalScore() {
        return calculateThisRobotsAproxAutonScore() +
                calculateThisRobotsAproxTeleopScore() +
                calculateThisRobotsAproxCoopScore();
    }
}

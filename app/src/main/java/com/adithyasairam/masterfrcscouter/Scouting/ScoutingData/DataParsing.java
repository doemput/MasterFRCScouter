package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Scouting.Scouter;

import java.util.List;

/**
 * Created by Adi on 7/14/2015.
 */

@Changeable(source = com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.DataParsing.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class DataParsing {
    public static String TAG = "DataParsing";
    public static int teamNumber, matchNumber, scoutingPosition; //Basic Info
    public static String scouterName, allianceColor; //Basic Info
    public static String autonMode; //Auton Info
    public static int acquiredBins, autoFouls; //Auton Info
    public static int numAutonCansAttemptedToBurgle, numAutonCansBurgled; //Can Burgling Auton Only!!!
    public static double canBurglingSpeed; //Can Burgling Auton Only!!!
    public static int numTeleFoulsPoints, numCansCapped; //Teleop Info
    public static boolean coopSet, coopStack, stackDown, badDriving, didCap; //Teleop Info
    public static String toteSource; //Teleop Info
    public static List<RRStack> rrStackList; //Teleop Info
    public static String comments; //Extra Info
    public static int allianceScore; //Scoring Info

    public static void setBasicInfo(int tN, int mN) {
        teamNumber = tN;
        matchNumber = mN;
        scouterName = Scouter.scouterName;
        scoutingPosition = Scouter.scoutingPosition;
        allianceColor = Scouter.isRedScouter ? "Red" : "Blue";
    }

    public static void setAutonInfo(String aM, int aB, int aF) {
        autonMode = aM;
        acquiredBins = aB;
        autoFouls = aF;
    }

    public static void setCanBurglingAutonInfo(int att, int act, double s) {
        numAutonCansAttemptedToBurgle = att;
        numAutonCansBurgled = act;
        canBurglingSpeed = s;
    }

    public static void setTeleopInfo(int nTFP, int nCC, int aS, boolean cSet, boolean cStack, boolean sD, boolean bD, boolean dC, String tS, List<RRStack> rSL) {
        numTeleFoulsPoints = nTFP;
        numCansCapped = nCC;
        allianceScore = aS;
        coopSet = cSet;
        coopStack = cStack;
        stackDown = sD;
        badDriving = bD;
        didCap = dC;
        toteSource = tS;
        rrStackList = rSL;
    }

    public static void setExtraInfo(String c) {
        comments = c;
    }

    public static int calculateThisRobotsAproxAutonScore() {
        int score = 0;
        if (autonMode.equals("Set Scored")) {
            score += 4;
        }
        if (autonMode.equals("Tote Set Scored")) {
            score += 6;
        }
        if (autonMode.equals("Stacked Tote Set Scored")) {
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

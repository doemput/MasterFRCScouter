package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adi on 7/16/2015.
 */
@Changeable(source = Match.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class Match implements Comparable<Match> {
    //Basic:
    public static int MatchNumber;
    public static String ScouterName;
    public static int TeamNumber;
    public static String ScoutingPosition;
    public static String RandomID;

    //Auton:
    public static String AutonMode;
    public static int NumberOfAcquiredBinsInAuton;
    public static int NumberOfAutonFoulPoints;

    //Can Burgling Auton Only!!!
    public static int NumAutonCansAttemptedToBurgle;
    public static int NumAutonCansBurgled;
    public static double CanBurglingSpeed;

    //Teleop:
    public static boolean WasACOOPSetScoredInTeleop;
    public static boolean WasACOOPStackScoredInTeleop;
    public static boolean AreStacksDown;
    public static boolean RobotWasPoorlyDriven;
    public static boolean RobotDidCap;
    public static int NumberOfCaps;
    public static int NumberOfStacksScoredInTeleop;
    public static String ToteSource;
    public static transient List<RRStack> Stacks;
    public static int NumberOfTeleopFoulPoints;

    //Scoring:
    public static int ThisRobotsAproxAutonScore;
    public static int ThisRobotsAproxTeleopScore;
    public static int ThisRobotsAproxCOOPScore;
    public static int ThisRobotsAproxTotalScore;
    public static int TotalAllianceScore;

    //Other
    public static String Comments;

    public int compareTo(Match other) {
        if (ThisRobotsAproxTotalScore > ThisRobotsAproxTotalScore) {
            return 1;
        } else if (ThisRobotsAproxTotalScore < ThisRobotsAproxTotalScore) {
            return -1;
        } else return 0;
    }

    public static void fieldReset() {
        MatchNumber = DataParsing.matchNumber + 1;
        ScouterName = DataParsing.scouterName;
        TeamNumber = 0;
        //TeamName = TeamNumber + " - " + TBARequest.GetTeamInformation(("frc" + TeamNumber), false).name;
        ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
        RandomID = UUID.randomUUID().toString();

        //Auton:
        AutonMode = "";
        NumberOfAcquiredBinsInAuton = 0;
        NumberOfAutonFoulPoints = 0;

        //Can Burgling Auton Only!!!
        NumAutonCansAttemptedToBurgle = 0;
        NumAutonCansBurgled = 0;
        CanBurglingSpeed = 0;

        //Teleop:
        WasACOOPSetScoredInTeleop = false;
        WasACOOPStackScoredInTeleop = false;
        AreStacksDown = false;
        RobotWasPoorlyDriven = false;
        RobotDidCap = false;
        NumberOfCaps = 0;
        NumberOfStacksScoredInTeleop = 0;
        ToteSource = "";
        Stacks = null;
        NumberOfTeleopFoulPoints = 0;

        //Scoring:
        ThisRobotsAproxAutonScore = 0;
        ThisRobotsAproxTeleopScore = 0;
        ThisRobotsAproxCOOPScore = 0;
        ThisRobotsAproxTotalScore = 0;
        TotalAllianceScore = 0;

        //Other
        Comments = "";
    }

    public Match() {
        MatchNumber = DataParsing.matchNumber;
        ScouterName = DataParsing.scouterName;
        TeamNumber = DataParsing.teamNumber;
        //TeamName = TeamNumber + " - " + TBARequest.GetTeamInformation(("frc" + TeamNumber), false).name;
        ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
        RandomID = UUID.randomUUID().toString();

        //Auton:
        AutonMode = DataParsing.autonMode;
        NumberOfAcquiredBinsInAuton = DataParsing.acquiredBins;
        NumberOfAutonFoulPoints = DataParsing.autoFouls;

        //Can Burgling Auton Only!!!
        NumAutonCansAttemptedToBurgle = DataParsing.numAutonCansAttemptedToBurgle;
        NumAutonCansBurgled = DataParsing.numAutonCansBurgled;
        CanBurglingSpeed = DataParsing.canBurglingSpeed;

        //Teleop:
        WasACOOPSetScoredInTeleop = DataParsing.coopSet;
        WasACOOPStackScoredInTeleop = DataParsing.coopStack;
        AreStacksDown = DataParsing.stackDown;
        RobotWasPoorlyDriven = DataParsing.badDriving;
        RobotDidCap = DataParsing.didCap;
        NumberOfCaps = DataParsing.numCansCapped;
        NumberOfStacksScoredInTeleop = DataParsing.rrStackList.size();
        ToteSource = DataParsing.toteSource;
        Stacks = DataParsing.rrStackList;
        NumberOfTeleopFoulPoints = DataParsing.numTeleFoulsPoints;

        //Scoring:
        ThisRobotsAproxAutonScore = DataParsing.calculateThisRobotsAproxAutonScore();
        ThisRobotsAproxTeleopScore = DataParsing.calculateThisRobotsAproxTeleopScore();
        ThisRobotsAproxCOOPScore = DataParsing.calculateThisRobotsAproxCoopScore();
        ThisRobotsAproxTotalScore = DataParsing.calculateThisRobotsAproxTotalScore();
        TotalAllianceScore = DataParsing.allianceScore;

        //Other
        Comments = DataParsing.comments;
    }
}
package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;

import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Adi on 7/16/2015.
 */
@Changeable(source = MatchData.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchData extends RealmObject implements Comparable<MatchData> {
    //Basic:
    private int MatchNumber = DataParsing.matchNumber;
    private String ScouterName = DataParsing.scouterName;
    private int TeamNumber = DataParsing.teamNumber;
    //private String TeamName = TeamNumber + " - " + TBARequest.GetTeamInformation(("frc" + TeamNumber), false).name;
    private String ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
    @PrimaryKey
    private String RandomID = UUID.randomUUID().toString();

    //Auton:
    private boolean RobotDroveToAutoZone = DataParsing.droveToAuto;
    private boolean RobotDidNothingDuringAuton = DataParsing.didNothing;
    private boolean RobotSetScoredInAuton = DataParsing.roboSet;
    private boolean RobotScoredAToteSetInAuton = DataParsing.toteSet;
    private boolean RobotScoredAStackedToteSetInAuton = DataParsing.stackedToteSet; // Buzz Buzz Buzz
    private boolean RobotScoredABinSetDuringAuton = DataParsing.binSet;
    private boolean RobotCanGrabbedDuringAuton = DataParsing.canGrabbed;
    private int NumberOfCansGrabbedDuringAuton = DataParsing.numCansGrabbed;
    private int NumberOfAcquiredBinsInAuton = DataParsing.acquiredBins;
    private int NumberOfAutonFoulPoints = DataParsing.autoFouls;

    //Teleop:
    //private int AmountOfLitterThrownByThisTeam = DataParsing.numHumanLitterThrown;
    //private int AmountOfLitterPushedByThisRobot = DataParsing.numLitterPushed;
    private boolean WasACOOPSetScoredInTeleop = DataParsing.coopSet;
    private boolean WasACOOPStackScoredInTeleop = DataParsing.coopStack;
    private int NumberOfStacksScoredInTeleop = DataParsing.rrStackList.size();
    private List<RRStack> Stacks = DataParsing.rrStackList;
    private int NumberOfTeleopFoulPoints = DataParsing.numTeleFoulsPoints;

    //Scoring:
    private int ThisRobotsAproxAutonScore = DataParsing.calculateThisRobotsAproxAutonScore();
    private int ThisRobotsAproxTeleopScore = DataParsing.calculateThisRobotsAproxTeleopScore();
    private int ThisRobotsAproxCOOPScore = DataParsing.calculateThisRobotsAproxCoopScore();
    private int ThisRobotsAproxTotalScore = DataParsing.calculateThisRobotsAproxTotalScore();

    public int compareTo(MatchData other) {
        if (ThisRobotsAproxTotalScore > other.ThisRobotsAproxTotalScore) {
            return 1;
        } else if (ThisRobotsAproxTotalScore < other.ThisRobotsAproxTotalScore) {
            return -1;
        } else return 0;
    }

    public int getMatchNumber() {
        return MatchNumber;
    }

    public String getScouterName() {
        return ScouterName;
    }

    public int getTeamNumber() {
        return TeamNumber;
    }

    public String getScoutingPosition() {
        return ScoutingPosition;
    }

    public String getRandomID() {
        return RandomID;
    }

    public boolean isRobotDroveToAutoZone() {
        return RobotDroveToAutoZone;
    }

    public boolean isRobotDidNothingDuringAuton() {
        return RobotDidNothingDuringAuton;
    }

    public boolean isRobotSetScoredInAuton() {
        return RobotSetScoredInAuton;
    }

    public boolean isRobotScoredAToteSetInAuton() {
        return RobotScoredAToteSetInAuton;
    }

    public boolean isRobotScoredAStackedToteSetInAuton() {
        return RobotScoredAStackedToteSetInAuton;
    }

    public boolean isRobotScoredABinSetDuringAuton() {
        return RobotScoredABinSetDuringAuton;
    }

    public boolean isRobotCanGrabbedDuringAuton() {
        return RobotCanGrabbedDuringAuton;
    }

    public int getNumberOfCansGrabbedDuringAuton() {
        return NumberOfCansGrabbedDuringAuton;
    }

    public int getNumberOfAcquiredBinsInAuton() {
        return NumberOfAcquiredBinsInAuton;
    }

    public int getNumberOfAutonFoulPoints() {
        return NumberOfAutonFoulPoints;
    }

    public boolean isWasACOOPSetScoredInTeleop() {
        return WasACOOPSetScoredInTeleop;
    }

    public boolean isWasACOOPStackScoredInTeleop() {
        return WasACOOPStackScoredInTeleop;
    }

    public int getNumberOfStacksScoredInTeleop() {
        return NumberOfStacksScoredInTeleop;
    }

    public List<RRStack> getStacks() {
        return Stacks;
    }

    public int getNumberOfTeleopFoulPoints() {
        return NumberOfTeleopFoulPoints;
    }

    public int getThisRobotsAproxAutonScore() {
        return ThisRobotsAproxAutonScore;
    }

    public int getThisRobotsAproxTeleopScore() {
        return ThisRobotsAproxTeleopScore;
    }

    public int getThisRobotsAproxCOOPScore() {
        return ThisRobotsAproxCOOPScore;
    }

    public int getThisRobotsAproxTotalScore() {
        return ThisRobotsAproxTotalScore;
    }
}
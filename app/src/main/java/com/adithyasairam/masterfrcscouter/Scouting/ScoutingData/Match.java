package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.ContentValues;

import com.adithyasairam.Utils.Annotations.Changeable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Adi on 7/16/2015.
 */
@Changeable(source = Match.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class Match implements Comparable<Match> {
    //Basic:
    private int MatchNumber = DataParsing.matchNumber;
    private String ScouterName = DataParsing.scouterName;
    private int TeamNumber = DataParsing.teamNumber;
    //private String TeamName = TeamNumber + " - " + TBARequest.GetTeamInformation(("frc" + TeamNumber), false).name;
    private String ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
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

    public ContentValues setContentValues(ContentValues insertValues) {
        Map<String, Object> stringObjectMap = getKeyPairValues();
        Object[] keys = stringObjectMap.keySet().toArray();
        for (int i = 0; i < stringObjectMap.size(); i++) {
            insertValues.put(((String) keys[i]), (stringObjectMap.get(keys[i]).toString()));
        }
        return insertValues;
    }

    public Map<String, Object> getKeyPairValues() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Match Number", MatchNumber);
        map.put("Scouter Name", ScouterName);
        map.put("Team Number", TeamNumber);
        map.put("Scouting Position", ScoutingPosition);
        map.put("Random ID", RandomID);

        map.put("Robot Drove to Auto Zone", RobotDroveToAutoZone);
        map.put("Robot did noting during Auton", RobotDidNothingDuringAuton);
        map.put("Robot Set Scored in Auton", RobotSetScoredInAuton);
        map.put("Robot Scored a Tote in Auton", RobotScoredAToteSetInAuton);
        map.put("Robot Scored a Tote Set in Auton", RobotScoredAToteSetInAuton);
        map.put("Robot Scored a Stacked Tote Set in Auton", RobotScoredAStackedToteSetInAuton);
        map.put("Robot Scored a Bin Set in Auton", RobotScoredABinSetDuringAuton);
        map.put("Robot Can Burgeled in Auton", RobotCanGrabbedDuringAuton);
        map.put("Robot Num Cans Grabbed in Auton", NumberOfCansGrabbedDuringAuton);
        map.put("Robot Num Acquired Bins in Auton", NumberOfAcquiredBinsInAuton);
        map.put("Robot Num Auton Foul Points", NumberOfAutonFoulPoints);

        map.put("Robot Scored a COOP Set in Auton", WasACOOPSetScoredInTeleop);
        map.put("Robot Scored a COOP Stack in Auton", WasACOOPStackScoredInTeleop);
        map.put("Robot Num Stacks Scored", NumberOfStacksScoredInTeleop);
        for (int i = 0; i < Stacks.size(); i++) {
            map.put("Stack " + i, Stacks.get(i).toString());
        }
        map.put("Robot Num Teleop Foul Points", NumberOfTeleopFoulPoints);

        map.put("Robot Aprox Auton Score", ThisRobotsAproxAutonScore);
        map.put("Robot Aprox Teleop Score", ThisRobotsAproxTeleopScore);
        map.put("Robot Aprox COOP Score", ThisRobotsAproxCOOPScore);
        map.put("Robot Aprox Total Score", ThisRobotsAproxTotalScore);
        return map;
    }

    public int compareTo(Match other) {
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

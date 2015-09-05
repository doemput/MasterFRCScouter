package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import android.content.ContentValues;

import com.adithyasairam.Utils.Annotations.Changeable;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adi on 7/16/2015.
 */
@Changeable(source = com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.MatchData.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchData implements Comparable<MatchData> {
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
        insertValues.put("Match Number", MatchNumber);
        insertValues.put("Scouter Name", ScouterName);
        insertValues.put("Team Number", TeamNumber);
        insertValues.put("Scouting Position", ScoutingPosition);
        insertValues.put("Random ID", RandomID);

        insertValues.put("Robot Drove to Auto Zone", RobotDroveToAutoZone);
        insertValues.put("Robot did noting during Auton", RobotDidNothingDuringAuton);
        insertValues.put("Robot Set Scored in Auton", RobotSetScoredInAuton);
        insertValues.put("Robot Scored a Tote in Auton", RobotScoredAToteSetInAuton);
        insertValues.put("Robot Scored a Tote Set in Auton", RobotScoredAToteSetInAuton);
        insertValues.put("Robot Scored a Stacked Tote Set in Auton", RobotScoredAStackedToteSetInAuton);
        insertValues.put("Robot Scored a Bin Set in Auton", RobotScoredABinSetDuringAuton);
        insertValues.put("Robot Can Burgeled in Auton", RobotCanGrabbedDuringAuton);
        insertValues.put("Robot Num Cans Grabbed in Auton", NumberOfCansGrabbedDuringAuton);
        insertValues.put("Robot Num Acquired Bins in Auton", NumberOfAcquiredBinsInAuton);
        insertValues.put("Robot Num Auton Foul Points", NumberOfAutonFoulPoints);

        insertValues.put("Robot Scored a COOP Set in Auton", WasACOOPSetScoredInTeleop);
        insertValues.put("Robot Scored a COOP Stack in Auton", WasACOOPStackScoredInTeleop);
        insertValues.put("Robot Num Stacks Scored", NumberOfStacksScoredInTeleop);
        for (int i = 0; i < Stacks.size(); i++) {
            insertValues.put("Stack " + i, Stacks.get(i).toString());
        }
        insertValues.put("Robot Num Teleop Foul Points", NumberOfTeleopFoulPoints);

        insertValues.put("Robot Aprox Auton Score", ThisRobotsAproxAutonScore);
        insertValues.put("Robot Aprox Teleop Score", ThisRobotsAproxTeleopScore);
        insertValues.put("Robot Aprox COOP Score", ThisRobotsAproxCOOPScore);
        insertValues.put("Robot Aprox Total Score", ThisRobotsAproxTotalScore);

        return insertValues;
    }

    public int compareTo(MatchData other) {
        if (ThisRobotsAproxTotalScore > other.ThisRobotsAproxTotalScore) {
            return 1;
        } else if (ThisRobotsAproxTotalScore < other.ThisRobotsAproxTotalScore) {
            return -1;
        } else return 0;
    }
}

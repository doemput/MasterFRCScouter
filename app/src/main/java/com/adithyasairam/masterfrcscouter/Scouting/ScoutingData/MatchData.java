package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adi on 7/16/2015.
 */
@Changeable(source = com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.MatchData.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchData {
    //Basic:
    public int MatchNumber = DataParsing.matchNumber;
    public String ScouterName = DataParsing.scouterName;
    public int TeamNumber = DataParsing.teamNumber;
    //public String TeamName = TeamNumber + " - " + TBARequest.GetTeamInformation(("frc" + TeamNumber), false).name;
    public String ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
    public String RandomID = UUID.randomUUID().toString();

    //Auton:
    public boolean RobotDroveToAutoZone = DataParsing.droveToAuto;
    public boolean RobotDidNothingDuringAuton = DataParsing.didNothing;
    public boolean RobotSetScoredInAuton = DataParsing.roboSet;
    public boolean RobotScoredAToteSetInAuton = DataParsing.toteSet;
    public boolean RobotScoredAStackedToteSetInAuton = DataParsing.stackedToteSet; // Buzz Buzz Buzz
    public boolean RobotScoredABinSetDuringAuton = DataParsing.binSet;
    public boolean RobotCanGrabbedDuringAuton = DataParsing.canGrabbed;
    public int NumberOfCansGrabbedDuringAuton = DataParsing.numCansGrabbed;
    public int NumberOfAcquiredBinsInAuton = DataParsing.acquiredBins;
    public int NumberOfAutonFoulPoints = DataParsing.autoFouls;

    //Teleop:
    public int AmountOfLitterThrownByThisTeam = DataParsing.numHumanLitterThrown;
    public int AmountOfLitterPushedByThisRobot = DataParsing.numLitterPushed;
    public boolean WasACOOPSetScoredInTeleop = DataParsing.coopSet;
    public boolean WasACOOPStackScoredInTeleop = DataParsing.coopStack;
    public int NumberOfStacksScoredInTeleop = DataParsing.rrStackList.size();
    public List<RRStack> Stacks = DataParsing.rrStackList;
    public int NumberOfTeleopFoulPoints = DataParsing.numTeleFoulsPoints;

    //Scoring:
    public int ThisRobotsAproxAutonScore = DataParsing.calculateThisRobotsAproxAutonScore();
    public int ThisRobotsAproxTeleopScore = DataParsing.calculateThisRobotsAproxTeleopScore();
    public int ThisRobotsAproxCOOPScore = DataParsing.calculateThisRobotsAproxCoopScore();
    public int ThisRobotsAproxTotalScore = DataParsing.calculateThisRobotsAproxTotalScore();

    public MatchData() {
    }
}

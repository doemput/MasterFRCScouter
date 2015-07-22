package com.adithyasairam.android.masterfrcscouter;

import com.adithyasairam.TheBlueAlliance.Teams;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;
import java.util.UUID;
//import org.simpleframework.xml.Root;

/**
 * Created by Adi on 7/16/2015.
 */
public class MatchData {
    @Element(name = "Match Number")
    public int MatchNumber = DataParsing.matchNumber;
    @Element(name = "Scouter Name")
    public String ScouterName = Scouter.scouterName;
    @Element(name = "Team Number")
    public int TeamNumber = DataParsing.teamNumber;
    @Element(name = "Team Name")
    public String TeamName = TeamNumber + " - " + Teams.GetTeamInformation("frc" + TeamNumber).name;
    @Element(name = "Scouting Position")
    public String ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
    @Element(name = "Random ID")
    public String RandomID = UUID.randomUUID().toString();
    //Auton:
    @Element
    public boolean RobotDroveToAutoZone = DataParsing.droveToAuto;
    @Element
    public boolean RobotDidNothingDuringAuton = DataParsing.didNothing;
    @Element
    public boolean RobotSetScoredInAuton = DataParsing.roboSet;
    @Element
    public boolean RobotScoredAToteSetInAuton = DataParsing.toteSet;
    @Element
    public boolean RobotScoredAStackedToteSetInAuton = DataParsing.stackedToteSet; // Buzz Buzz Buzz
    @Element
    public boolean RobotScoredABinSetDuringAuton = DataParsing.binSet;
    @Element
    public boolean RobotCanGrabbedDuringAuton = DataParsing.canGrabbed;
    @Element
    public int NumberOfCansGrabbedDuringAuton = DataParsing.numCansGrabbed;
    @Element
    public int NumberOfAcquiredBinsInAuton = DataParsing.acquiredBins;
    @Element
    public int NumberOfAutonFoulPoints = DataParsing.autoFouls;
    //Teleop:
    @Element
    public int AmountOfLitterThrownByThisTeam = DataParsing.numHumanLitterThrown;
    @Element
    public int AmountOfLitterPushedByThisRobot = DataParsing.numLitterPushed;
    @Element
    public boolean WasACOOPSetScoredInTeleop = DataParsing.coopSet;
    @Element
    public boolean WasACOOPStackScoredInTeleop = DataParsing.coopStack;
    @Element
    public int NumberOfStacksScoredInTeleop = DataParsing.rrStackList.size();
    @ElementList
    public List<RRStack> Stacks = DataParsing.rrStackList;
    @Element
    public int NumberOfTeleopFoulPoints = DataParsing.numTeleFoulsPoints;
    //Scoring:
    @Element
    public int ThisRobotsAproxAutonScore = DataParsing.calculateThisRobotsAproxAutonScore();
    @Element
    public int ThisRobotsAproxTeleopScore = DataParsing.calculateThisRobotsAproxTeleopScore();
    @Element
    public int ThisRobotsAproxCOOPScore = DataParsing.calculateThisRobotsAproxCoopScore();
    @Element
    public int ThisRobotsAproxTotalScore = DataParsing.calculateThisRobotsAproxTotalScore();

    public MatchData() {
    }
}

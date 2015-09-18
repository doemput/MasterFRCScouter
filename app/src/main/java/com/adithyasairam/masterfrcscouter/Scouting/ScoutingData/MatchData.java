package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;

import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by Adi on 7/16/2015.
 */
@Changeable(source = MatchData.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class MatchData extends RealmObject {
    //Basic:
    private int MatchNumber = DataParsing.matchNumber;
    private String ScouterName = DataParsing.scouterName;
    private int TeamNumber = DataParsing.teamNumber;
    //private String TeamName = TeamNumber + " - " + TBARequest.GetTeamInformation(("frc" + TeamNumber), false).name;
    private String ScoutingPosition = DataParsing.allianceColor + DataParsing.scoutingPosition;
    //@PrimaryKey
    private String RandomID = UUID.randomUUID().toString();

    //Auton:
    private String AutonMode = DataParsing.autonMode;
    private int NumberOfAcquiredBinsInAuton = DataParsing.acquiredBins;
    private int NumberOfAutonFoulPoints = DataParsing.autoFouls;

    //Can Burgling Auton Only!!!
    private int NumAutonCansAttemptedToBurgle = DataParsing.numAutonCansAttemptedToBurgle;
    private int NumAutonCansBurgled = DataParsing.numAutonCansBurgled;
    private double CanBurglingSpeed = DataParsing.canBurglingSpeed;

    //Teleop:
    private boolean WasACOOPSetScoredInTeleop = DataParsing.coopSet;
    private boolean WasACOOPStackScoredInTeleop = DataParsing.coopStack;
    private boolean AreStacksDown = DataParsing.stackDown;
    private boolean RobotDidCap = DataParsing.didCap;
    private int NumberOfCaps = DataParsing.numCansCapped;
    private int NumberOfStacksScoredInTeleop = DataParsing.rrStackList.size();
    private String ToteSource = DataParsing.toteSource;
    @Ignore
    private List<RRStack> Stacks = DataParsing.rrStackList;
    private int NumberOfTeleopFoulPoints = DataParsing.numTeleFoulsPoints;

    //Scoring:
    private int ThisRobotsAproxAutonScore = DataParsing.calculateThisRobotsAproxAutonScore();
    private int ThisRobotsAproxTeleopScore = DataParsing.calculateThisRobotsAproxTeleopScore();
    private int ThisRobotsAproxCOOPScore = DataParsing.calculateThisRobotsAproxCoopScore();
    private int ThisRobotsAproxTotalScore = DataParsing.calculateThisRobotsAproxTotalScore();
    private int TotalAllianceScore = DataParsing.allianceScore;

    //Other
    private String Comments = DataParsing.comments;
    private boolean RobotWasPoorlyDriven = DataParsing.badDriving;

    public MatchData() {
        //Basic:
        MatchNumber = DataParsing.matchNumber;
        ScouterName = DataParsing.scouterName;
        TeamNumber = DataParsing.teamNumber;
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
        RobotDidCap = DataParsing.didCap;
        NumberOfCaps = DataParsing.numCansCapped;
        NumberOfStacksScoredInTeleop = DataParsing.rrStackList.size();
        ToteSource = DataParsing.toteSource;
        NumberOfTeleopFoulPoints = DataParsing.numTeleFoulsPoints;

        //Scoring:
        ThisRobotsAproxAutonScore = DataParsing.calculateThisRobotsAproxAutonScore();
        ThisRobotsAproxTeleopScore = DataParsing.calculateThisRobotsAproxTeleopScore();
        ThisRobotsAproxCOOPScore = DataParsing.calculateThisRobotsAproxCoopScore();
        ThisRobotsAproxTotalScore = DataParsing.calculateThisRobotsAproxTotalScore();
        TotalAllianceScore = DataParsing.allianceScore;

        //Other
        Comments = DataParsing.comments;
        RobotWasPoorlyDriven = false;
    }

    public int getMatchNumber() {
        return MatchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        MatchNumber = matchNumber;
    }

    public String getScouterName() {
        return ScouterName;
    }

    public void setScouterName(String scouterName) {
        ScouterName = scouterName;
    }

    public int getTeamNumber() {
        return TeamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        TeamNumber = teamNumber;
    }

    public String getScoutingPosition() {
        return ScoutingPosition;
    }

    public void setScoutingPosition(String scoutingPosition) {
        ScoutingPosition = scoutingPosition;
    }

    public String getRandomID() {
        return RandomID;
    }

    public void setRandomID(String randomID) {
        RandomID = randomID;
    }

    public String getAutonMode() {
        return AutonMode;
    }

    public void setAutonMode(String autonMode) {
        AutonMode = autonMode;
    }

    public int getNumberOfAcquiredBinsInAuton() {
        return NumberOfAcquiredBinsInAuton;
    }

    public void setNumberOfAcquiredBinsInAuton(int numberOfAcquiredBinsInAuton) {
        NumberOfAcquiredBinsInAuton = numberOfAcquiredBinsInAuton;
    }

    public int getNumberOfAutonFoulPoints() {
        return NumberOfAutonFoulPoints;
    }

    public void setNumberOfAutonFoulPoints(int numberOfAutonFoulPoints) {
        NumberOfAutonFoulPoints = numberOfAutonFoulPoints;
    }

    public int getNumAutonCansAttemptedToBurgle() {
        return NumAutonCansAttemptedToBurgle;
    }

    public void setNumAutonCansAttemptedToBurgle(int numAutonCansAttemptedToBurgle) {
        NumAutonCansAttemptedToBurgle = numAutonCansAttemptedToBurgle;
    }

    public int getNumAutonCansBurgled() {
        return NumAutonCansBurgled;
    }

    public void setNumAutonCansBurgled(int numAutonCansBurgled) {
        NumAutonCansBurgled = numAutonCansBurgled;
    }

    public double getCanBurglingSpeed() {
        return CanBurglingSpeed;
    }

    public void setCanBurglingSpeed(double canBurglingSpeed) {
        CanBurglingSpeed = canBurglingSpeed;
    }

    public boolean isWasACOOPSetScoredInTeleop() {
        return WasACOOPSetScoredInTeleop;
    }

    public void setWasACOOPSetScoredInTeleop(boolean wasACOOPSetScoredInTeleop) {
        WasACOOPSetScoredInTeleop = wasACOOPSetScoredInTeleop;
    }

    public boolean isWasACOOPStackScoredInTeleop() {
        return WasACOOPStackScoredInTeleop;
    }

    public void setWasACOOPStackScoredInTeleop(boolean wasACOOPStackScoredInTeleop) {
        WasACOOPStackScoredInTeleop = wasACOOPStackScoredInTeleop;
    }

    public boolean isAreStacksDown() {
        return AreStacksDown;
    }

    public void setAreStacksDown(boolean areStacksDown) {
        AreStacksDown = areStacksDown;
    }

    public boolean isRobotWasPoorlyDriven() {
        return RobotWasPoorlyDriven;
    }

    public void setRobotWasPoorlyDriven(boolean robotWasPoorlyDriven) {
        RobotWasPoorlyDriven = robotWasPoorlyDriven;
    }

    public boolean isRobotDidCap() {
        return RobotDidCap;
    }

    public void setRobotDidCap(boolean robotDidCap) {
        RobotDidCap = robotDidCap;
    }

    public int getNumberOfCaps() {
        return NumberOfCaps;
    }

    public void setNumberOfCaps(int numberOfCaps) {
        NumberOfCaps = numberOfCaps;
    }

    public int getNumberOfStacksScoredInTeleop() {
        return NumberOfStacksScoredInTeleop;
    }

    public void setNumberOfStacksScoredInTeleop(int numberOfStacksScoredInTeleop) {
        NumberOfStacksScoredInTeleop = numberOfStacksScoredInTeleop;
    }

    public String getToteSource() {
        return ToteSource;
    }

    public void setToteSource(String toteSource) {
        ToteSource = toteSource;
    }

    public List<RRStack> getStacks() {
        return Stacks;
    }

    public void setStacks(List<RRStack> stacks) {
        Stacks = stacks;
    }

    public int getNumberOfTeleopFoulPoints() {
        return NumberOfTeleopFoulPoints;
    }

    public void setNumberOfTeleopFoulPoints(int numberOfTeleopFoulPoints) {
        NumberOfTeleopFoulPoints = numberOfTeleopFoulPoints;
    }

    public int getThisRobotsAproxAutonScore() {
        return ThisRobotsAproxAutonScore;
    }

    public void setThisRobotsAproxAutonScore(int thisRobotsAproxAutonScore) {
        ThisRobotsAproxAutonScore = thisRobotsAproxAutonScore;
    }

    public int getThisRobotsAproxTeleopScore() {
        return ThisRobotsAproxTeleopScore;
    }

    public void setThisRobotsAproxTeleopScore(int thisRobotsAproxTeleopScore) {
        ThisRobotsAproxTeleopScore = thisRobotsAproxTeleopScore;
    }

    public int getThisRobotsAproxCOOPScore() {
        return ThisRobotsAproxCOOPScore;
    }

    public void setThisRobotsAproxCOOPScore(int thisRobotsAproxCOOPScore) {
        ThisRobotsAproxCOOPScore = thisRobotsAproxCOOPScore;
    }

    public int getThisRobotsAproxTotalScore() {
        return ThisRobotsAproxTotalScore;
    }

    public void setThisRobotsAproxTotalScore(int thisRobotsAproxTotalScore) {
        ThisRobotsAproxTotalScore = thisRobotsAproxTotalScore;
    }

    public int getTotalAllianceScore() {
        return TotalAllianceScore;
    }

    public void setTotalAllianceScore(int totalAllianceScore) {
        TotalAllianceScore = totalAllianceScore;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}

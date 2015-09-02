package com.adithyasairam.masterfrcscouter.Scouting.ScoutingData;

import com.adithyasairam.Utils.Annotations.Changeable;


/**
 * Created by Adi on 7/14/2015.
 */
@Changeable(source = com.adithyasairam.masterfrcscouter.Scouting.ScoutingData.RRStack.class,
        when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH)
public class RRStack implements Comparable<RRStack>{
    public int height;
    public boolean canOnTop;
    public boolean canOnTopWithLitter;

    public RRStack(int h, boolean cOTWL, boolean cOT) {
        height = h;
        canOnTopWithLitter = cOTWL;
        canOnTop = cOT;
    }
    public int calculateStackScore() {
        int score = 0;
        score += height * 2;
        if (canOnTopWithLitter) { score += ((height * 4) + 6); }
        else if (canOnTop) { score += (height * 4); }
        return score;
    }
    public int compareTo(RRStack other) {
        if (this.calculateStackScore() > other.calculateStackScore()) { return 1; }
        else if (this.calculateStackScore() < other.calculateStackScore()) { return -1; }
        else { return 0; }
    }
}

package com.adithyasairam.android.masterfrcscouter;

/**
 * Created by Adi on 7/14/2015.
 */
public class RRStack implements Comparable<RRStack>{
    public int height;
    public boolean canOnTopWithLitter;
    public boolean canOnTop;
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
    public String toString() {
        return ("{" + "\n" +
                "  Height: " + height + "\n" +
                "  Has a Can On To With Litter: " + canOnTopWithLitter + "\n" +
                "  Has a Can on Top " + canOnTop + "\n" +
                "}");
    }
}

package com.adithyasairam.android.masterfrcscouter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;


/**
 * Created by Adi on 7/14/2015.
 */
@Element(name = "Stack")
public class RRStack implements Comparable<RRStack>{
    @Attribute(name = "Height")
    public int height;
    @Attribute(name = "Can On Top")
    public boolean canOnTop;
    @Attribute(name = "Can On Top With Litter")
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

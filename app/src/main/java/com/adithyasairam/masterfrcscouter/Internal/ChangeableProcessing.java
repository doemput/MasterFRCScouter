package com.adithyasairam.masterfrcscouter.Internal;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.Utils.Annotations.Processing.Examples.ChangeableProcessor.ChangeableProcessor;
import com.adithyasairam.Utils.Misc.GetClasses;

/**
 * Created by Adi on 8/30/2015.
 */
public class ChangeableProcessing {
    public static void main(String[] args) {
        Class[] classes = GetClasses.getClassesAnnotatedWith("com.adithyasairam", Changeable.class);
        ChangeableProcessor changeableProcessor = new ChangeableProcessor(classes);
        System.out.println("Count: " + changeableProcessor.getClasses().length + "\n");
        changeableProcessor.run();
    }
}

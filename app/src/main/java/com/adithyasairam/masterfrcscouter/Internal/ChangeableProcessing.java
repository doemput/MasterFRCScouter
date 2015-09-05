package com.adithyasairam.masterfrcscouter.Internal;

import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.Utils.Annotations.Processing.Examples.ChangeableProcessor.ChangeableProcessor;
import com.adithyasairam.Utils.Misc.GetClasses;
import com.adithyasairam.Utils.Misc.Misc;

/**
 * Created by Adi on 8/30/2015.
 */
public class ChangeableProcessing {
    public static void main(String[] args) {
        Class[] class1 = GetClasses.getClassesAnnotatedWith("com.adithyasairam.masterfrcscouter", Changeable.class);
        Class[] class2 = GetClasses.getClassesAnnotatedWith("org.hammerhead226.masterfrcscouter", Changeable.class);
        Class[] classes = Misc.mergeArrays(class1, class2);
        ChangeableProcessor changeableProcessor = new ChangeableProcessor(classes);
        System.out.println("Count: " + changeableProcessor.getClasses().length + "\n");
        changeableProcessor.run();
    }
}

package com.example;

import com.example.dijkstrasp.DijkstraSPDemonstration;

public class App {

    public static void main(String[] args) {

        // UNCOMMENT TO SEE DeMONSTRATION
        // TASK 1
        /* System.out.println("----------> QUICK SORT <----------");
    DemonstrateQuickSort dqs = new DemonstrateQuickSort();
    dqs.demonstrate();
    System.out.println(); */

        // TASK 1
        /*
    System.out.println("----------> HEAP SORT <----------");
    DemonstrateHeapSort dhs = new DemonstrateHeapSort();
    dhs.demonstrate();
    System.out.println();
    }
         */

        // TASK 1
        /* 
        System.out.println("----------> INSERT SORT <----------");
        DemonstrateInsertSort dis = new DemonstrateInsertSort();
        dis.demonstrate();
        System.out.println();
         */

        // TASK 1
        /* QuickSortToInsertSortExperiment experiment = new QuickSortToInsertSortExperiment();
        experiment.pack();
        experiment.setVisible(true);
        experiment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         */

        // TASK 1
        /*  QsToIsOptimanArraySizeExperiment experiment2 = new QsToIsOptimanArraySizeExperiment();
        experiment2.pack();
        experiment2.setVisible(true);
        experiment2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        // TASK 2 and 3
        /* GraphDemonstration gd = new GraphDemonstration();
        gd.demonstrate(); */
        
        // TASK 4
        DijkstraSPDemonstration dijkstraSPDemonstration = new DijkstraSPDemonstration();
        dijkstraSPDemonstration.demonstrate();
    }
}

package com.example.quicksort_to_insertsort_experiment;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.example.quicksort.QuickSortWithDepthTreshold;
import com.example.timer.Timer;

public class QuickSortToInsertSortExperiment extends JFrame {

    Timer timer = new Timer();

    // Constructor for the JFrame
    public QuickSortToInsertSortExperiment() {
        super("QuickSort with InsertSort Experiment");

        // Run the experiment
        XYSeriesCollection dataset = runExperiment();

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "QuickSort with InsertSort Time by Depth Threshold; Array size = 1000000",
                "Depth Threshold",
                "Time Taken (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Create a panel for the chart and add it to the JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public XYSeriesCollection runExperiment() {
        int arraySize = 1000000;
        
        // Generate the random array once
        int[] randomArray = generateRandomArray(arraySize);
        int[] sortedArray = generateSortedArray(arraySize);
        int[] reverseSortedArray = generateReverseSortedArray(arraySize);

        // Store the results in a dataset for plotting
        XYSeries randomSeries = new XYSeries("Random Array");
        XYSeries sortedSeries = new XYSeries("Sorted Array");
        XYSeries reverseSortedSeries = new XYSeries("Reverse Sorted Array");

        for (int thresholdD = 50; thresholdD >= 0; thresholdD -= 1) {
            // Clone the original array for random case
            int[] arrayCopyRandom = randomArray.clone();
            long timeTakenRandom = experiment(arrayCopyRandom, thresholdD);
            randomSeries.add(thresholdD, timeTakenRandom);

            // Clone the sorted array for sorted case
            int[] arrayCopySorted = sortedArray.clone();
            long timeTakenSorted = experiment(arrayCopySorted, thresholdD);
            sortedSeries.add(thresholdD, timeTakenSorted);

            // Clone the reverse sorted array for reverse sorted case
            int[] arrayCopyReverseSorted = reverseSortedArray.clone();
            long timeTakenReverseSorted = experiment(arrayCopyReverseSorted, thresholdD);
            reverseSortedSeries.add(thresholdD, timeTakenReverseSorted);

            System.out.print(thresholdD + ", ");
        }

        // Create a dataset and add the series to it
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(randomSeries);
        dataset.addSeries(sortedSeries);
        dataset.addSeries(reverseSortedSeries);

        return dataset;
    }

    private long experiment(int[] array, int depthThreshold) {
        QuickSortWithDepthTreshold quickSort = new QuickSortWithDepthTreshold(depthThreshold);

        long time = timer.timeit(() -> quickSort.sort(array), () -> {
        }, 1);
        System.out.println("Max depth: " + quickSort.getMaxDepth());
        System.out.println("Time taken: " + time);
        return time;
    }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10000); // Random numbers between 0-999
        }
        return array;
    }

    private int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i; // Sorted array from 0 to size-1
        }
        return array;
    }

    private int[] generateReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i - 1; // Reverse sorted array from size-1 to 0
        }
        return array;
    }
}

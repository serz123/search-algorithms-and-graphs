package com.example.quicksort_to_insertsort_experiment;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.example.quicksort.QuickSortWithSwichSize;
import com.example.timer.Timer;

public class QsToIsOptimanArraySizeExperiment extends JFrame {
    Timer timer = new Timer();

    public QsToIsOptimanArraySizeExperiment() {
        super("QuickSort with InsertSort Experiment");

        XYSeriesCollection dataset = runExperiment();

        JFreeChart chart = ChartFactory.createXYLineChart(
                "QuickSort with InsertSort Time by Subarray Size Switch; Array size = 100000",
                "Switch Size (Subarray Size)",
                "Time Taken (ns)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public XYSeriesCollection runExperiment() {
        int arraySize = 100000;
        
        // Generate the random array once
        int[] randomArray = generateRandomArray(arraySize);
        int[] sortedArray = generateSortedArray(arraySize);
        int[] reverseSortedArray = generateReverseSortedArray(arraySize);

        // Store the results in a dataset for plotting
        XYSeries randomSeries = new XYSeries("Random Array");
        XYSeries sortedSeries = new XYSeries("Sorted Array");
        XYSeries reverseSortedSeries = new XYSeries("Reverse Sorted Array");

        // Experiment by varying the subarray size threshold for switching to Insertion Sort
        for (int switchSize = 2; switchSize <= 300; switchSize++) {
            // Clone the original array for random case
            int[] arrayCopyRandom = randomArray.clone();
            long timeTakenRandom = experiment(arrayCopyRandom, switchSize);
            randomSeries.add(switchSize, timeTakenRandom);

            // Clone the sorted array for sorted case
            int[] arrayCopySorted = sortedArray.clone();
            long timeTakenSorted = experiment(arrayCopySorted, switchSize);
            sortedSeries.add(switchSize, timeTakenSorted);

            // Clone the reverse sorted array for reverse sorted case
            int[] arrayCopyReverseSorted = reverseSortedArray.clone();
            long timeTakenReverseSorted = experiment(arrayCopyReverseSorted, switchSize);
            reverseSortedSeries.add(switchSize, timeTakenReverseSorted);

            System.out.print(switchSize + ", ");
        }

        // Create a dataset and add the series to it
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(randomSeries);
        dataset.addSeries(sortedSeries);
        dataset.addSeries(reverseSortedSeries);

        return dataset;
    }

    private long experiment(int[] array, int switchSize) {
        QuickSortWithSwichSize quickSortWithSwichSize = new QuickSortWithSwichSize(switchSize);

        long time = timer.timeit(() -> quickSortWithSwichSize.sort(array), () -> {
        }, 1);
        System.out.println("Switch Size: " + switchSize);
        System.out.println("Time taken: " + time);
        return time;
    }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 10000); // Random numbers between 0-9999
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

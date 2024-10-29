package com.example.quicksort;

import com.example.insertsort.InsertSort;

public class QuickSortWithDepthTreshold {

    private int depthThreshold;
    private int maxDepth; // Field to track the maximum depth
    InsertSort insertSort;

    public QuickSortWithDepthTreshold(int depth) {
        this.depthThreshold = depth;
        this.maxDepth = 0; // Initialize maxDepth to 0
        this.insertSort = new InsertSort();
    }

    // Public method to get the maximum depth
    public int getMaxDepth() {
        return maxDepth;
    }

    public void sort(int[] a) {
        _sort(a, 0, a.length - 1, 0);
    }

    private void _sort(int[] a, int low, int high, int currentDepth) {
        if (high <= low) {
            return;
        }

        // Update maxDepth if the current depth is greater than maxDepth
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        // Check if the depth exceeds the threshold
        if (currentDepth >= depthThreshold) {
            // Sort the array directly without creating a new array
            insertSort.sort(a, low, high);
            return;
        }

        // Handle the case when subarray has only two elements
        if (high - low == 1) {
            if (a[low] > a[high]) {
                swap(a, low, high);
            }
            return;
        }

        int j = partition(a, low, high);

        _sort(a, low, j - 1, currentDepth + 1);
        _sort(a, j + 1, high, currentDepth + 1);
    }

    private int partition(int[] a, int low, int high) {
        int pivot = medianOfThree(a, low, high);
        int i = low;
        int j = high - 1;

        while (true) {
            while (a[++i] < pivot) {
                if (i == high - 1) {
                    break;
                }
            }

            while (a[--j] > pivot) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(a, i, j);
        }

        swap(a, i, high - 1);

        return i;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int medianOfThree(int[] a, int low, int high) {
        int center = (low + high) / 2;

        if (a[center] < a[low]) {
            swap(a, low, center);
        }

        if (a[high] < a[low]) {
            swap(a, low, high);
        }

        if (a[high] < a[center]) {
            swap(a, center, high);
        }

        swap(a, center, high - 1);
        return a[high - 1];
    }
}

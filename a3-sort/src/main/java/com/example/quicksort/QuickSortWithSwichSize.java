package com.example.quicksort;

import com.example.insertsort.InsertSort;

public class QuickSortWithSwichSize {

    private int switchSize;
    InsertSort insertSort;

    // Constructor that accepts both depth threshold and switch size
    public QuickSortWithSwichSize(int switchSize) {
        this.switchSize = switchSize;
        this.insertSort = new InsertSort();
    }

    // The main method to sort the array
    public void sort(int[] array) {
        _sort(array, 0, array.length - 1);
    }

    // Recursive QuickSort function
    private void _sort(int[] array, int low, int high) {
        if (high <= low) {
            return;
        }
        // If the current segment is small enough, use Insertion Sort
        if (high - low + 1 <= switchSize) {
            insertSort.sort(array, low, high);
        }

        int pivotIndex = partition(array, low, high);
        _sort(array, low, pivotIndex - 1);
        _sort(array, pivotIndex + 1, high);

    }

    private int partition(int[] a, int low, int high) {
        int pivot = medianOfThree(a, low, high);
        int i = low;
        int j = high - 1;

        while (true) {
            while (i < high - 1 && a[++i] < pivot) {
                if (i == high - 1) {
                    break;
                }
            }

            while (j > low && a[--j] > pivot) {
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

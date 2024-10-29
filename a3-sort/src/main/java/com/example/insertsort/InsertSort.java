package com.example.insertsort;

public class InsertSort {

    public void sort(int[] array, int low, int high) {
        for (int p = low + 1; p < high; p++) { // Start from low + 1 to high
            int tmp = array[p];
            int j;
            
            // Move elements of array[low..p-1], that are greater than tmp,
            // to one position ahead of their current position
            for (j = p; j > low && tmp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = tmp;
        }
    }
}

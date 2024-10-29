package com.example.heapsort;

public class HeapSort {

    private int[] heap;
    private int size;

    public HeapSort(int[] list) {

        heap = new int[list.length + 1];
        heap[0] = Integer.MIN_VALUE;

        System.arraycopy(list, 0, heap, 1, list.length);

        size = heap.length - 1;
    }

    private void sink(int currentIndex) {
        while (2 * currentIndex <= size) {
            int j = 2 * currentIndex;

            if (j < size && heap[j] < heap[j + 1]) {
                j++;
            }

            if (heap[currentIndex] >= heap[j]) {
                break;
            }

            swap(currentIndex, j);

            currentIndex = j;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int[] sort() {
        for (int k = size / 2; k >= 1; k--) {
            sink(k);
        }

        while (size > 1) {
            swap(1, size);
            size--;
            sink(1);
        }
        return heap;
    }

}

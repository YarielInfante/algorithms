package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {
    public static int[] bucketSort(int[] n, int numberOfBuckets) {

        List<List<Integer>> buckets = new ArrayList<>();

        for (var i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for (var item : n)
            buckets.get(item / numberOfBuckets).add(item);

        var i = 0;
        for (var bucket : buckets) {
            Collections.sort(bucket);
            for (var item : bucket)
                n[i++] = item;
        }

        return n;
    }

    public static int[] countingSort(int[] n, int max) {

        var countingArray = new int[max + 1];

        for (int i : n) {
            countingArray[i]++;
        }

        int k = 0;
        for (int i = 0; i < countingArray.length; i++)
            for (int j = 0; j < countingArray[i]; j++) {
                n[k++] = i;
            }

        return n;
    }

    //    1. selects a pivot
    //    2. set a boundary
    //    3. set a cursor
    //    4. increase boundary if cursor is smaller than pivot
    public static void quickSort(int[] n) {
        quickSort(n, 0, n.length - 1);
    }

    private static void quickSort(int[] n, int start, int end) {
        if (start >= end)
            return;

        var boundary = partition(n, start, end);

        quickSort(n, start, boundary - 1);
        quickSort(n, boundary + 1, end);

    }


    private static int partition(int[] n, int start, int end) {
        int pivot = n[end];
        int boundary = start - 1;

        for (int i = start; i <= end; i++) {
            if (n[i] <= pivot) {
                int temp = n[++boundary];
                n[boundary] = n[i];
                n[i] = temp;
            }
        }
        return boundary;
    }

    // break down a list into smaller list and merge them back
//    1. divide array in half
//    2. combine array sorted
    public static int[] mergeSort(int[] n) {

        if (n.length < 2)
            return n;

        int middle = n.length / 2;

        int[] left = new int[middle];
        for (int i = 0; i < middle; i++)
            left[i] = n[i];

        int[] right = new int[n.length - middle];
        for (int i = middle; i < n.length; i++)
            right[i - middle] = n[i];

        mergeSort(left);
        mergeSort(right);

        merge(left, right, n);

        return n;
    }

    private static void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length)
            if (left[i] <= right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];

        while (i < left.length)
            result[k++] = left[i++];

        while (j < right.length)
            result[k++] = right[j++];
    }

    public static int[] insertionSort(int[] n) {
        for (int i = 1; i < n.length; i++) {
            var current = n[i];
            var j = i - 1;
            while (j >= 0 && n[j] > current) {
                n[j + 1] = n[j];
                j--;
            }
            n[j + 1] = current;
        }
        return n;
    }


    public static int[] selectionSort(int[] n) {
        for (int i = 0; i < n.length; i++) {
            int min = n[i];
            for (int j = i + 1; j < n.length; j++) {
                if (min > n[j]) {
                    int temp = min;
                    min = n[j];
                    n[j] = temp;
                }
                n[i] = min;
            }
        }
        return n;
    }

    public static int[] bubbleSort(int[] n) {
        boolean isSorted;
        for (int i = 0; i < n.length; i++) {
            isSorted = true;
            for (int j = 1; j < n.length - i; j++)
                if (n[j] < n[j - 1]) {
                    int temp = n[j];
                    n[j] = n[j - 1];
                    n[j - 1] = temp;
                    isSorted = false;
                }
            if (isSorted)
                return n;
        }
        return n;
    }


}

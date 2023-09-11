package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {


    private void swap(Integer[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Aufwand: n^2/2
     *
     * @param array array of integers
     * @return sorted array small to big
     */
    public Integer[] selectionSort(final Integer[] array) {
        int length = array.length - 1;
        while (length > 0) {
            int biggestNumber = 0;
            for (int i = 0; i < length + 1; i++) {
                if (array[i] > array[biggestNumber]) {
                    biggestNumber = i;
                }
            }
            swap(array, biggestNumber, length);
            length--;
        }
        return array;
    }

    /**
     * Aufwand:
     * Bester Fall (Liste schon sortiert) = n-1 --> für große Listen ca. n
     * normaler Fall = n^2/4
     * |
     *
     * @param array array of integers
     * @return sorted array small to big
     */
    public Integer[] insertionSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int key = array[j];
            while (j > 0) {
                if (array[j - 1] > key) {
                    array[j] = array[j - 1];
                    j--;
                } else {
                    break;
                }
            }
            array[j] = key;
        }
        return array;
    }

    /**
     * Aufwand:
     * Bester Fall = n
     * optimierter Fall = n^2/2
     * schlechtester Fall = n^2
     * durchschnitts Fall = n^2
     * |
     *
     * @param array array of integers
     * @return sorted array small to big
     */
    public Integer[] bubbleSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j] > array[i]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    /**
     * Aufwand:
     * nlog2n
     * |
     * Vorteil : teile und herrsche
     * |
     * Problem: nutzt mehr speicher wegen links und rechts,
     * deshalb abwegen, ob sich quick nicht mehr lohnt
     *
     * @param array array of integers
     * @return sorted array small to big
     */
    public Integer[] mergeSort(Integer[] array) {
        if (array.length <= 1) {
            return array;
        } else {
            int middle = array.length / 2;
            Integer[] links = new Integer[middle];
            Integer[] rechts = new Integer[array.length - middle];
            System.arraycopy(array, 0, links, 0, middle - 1 + 1);
            if (array.length - middle >= 0)
                System.arraycopy(array, middle, rechts, 0, array.length - middle);
            links = mergeSort(links);
            rechts = mergeSort(rechts);
            return merg(links, rechts);

        }
    }

    /**
     * Merges the left and right side in the right order
     *
     * @param links  left side of original array
     * @param rechts right side of original array
     * @return merged array out of left and right
     */
    private Integer[] merg(Integer[] links, Integer[] rechts) {
        Integer[] merged = new Integer[links.length + rechts.length];
        int indexLinks = 0;
        int indexRechts = 0;
        int index = 0;

        while (indexRechts < rechts.length && indexLinks < links.length) {
            if (rechts[indexRechts] < links[indexLinks]) {
                merged[index] = rechts[indexRechts];
                indexRechts++;
            } else {
                merged[index] = links[indexLinks];
                indexLinks++;
            }
            index++;
        }
        while (indexRechts < rechts.length) {
            merged[index] = rechts[indexRechts];
            indexRechts++;
            index++;
        }
        while (indexLinks < links.length) {
            merged[index] = links[indexLinks];
            indexLinks++;
            index++;
        }
        return merged;
    }

    /**
     * Aufwand:
     * Bester Fall = nlog2n
     * schlechtester Fall = n^2
     * durchschnitts Fall = nlog2n
     * |
     *
     * @param under lower border of array
     * @param upper upper border of array
     * @param array array of integers
     * @return sorted array small to big
     */
    public Integer[] quickSort(final Integer[] array, final int under, final int upper) {
        if(under < upper){
            int pivot = divide(array, under,upper);
            quickSort(array,under, pivot);
            quickSort(array,pivot+1,upper);
        }
        return array;
    }

    /**
     * Berechnet die neue position des pivot elements und sortiert dabei die array elemente
     * @param array array of integers
     * @param left linke seite des pivot elements
     * @param right rechte seite des pivot elements
     * @return neue position des pivot elements
     */
    private int divide(final Integer[] array, final int left, final int right) {
        int lower, upper, median = array[(left + right) / 2];
        lower = left - 1;
        upper = right + 1;
        while (true) {
            do {
                lower++;
            } while (array[lower] < median);
            do {
                upper--;
            }
            while (array[upper] > median);
            System.out.println(Arrays.toString(array));
            if (lower < upper) {
                swap(array,upper,lower);
            } else {
                return upper;
            }
        }
    }
}


package Algorithm;

public class Search {
    /**
     * Aufwand:
     * Bester Fall = 1
     * Schlechtester Fall = log2n
     * Durschnitt = log2n
     * |
     * Geeignet für große Arrays
     * |
     * Gut wegen teile und herrsche
     * |
     * Sortierter Array nötig
     *
     * @param array array which contains the key
     * @param key   searched number
     * @param up    upper border
     * @param low   lower border
     * @return index of searched key
     */
    public Object binarySearch(final Integer[] array, final int key, final int up, final int low) {
        if (up > low) {
            return null;
        }
        int center = (up + low) / 2;
        if (array[center] == key) {
            return center;
        } else if (key < array[center]) {
            return binarySearch(array, key, center - 1, low);
        } else if (key > array[center]) {
            return binarySearch(array, key, up, center + 1);
        }
        return null;
    }

    /**
     * Aufwand:
     * Bester Fall = 1
     * Schlechtester Fall = n
     * Durschnitt Erfolg = (n+1)/2
     * Durschnitt Lose = n
     * |
     * Geeignet für sehr kleine Arrays
     * |
     * Sortierter Array nicht nötig
     *
     * @param array array which contains the key
     * @param key   searched number
     * @return index of searched key
     */
    public Object sequenceSearch(final Integer[] array, final int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return null;
    }
}

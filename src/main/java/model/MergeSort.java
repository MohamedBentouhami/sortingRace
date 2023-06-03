package model;

/**
 * class that implements the merge sorting algorithm
 */
public class MergeSort implements Sort {

    private long operation;
    private SortType sortType;

    public MergeSort() {
        this.sortType = SortType.MERGE;
        operation = 0;

    }

    @Override
    public void sortArray(int[] arr) {

        int longueur = arr.length;
        if (longueur > 0) {
            mergeSort(arr, 0, longueur - 1);
        }
    }

    @Override
    public long getOperation() {
        return operation;
    }

    private void mergeSort(int tableau[], int deb, int fin) {
        operation++;
        if (deb != fin) {
            int milieu = (fin + deb) / 2;
            operation++;
            mergeSort(tableau, deb, milieu);
            mergeSort(tableau, milieu + 1, fin);
            fusion(tableau, deb, milieu, fin);
        }
    }

    private void fusion(int tableau[], int begin, int end, int end2) {
        int deb2 = end + 1;
        operation++;
        int table1[] = new int[end - begin + 1];
        operation++;
        for (int i = begin; i <= end; i++) {
            operation++;
            table1[i - begin] = tableau[i];
        }

        int compt = begin;
        int compt2 = deb2;

        for (int i = begin; i <= end2; i++) {
            operation += 4;
            if (compt == deb2) {
                break;
            } else if (compt2 == (end2 + 1)) {
                tableau[i] = table1[compt - begin];
                compt++;
            } else if (table1[compt - begin] < tableau[compt2]) {
                tableau[i] = table1[compt - begin];
                compt++;
            } else {
                tableau[i] = tableau[compt2];
                compt2++;
            }
        }
    }

    @Override
    public String toString() {
        return "MERGE";
    }


}

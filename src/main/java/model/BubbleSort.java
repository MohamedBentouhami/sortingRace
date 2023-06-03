package model;

/**
 * class that implements the bubble sorting algorithm
 */
public class BubbleSort implements Sort {

    private long operation;
    private SortType sortType;

    public BubbleSort() {
        this.sortType = SortType.BUBBLE;
        operation = 0;
    }

    @Override
    public void sortArray(int[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    operation++;
                    swap(arr, j);
                }
            }

        }
    }

    private void swap(int[] arr, int j) {
        int tmp = arr[j + 1];
        arr[j + 1] = arr[j];
        arr[j] = tmp;
        operation += 3;

    }

    @Override
    public long getOperation() {

        return operation;

    }

    @Override
    public String toString() {
        return "BUBBLE";
    }

}

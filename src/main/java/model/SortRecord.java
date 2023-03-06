package model;

/**
 * class which allows to give the results obtained after a sorting of an array
 */
public class SortRecord {

    private Sort sort;
    private SortType type;
    private long duration;
    private long nbOperation;
    private int pas;

    /**
     * simple constructor of SortRecord class
     *
     * @param type type of sorting
     */
    public SortRecord(SortType type) {
        if (type == SortType.BUBBLE) {
            sort = new BubbleSort();
        }
        if (type == SortType.MERGE) {
            sort = new MergeSort();
        }
        this.type = type;
        nbOperation = 0;
        pas = 0;
    }

    /**
     * sort the array given
     *
     * @param array the given array of numbers
     */
    public void sort(int[] array) {
        sort.sortArray(array);
        nbOperation = sort.getOperation();
    }

    public long getNbOperation() {
        return sort.getOperation();
    }

    public Sort getSort() {
        return sort;
    }

    public long getDuration() {
        return duration;
    }

    public void setNbOperation(long nbOperation) {
        this.nbOperation = nbOperation;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setPas(int pas) {
        this.pas = pas;
    }

    public int getPas() {
        return pas;
    }

    public SortType getType() {
        return type;
    }
}

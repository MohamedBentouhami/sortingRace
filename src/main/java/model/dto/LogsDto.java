
package model.dto;

/**
 * @author mohamed
 */
public class LogsDto extends Dto<Integer> {

    private String date;
    private String typeSort;
    private int maxSize;

    public LogsDto(Integer key, String date, String typeSort, int maxSize) {
        super(key);
        this.date = date;
        this.typeSort = typeSort;
        this.maxSize = maxSize;
    }


    public String getDate() {
        return date;
    }

    public String getTypeSort() {
        return typeSort;
    }

    public int getMaxSize() {
        return maxSize;
    }

}

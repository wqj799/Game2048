package entity;

public class Cell {

    //单元格的值
    private int value;
    //单元格是否被合并
    private boolean isMerge;

    public Cell(int value, boolean isMerge) {
        this.value = value;
        this.isMerge = isMerge;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMerge() {
        return isMerge;
    }

    public void setMerge(boolean merge) {
        isMerge = merge;
    }
}

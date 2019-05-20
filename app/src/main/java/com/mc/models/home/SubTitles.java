package com.mc.models.home;

public class SubTitles {
    private boolean isSelected;
    private long timeStart;
    private long timeEnd;
    private String itemSub;
    private int color;
    private boolean isAdd;

    public SubTitles() {
    }

    public SubTitles(String itemSub, long timeStart, long timeEnd, int color) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.itemSub = itemSub;
        this.color = color;
    }

    public SubTitles(String itemSub, long timeStart, long timeEnd, boolean isSelected, int color) {
        this.isSelected = isSelected;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.itemSub = itemSub;
        this.color = color;
    }


    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public String getItemSub() {
        return itemSub;
    }

    public void setItemSub(String itemSub) {
        this.itemSub = itemSub;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "SubTitles{" +
                "isSelected=" + isSelected +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", itemSub='" + itemSub + '\'' +
                ", color=" + color +
                '}';
    }
}

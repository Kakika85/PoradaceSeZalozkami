package org.example;

public class Separator {
    private int count;

    public Separator(int count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "|";
    }
}

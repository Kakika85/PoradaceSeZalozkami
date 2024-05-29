package org.example;

public class Sep {

    private Sep prevSep;

    private Sep nextSep;

    private int index;

    private int length;

    public Sep(int index, int length) {
        this.index = index;
        this.length = length;
    }

    public Sep getPrevSep() {
        return prevSep;
    }

    public Sep getNextSep() {
        return nextSep;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public int move(int amount) {
        index += amount;
        return getIndex();
    }

    public void setNextSep(Sep newSep) {
        this.nextSep = newSep;
    }

    public void setPrevSep(Sep prevSep) {
        this.prevSep = prevSep;
    }

    @Override
    public String toString() {
        return index + "-" + length;
    }
}

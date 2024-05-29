package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sequencex {
    private static final int MIN_ITEMS = 2;//20;
    private static final int MAX_ITEMS = 5;//100;
    private List<Object> items;
    private List<Separator> separators;

    public Sequencex() {
        items = new ArrayList<>();
        separators = new ArrayList<>();
    }

    public void addItem(Object item) {
        items.add(item);
        adjustSeparatorsAfterAddition(items.lastIndexOf(item));
    }

    public void addItem(Object item, int index) {
        items.add(index, item);
        adjustSeparatorsAfterAddition(index);
    }

    public void removeItem(int index) {
        items.remove(index);
        adjustSeparatorsAfterRemoval(index);
    }

    private void adjustSeparatorsAfterAddition(int index) {
        int itemCount = 0;
        int lastSeparatorIndex = -1;
        for (int i = 0; i < items.size(); i++) {
            if (itemCount > MAX_ITEMS) {
                separators.add(new Separator(lastSeparatorIndex + 1, itemCount));
                lastSeparatorIndex = i - 1;
                itemCount = 1;
            } else {
                itemCount++;
            }

            if (i == items.size() - 1 && itemCount >= MIN_ITEMS) {
                separators.add(new Separator(lastSeparatorIndex + 1, itemCount));
            }
        }
        mergeOrSplitSeparators();
    }

    private void adjustSeparatorsAfterRemoval(int index) {
        int itemCount = 0;
        int lastSeparatorIndex = -1;
        separators.clear();
        for (int i = 0; i < items.size(); i++) {
            if (itemCount > MAX_ITEMS) {
                separators.add(new Separator(lastSeparatorIndex + 1, itemCount));
                lastSeparatorIndex = i - 1;
                itemCount = 1;
            } else {
                itemCount++;
            }

            if (i == items.size() - 1 && itemCount >= MIN_ITEMS) {
                separators.add(new Separator(lastSeparatorIndex + 1, itemCount));
            }
        }
        mergeOrSplitSeparators();
    }

    private void mergeOrSplitSeparators() {
        for (int i = 0; i < separators.size(); i++) {
            Separator current = separators.get(i);
            if (i < separators.size() - 1) {
                Separator next = separators.get(i + 1);
                int combinedLength = next.index - current.index;

                if (combinedLength < MIN_ITEMS) {
                    separators.remove(i + 1);
                    current.length = next.length;
                    i--;
                } else if (current.length > MAX_ITEMS) {
                    int excessItems = current.length - MAX_ITEMS;
                    separators.add(i + 1, new Separator(current.index + MAX_ITEMS, excessItems));
                    current.length = MAX_ITEMS;
                }
            }
        }
    }

    private static class Separator {
        private int index;
        private int length;

        public Separator(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public String toString() {
            return "S[" + index + ", " + length + "]";
        }
    }

    @Override
    public String toString() {
        return "Items: " + items
            + "\nSeparators: " + separators;
    }

    private String toString2() {
        StringBuilder sb = new StringBuilder();
        sb.append("xItems: ");

        Separator s = separators.size() > 0 ? separators.get(0) : null;

        for(int i = 0; i < items.size(); i++) {
            if(i > 0) {
                sb.append(", ");
            }
            sb.append(items.get(i));

            if(s != null && s.index + s.length == i) {
                sb.append(", |").append(s.index).append(";").append(s.length).append("|");

                int nextSepIndex = separators.indexOf(s) + 1;
                if(separators.size() > nextSepIndex) {
                    s = separators.get(nextSepIndex);
                }
            }
        }
        if(s != null && s.index + s.length == items.size() - 1) {
            sb.append(", |").append(s.index).append(";").append(s.length).append("|");
        }


        sb.append("\nxSeparators: ").append(separators);
        return sb.toString();
    }

    public static void main(String[] args) {
        Sequencex sequence = new Sequencex();

        // prvni naplneni
        System.out.println("");
        for (int i = 0; i < 26; i++) {
            sequence.addItem("" + i);
        }
        System.out.println(sequence.toString2());

        System.out.println("");
        // prvni odebrani
        sequence.removeItem(10);
        System.out.println(sequence.toString2());

    }
}

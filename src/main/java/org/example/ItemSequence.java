package org.example;

import java.util.ArrayList;
import java.util.List;

public class ItemSequence {
    private List<Object> sequence;
    private static final int MIN_ITEMS = 20;
    private static final int MAX_ITEMS = 100;

    public ItemSequence() {
        sequence = new ArrayList<>();
    }

    public void addItem(Object item, int index) {
        sequence.add(index, item);
        adjustSeparatorsInsertion(index);
    }

    private void adjustSeparatorsInsertion(int index) {
        int count = 0;
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) instanceof Separator) {
                Separator sep = (Separator) sequence.get(i);
                sep.setCount(count);
                count = 0;
            } else {
                count++;
                if (count > MAX_ITEMS) {
                    sequence.add(i, new Separator(MAX_ITEMS));
                    count = 0;
                }
            }
        }
    }

    public void removeItem(int index) {
        sequence.remove(index);
        adjustSeparatorsRemoval(index);
    }

    private void adjustSeparatorsRemoval(int index) {
        int count = 0;
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) instanceof Separator) {
                Separator sep = (Separator) sequence.get(i);
                if (count > MIN_ITEMS) {
                    sequence.remove(i);
                    i--;
                } else {
                    sep.setCount(count);
                }
                count = 0;
            } else {
                count++;
            }
        }
        if (count > 0 && count < MIN_ITEMS) {
            for (int i = sequence.size() - 1; i >= 0; i--) {
                if (sequence.get(i) instanceof Separator) {
                    sequence.remove(i);
                    break;
                }
            }
        }
    }

    public List<Object> getSequence() {
        return sequence;
    }
}

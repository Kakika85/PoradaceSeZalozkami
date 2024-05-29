package org.example;

import java.util.ArrayList;
import java.util.List;

public class NewSeq<T> {

    public static final int MIN_ITEMS = 3;//20;

    public static final int MAX_ITEMS = 5;//100;

    private final List<T> items;

    private final List<Integer> seps;

    public static void main(String[] args) {
        NewSeq<String> newSeq = new NewSeq<>();

        // prvni naplneni
        System.out.println();
        for (int i = 0; i < 26; i++) {
            newSeq.addItem(String.valueOf((char)(i + 97)));
        }
        System.out.println(newSeq);

        // prvni pridani
        System.out.println();
        newSeq.addItem("x");
        newSeq.addItem(1, "x");
        System.out.println(newSeq);

        // prvni odebrani
        System.out.println();
        newSeq.removeItem(5);
        System.out.println(newSeq);
        newSeq.removeItem(5);
        System.out.println(newSeq);

    }

    public NewSeq() {
        items = new ArrayList<>();
        seps = new ArrayList<>();
    }

    private void removeItem(int index) {
        items.remove(index);

        for(int i = 0; i < seps.size(); i++) {
            if(seps.get(i) > index) {
                seps.set(i, seps.get(i) - 1);
            }
            if(seps.get(i) - (i > 0 ? seps.get(i - 1) : 0) < MIN_ITEMS) {
                seps.remove(i);
                break;
            }
        }
    }

    private void addItem(T item) {
        addItem(items.size(), item);
    }

    private void addItem(int index, T item) {
        items.add(index, item);

        if(index == items.size() - 1) {
            int currSepIndex = seps.size() > 0 ? seps.get(seps.size() - 1) : 0;
            int prevSepIndex = seps.size() > 1 ? seps.get(seps.size() - 2) : 0;
            if (currSepIndex - prevSepIndex >= MIN_ITEMS || seps.isEmpty()) {
                seps.add(items.size() - 1);
            } else {
                seps.set(seps.size() - 1, seps.get(seps.size() - 1) + 1);
            }
        } else {
            for (int i = 0; i < seps.size(); i++) {
                if (seps.get(i) > index) {
                    seps.set(i, seps.get(i) + 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        int lastSepIndex = -1;

        StringBuilder sb = new StringBuilder();
        sb.append("xItems: ");
        for(int i = 0; i < items.size(); i++) {
            sb.append(i == 0 ? "[" : !seps.contains(i - 1) ? ", " : "");
            sb.append(items.get(i));
            if(seps.contains(i)) {
                sb.append("](").append(lastSepIndex + 1).append("-").append(i).append(i == items.size() - 1 ? ")" : ") [");
                lastSepIndex = i;
            }
        }

        sb.append("\nxSeps: ").append(seps);
        return sb.toString();
    }
}

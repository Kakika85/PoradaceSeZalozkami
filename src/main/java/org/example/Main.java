package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ItemSequence itemSequence = new ItemSequence();
        for (int i = 0; i <150; i++) {
            itemSequence.addItem(new Item("Item" + i), itemSequence.getSequence().size());
        }

        System.out.println("Sequence after adding items: " + itemSequence.getSequence());

        for (int i = 0; i <10; i++) {
            itemSequence.removeItem(96);
        }
        System.out.println("Sequence after removing items: " + itemSequence.getSequence());

    }
}
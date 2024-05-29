import org.example.ItemSequence;
import org.junit.jupiter.api.Test;

public class ItemSequenceTest {
    ItemSequence itemSequence = new ItemSequence();

    @Test
    public void testAddItem() {
        // Přidání 35 položek
        for (int i = 0; i < 35; i++) {
            itemSequence.addItem("Item " + i, i);
        }
        System.out.println("Sequence after adding 35 items: " + itemSequence.getSequence());

        // Přidání dalších 65 položek
        for (int i = 35; i < 100; i++) {
            itemSequence.addItem("Item " + i, i);
        }
        System.out.println("Sequence after adding additional 65 items: " + itemSequence.getSequence());

        // Přidání dalších 15 položek
        for (int i = 100; i < 115; i++) {
            itemSequence.addItem("Item " + i, i);
        }
        System.out.println("Sequence after adding additional 15 items: " + itemSequence.getSequence());
    }

    @Test
    public void testRemoveItem() {
        // Vložím položek jako příprava testu
        for (int i = 0; i < 100; i++) {
            itemSequence.addItem("Item " + i, i);
        }
        System.out.println("Sequence after adding additional 65 items: " + itemSequence.getSequence());

        // Odstranění 10 položek
        for (int i = 0; i < 10; i++) {
            itemSequence.removeItem(0);
        }
        System.out.println("Sequence after removing 10 items: " + itemSequence.getSequence());
    }
}




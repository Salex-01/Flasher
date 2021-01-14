import java.util.LinkedList;

public class FlashList extends LinkedList<Flash> {
    public void removeFlash(Flash f) {
        if (remove(f) && f.metronome) {
            FlashList fl = new FlashList();
            for (Flash f2 : this) {
                if (f2.parent == f) {
                    fl.add(f2);
                }
            }
            for (Flash f2 : fl) {
                remove(f2);
            }
        }
    }
}

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Arrays;

public abstract class FlashSelector extends JFrame implements ListSelectionListener {
    JList<String> list;

    public FlashSelector() {
        String[] flashes = new String[Main.e.flashes.size()];
        int i = 0;
        for (Flash f : Main.e.flashes) {
            if (f.parent == null) {
                flashes[i] = (f.metronome ? "M " : "P ") + FlashManager.timeToString(f.time);
                i++;
            }
        }
        if (i != flashes.length) {
            flashes = Arrays.copyOfRange(flashes, 0, i);
        }
        list = new JList<>(flashes);
        list.addListSelectionListener(this);
        add(list, BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
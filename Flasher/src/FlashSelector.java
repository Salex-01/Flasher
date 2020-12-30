import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public abstract class FlashSelector extends JFrame implements ListSelectionListener {
    JList<String> list;

    public FlashSelector() {
        String[] flashes = new String[Main.e.flashes.size()];
        int i = 0;
        for (Integer f : Main.e.flashes) {
            flashes[i] = FlashManager.timeToString(f);
        }
        list = new JList<>(flashes);
        list.addListSelectionListener(this);
        add(list, BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
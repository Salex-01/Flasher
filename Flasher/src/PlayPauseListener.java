import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPauseListener implements ActionListener {
    Interface i;
    JButton b;

    public PlayPauseListener(Interface i1, JButton b1) {
        i = i1;
        b = b1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.e != null) {
            if (!Main.e.playing) {
                b.setText("⏸");
            } else {
                b.setText("▶");
            }
            Main.e.togglePlay();
        }
    }
}
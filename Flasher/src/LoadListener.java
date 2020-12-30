import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadListener implements ActionListener {
    Interface i;

    public LoadListener(Interface i1) {
        i = i1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PopupManager.askSaveChanges()) return;
        JFileChooser fc = new JFileChooser(".");
        fc.showOpenDialog(i);
        File f = fc.getSelectedFile();
        if (f != null) {
            try {
                if (Main.e != null) {
                    Main.e.end();
                }
                Main.e = new FlashManager(f, i);
                Main.e.start();
            } catch (IOException ignored) {
            }
        }
    }

}
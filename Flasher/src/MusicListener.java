import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MusicListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        if (Main.e.mm.path != null) {
            fc.setSelectedFile(new File(Main.e.mm.path));
        }
        fc.showOpenDialog(null);
        File f1 = fc.getSelectedFile();
        if (f1 != null) {
            Main.e.mm.loadMusic(f1.getAbsolutePath());
        }
    }
}

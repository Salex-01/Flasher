import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewListener implements ActionListener {
    Interface i;

    public NewListener(Interface i1) {
        i = i1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PopupManager.askSaveChanges()) return;
        if (Main.e != null) {
            Main.e.end();
        }
        Main.e = new FlashManager(i);
        Main.e.start();
    }
}
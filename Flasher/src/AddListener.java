import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {
    Interface i;

    public AddListener(Interface i1) {
        i = i1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog("Heure du flash", "[[h:]m:]s[.xxx]");
        if (str != null && Main.e != null) {
            Main.e.hasChanged = true;
            Main.e.parseAndAdd(str);
            Main.e.draw(i);
        }
    }
}
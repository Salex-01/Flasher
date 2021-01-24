import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DurationListener implements ActionListener {
    Interface i;

    public DurationListener(Interface i1) {
        i = i1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer tmp = askDuration();
        if (tmp != null && Main.e != null) {
            Main.e.setDuration(tmp,i);
        }
    }

    private Integer askDuration() {
        String str = JOptionPane.showInputDialog("Durée", "[[h:]m:]s[.xxx]");
        if (str != null) {
            return FlashManager.parseTime(str);
        }
        return null;
    }

}
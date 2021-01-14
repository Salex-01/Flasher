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
            String[] ObjButtons = {"Ponctuel", "Métronome"};
            Main.e.hasChanged = true;
            if (JOptionPane.showOptionDialog(null, "Type de flash", "Réglage", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ObjButtons, ObjButtons[0]) == 0) {
                Main.e.parseAndAddFlash(str);
            } else {
                String interval = JOptionPane.showInputDialog("Intervalle", "[[h:]m:]s[.xxx]");
                String rep = JOptionPane.showInputDialog("Répétitions", "-1 pour infini");
                Main.e.parseAndAddMetronome(str, interval,rep);
            }
            Main.e.draw(i);
        }
    }
}
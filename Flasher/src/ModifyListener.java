import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyListener implements ActionListener {
    Interface i;

    public ModifyListener(Interface i1) {
        i = i1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        class FlashSelectorM extends FlashSelector {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                Flash f = Main.e.flashes.get(list.getSelectedIndex());
                if (f.metronome) {
                    if (PopupManager.yesNo("Modification", "Modifier " + list.getSelectedValue() + " ?")) {
                        String str = JOptionPane.showInputDialog("Heure du flash", FlashManager.timeToString(f.time));
                        String interval = JOptionPane.showInputDialog("Intervalle", FlashManager.timeToString(f.inter));
                        String rep = JOptionPane.showInputDialog("Répétitions", Integer.toString(f.n));
                        Main.e.hasChanged = true;
                        Main.e.flashes.removeFlash(f);
                        Main.e.parseAndAddMetronome(str, interval, rep);
                        Main.e.draw(i);
                    }
                } else {
                    if (PopupManager.yesNo("Modification", "Déplacer " + list.getSelectedValue() + " ?")) {
                        String str = JOptionPane.showInputDialog("Heure du flash", FlashManager.timeToString(f.time));
                        if (str != null) {
                            Main.e.hasChanged = true;
                            Main.e.flashes.removeFlash(f);
                            Main.e.parseAndAddFlash(str);
                            Main.e.draw(i);
                        }
                    }
                }
                this.dispose();
            }
        }
        new FlashSelectorM();
    }
}
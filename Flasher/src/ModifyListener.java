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
                if (PopupManager.yesNo("Modification", "Déplacer " + list.getSelectedValue() + " ?")) {
                    String str = JOptionPane.showInputDialog("Heure du flash", "[[h:]m:]s[.xxx]");
                    if (str != null) {
                        Main.e.hasChanged = true;
                        Main.e.flashes.remove(list.getSelectedIndex());
                        Main.e.parseAndAdd(str);
                        Main.e.draw(i);
                    }
                    this.dispose();
                }
            }
        }
        new FlashSelectorM();
    }

}
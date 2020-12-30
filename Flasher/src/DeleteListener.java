import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListener implements ActionListener {
    Interface i;

    public DeleteListener(Interface i1) {
        i = i1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        class FlashSelectorD extends FlashSelector {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                if (PopupManager.yesNo("Suppression", "Supprimer " + list.getSelectedValue() + " ?")) {
                    Main.e.hasChanged = true;
                    Main.e.flashes.remove(list.getSelectedIndex());
                    Main.e.buttonsChanged = true;
                    Main.e.draw(i);
                }
                this.dispose();
            }
        }
        new FlashSelectorD();
    }
}
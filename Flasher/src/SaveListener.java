import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Main.e != null) {
                Main.e.save();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            PopupManager.info("Erreur","L'enregistrement a échoué");
        }
    }
}
import javax.swing.*;
import java.io.IOException;

public class PopupManager {

    static public boolean yesNo(String title, String message) {
        String[] ObjButtons = {"Oui", "Non"};
        return (JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ObjButtons, ObjButtons[0])) == 0;
    }

    static public int ask(String title, String message, String[] options) {
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
    }

    static boolean askSaveChanges() {
        if (Main.e != null && Main.e.hasChanged) {
            int res = ask("Sauvegarder ?", "Sauvegarder les changements ?", new String[]{"Oui", "Non", "Annuler"});
            if (res == 0) {
                try {
                    Main.e.save();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    info("Erreur","L'enregistrement a échoué");
                }
            } else return res == 2;
        }
        return false;
    }

    public static void info(String title, String message) {
        String[] ObjButtons = {"OK"};
        JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ObjButtons, ObjButtons[0]);
    }
}
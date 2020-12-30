import javax.swing.*;
import java.awt.*;

public class MJButton extends JButton {
    public MJButton(String label, Font f) {
        super(label);
        setBackground(new Color(230, 230, 230));
        setFont(f);
    }
}
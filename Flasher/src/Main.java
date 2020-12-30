import java.awt.*;

public class Main {
    static FlashManager e;

    public static void main(String[] args) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Frame f = new Frame();
        f.setBounds(0, 0, (int) d.getWidth(), (int) d.getHeight()-39);
        f.setVisible(true);
        f.addWindowListener(new CloserListener(f));
        f.add(new Interface(f));
    }
}
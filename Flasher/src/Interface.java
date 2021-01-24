import javax.swing.*;
import java.awt.*;

public class Interface extends Container {
    int topBarSize = 31;

    JButton bNew;
    JButton bLoad;
    JButton bSave;
    JButton bMusic;
    JButton bM5;
    JButton bM1;
    JButton bP5;
    JButton bPlayPause;
    JButton bStop;
    JButton bAdd;
    JButton bModify;
    JButton bDelete;
    JButton bDuration;
    Container panel;
    JPanel flash;

    public Interface(Frame f) {
        String font = "TimesRoman";
        double sizeFactor = ((double) (f.getHeight())) / 400;
        Font f1 = new Font(font, Font.BOLD, (int) (15 * sizeFactor));
        setBounds(0, topBarSize, f.getWidth(), f.getHeight() - topBarSize);
        bNew = new MJButton("Nouveau", f1);
        bNew.setBounds(0, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
        bNew.addActionListener(new NewListener(this));
        this.add(bNew);
        bLoad = new MJButton("Charger", f1);
        bLoad.setBounds(f.getWidth() / 8, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
        bLoad.addActionListener(new LoadListener(this));
        this.add(bLoad);
        bSave = new MJButton("Sauvegarder", f1);
        bSave.setBounds(f.getWidth() / 4, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
        bSave.addActionListener(new SaveListener());
        this.add(bSave);
        bMusic = new MJButton("Musique", f1);
        bMusic.setBounds((3*f.getWidth()) / 8, 0, f.getWidth() / 8, (int) (50 * sizeFactor));
        bMusic.addActionListener(new MusicListener());
        this.add(bMusic);
        bM5 = new MJButton("⏮ 5", f1);
        bM5.setBounds(0, (int) (50 * sizeFactor), f.getWidth() / 6, (int) (50 * sizeFactor));
        bM5.addActionListener(e -> {
            Main.e.time -= 5000;
            Main.e.nfi = 0;
            Main.e.mm.changeTime(-5000);
        });
        this.add(bM5);
        bM1 = new MJButton("⏮ 1", f1);
        bM1.setBounds(f.getWidth() / 6, (int) (50 * sizeFactor), f.getWidth() / 6, (int) (50 * sizeFactor));
        bM1.addActionListener(e -> {
            Main.e.time -= 1000;
            Main.e.nfi = 0;
            Main.e.mm.changeTime(-1000);
        });
        this.add(bM1);
        bP5 = new MJButton("5 ⏭", f1);
        bP5.setBounds(f.getWidth() / 3, (int) (50 * sizeFactor), f.getWidth() / 6, (int) (50 * sizeFactor));
        bP5.addActionListener(e -> {
            Main.e.time += 5000;
            Main.e.nfi = 0;
            Main.e.mm.changeTime(5000);
        });
        this.add(bP5);
        bStop = new MJButton("⏹", f1);
        bStop.setBounds(0, (int) (100 * sizeFactor), f.getWidth() / 4, (int) (100 * sizeFactor));
        bStop.addActionListener(e -> {
            if (Main.e != null) {
                Main.e.stopp();
                bPlayPause.setText("▶");
            }
        });
        bPlayPause = new MJButton("▶", f1);
        bPlayPause.setBounds(f.getWidth() / 4, (int) (100 * sizeFactor), f.getWidth() / 4, (int) (100 * sizeFactor));
        bPlayPause.addActionListener(new PlayPauseListener(this, bPlayPause));
        this.add(bPlayPause);
        this.add(bStop);
        int heightLeft = (int) (f.getHeight() - 200 * sizeFactor) - 39;
        bAdd = new MJButton("Ajouter", f1);
        bAdd.setBounds(0, (int) (200 * sizeFactor), (int) (100 * sizeFactor), heightLeft / 4);
        bAdd.addActionListener(new AddListener(this));
        this.add(bAdd);
        bModify = new MJButton("Modifier", f1);
        bModify.setBounds(0, (int) (200 * sizeFactor + heightLeft * 0.25), (int) (100 * sizeFactor), heightLeft / 4);
        bModify.addActionListener(new ModifyListener(this));
        this.add(bModify);
        bDelete = new MJButton("Supprimer", f1);
        bDelete.setBounds(0, (int) (200 * sizeFactor + heightLeft * 0.5), (int) (100 * sizeFactor), heightLeft / 4);
        bDelete.addActionListener(new DeleteListener(this));
        this.add(bDelete);
        bDuration = new MJButton("Durée", f1);
        bDuration.setBounds(0, (int) (200 * sizeFactor + heightLeft * 0.75), (int) (100 * sizeFactor), heightLeft / 4);
        bDuration.addActionListener(new DurationListener(this));
        this.add(bDuration);
        panel = new JPanel();
        panel.setBounds((int) (100 * sizeFactor), (int) (200 * sizeFactor), (int) (f.getWidth() - 100 * sizeFactor), heightLeft);
        this.add(panel);
        flash = new JPanel();
        flash.setBounds(f.getWidth() / 2, 0, f.getWidth() / 2, (int) (200 * sizeFactor));
        this.add(flash);
        f.addMouseMotionListener(new SizeUpdater(f, this, font));
    }
}
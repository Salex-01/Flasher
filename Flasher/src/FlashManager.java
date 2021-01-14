import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Comparator;

public class FlashManager extends Thread {
    private final Interface i;
    int duration;
    boolean hasChanged;
    private File f;
    final FlashList flashes = new FlashList();
    int time = 0;
    private boolean flashing = false;
    boolean playing = false;
    private boolean stopped = false;
    int nfi = 0;
    boolean buttonsChanged = false;
    long oldTime;

    public FlashManager(Interface i1) {
        hasChanged = true;
        f = null;
        duration = 60000;
        i = i1;
    }

    public FlashManager(File f1, Interface i1) throws IOException {
        f = f1;
        DataInputStream is = new DataInputStream(new FileInputStream(f));
        duration = is.readInt();
        while (is.available() > 0) {
            int t = is.readInt();
            if (is.readBoolean()) {
                int inter = is.readInt();
                int n = is.readInt();
                Flash f = new Flash(t, true, null, inter, n);
                flashes.add(f);
                if (n > 0) {
                    int ti = t + inter;
                    for (int i = 1; i < n; i++) {
                        flashes.add(new Flash(ti, true, f, inter, n));
                        ti = ti + inter;
                    }
                } else {
                    for (int ti = t + inter; ti < duration; ti += inter) {
                        flashes.add(new Flash(ti, true, f, inter, n));
                    }
                }
            } else {
                flashes.add(new Flash(t, false, null, is.readInt(), is.readInt()));
            }
        }
        i = i1;
    }

    static String timeToString(int t) {
        int h = t / 3600000;
        int m = (t - h * 3600000) / 60000;
        double s = (t - h * 3600000 - m * 60000) / 1000.0;
        return h + ":" + m + ":" + s;
    }

    static int parseTime(String str) {
        String[] split = str.split(":");
        int h = 0;
        int m = 0;
        int s = 0;
        int i = 0;
        switch (split.length) {
            case 3:
                h = Integer.parseInt(split[i]);
                i++;
            case 2:
                m = Integer.parseInt(split[i]);
                i++;
            case 1:
                s = (int) (Double.parseDouble(split[i]) * 1000);
                break;
        }
        return h * 3600000 + m * 60000 + s;
    }

    void parseAndAddFlash(String str) {
        int t = parseTime(str);
        flashes.add(new Flash(t, false, null, -1, 0));
        if (t > duration) {
            duration = t;
        }
        flashes.sort(Comparator.comparingInt(o -> o.time));
        buttonsChanged = true;
    }

    public void parseAndAddMetronome(String str, String interval, String reps) {
        parseAndAddMetronome(str, parseTime(interval), Integer.parseInt(reps));
    }

    public void parseAndAddMetronome(String str, int inter, int rep) {
        int init = parseTime(str);
        Flash f = new Flash(init, true, null, inter, rep);
        flashes.add(f);
        if (rep > 0) {
            int t = init + inter;
            for (int i = 1; i < rep; i++) {
                flashes.add(new Flash(t, true, f, inter, rep));
                t += inter;
            }
        } else {
            for (int t = init + inter; t < duration; t += inter) {
                flashes.add(new Flash(t, true, f, inter, rep));
            }
        }
        flashes.sort(Comparator.comparingInt(o -> o.time));
        buttonsChanged = true;
    }

    public void draw(Interface i2) {
        class FlashButton extends Button {
            FlashButton(Flash f) {
                super("");
                setBackground(Color.BLACK);
                setBounds((int) (((f.time * 1.0 * (i2.panel.getWidth() - 30)) / duration) + 15), 15, 20, i2.panel.getHeight() - 30);
                this.
                        addActionListener(e -> {
                            class ActionSelector {
                                public ActionSelector() {
                                    String[] actions = {"Modifier", "Supprimer"};
                                    int res = PopupManager.ask("Action", "", actions);
                                    switch (res) {
                                        case 0:
                                            if (PopupManager.yesNo("Modification", "Déplacer " + timeToString(f.time) + " ?")) {
                                                String str = JOptionPane.showInputDialog("Heure du flash", "[[h:]m:]s[.xxx]");
                                                if (str != null) {
                                                    Main.e.flashes.removeFlash(f);
                                                    if (f.metronome) {
                                                        Main.e.parseAndAddMetronome(str, f.inter, f.n);
                                                    } else {
                                                        Main.e.parseAndAddFlash(str);
                                                    }
                                                }
                                            }
                                            break;
                                        case 1:
                                            if (PopupManager.yesNo("Suppression", "Supprimer " + timeToString(f.time) + " ?")) {
                                                Main.e.flashes.removeFlash(f);
                                                buttonsChanged = true;
                                            }
                                            break;
                                    }
                                    draw(i2);
                                }
                            }
                            new ActionSelector();
                        });
            }
        }
        Graphics g = i2.panel.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, i.panel.getWidth(), i.panel.getHeight());
        if (buttonsChanged) {
            i2.panel.removeAll();
            for (Flash f : flashes) {
                i2.panel.add(new FlashButton(f));
            }
        }
        Graphics g1 = i2.panel.getGraphics();
        g1.setColor(Color.BLACK);
        g1.drawLine((time * i2.panel.getWidth()) / duration, 0, (time * i2.panel.getWidth()) / duration, i2.panel.getHeight());
        if (!buttonsChanged) {
            for (Component c : i2.panel.getComponents()) {
                c.paint(g1);
            }
        } else {
            buttonsChanged = false;
        }
        Graphics g2 = i2.flash.getGraphics();
        g2.setColor(flashing ? Color.WHITE : Color.BLACK);
        g2.fillRect(0, 0, i2.flash.getWidth(), i2.flash.getHeight());
    }

    public void save() throws IOException {
        System.out.println("save");
        JFileChooser fc = new JFileChooser();
        if (f != null) {
            fc.setSelectedFile(f);
        }
        fc.showOpenDialog(null);
        File f1 = fc.getSelectedFile();
        if (f1 != null) {
            if (!f1.getName().endsWith(".f")) {
                f1 = new File(f1.getName() + ".f");
            }
            f = f1;
        } else {
            return;
        }
        DataOutputStream os = new DataOutputStream(new FileOutputStream(f));
        os.writeInt(duration);
        for (Flash f : flashes) {
            os.writeInt(f.time);
            os.writeBoolean(f.metronome);
            os.writeInt(f.inter);
            os.writeInt(f.n);
        }
        hasChanged = false;
    }

    public void stopp() {
        playing = false;
        time = 0;
        nfi = 0;
        Graphics g = i.panel.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, i.panel.getWidth(), i.panel.getHeight());
    }

    public void togglePlay() {
        if (!playing) {
            oldTime = System.currentTimeMillis();
        }
        playing = !playing;
    }

    public void end() {
        stopped = true;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        draw(i);
        while (!stopped) {
            if (playing && time <= duration) {
                draw(i);
                if (nfi < flashes.size()) {
                    int nf = flashes.get(nfi).time;
                    if (time >= nf) {
                        flashing = true;
                        if (time > nf + 250) {
                            flashing = false;
                            nfi++;
                            while (nfi < flashes.size()) {
                                if (flashes.get(nfi).time >= time) {
                                    break;
                                }
                                nfi++;
                            }
                        }
                    }
                }
                long tmp = System.currentTimeMillis();
                time += (tmp - oldTime);
                oldTime = tmp;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
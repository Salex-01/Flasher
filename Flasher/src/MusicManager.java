import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicManager {
    String path;
    MediaPlayer mp;
    boolean playing = false;
    Interface i;

    public MusicManager(Interface i1) {
        i = i1;
    }

    public void loadMusic(String path1) {
        new JFXPanel();
        path = path1;
        Media hit = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(hit);
        if (PopupManager.yesNo("Durée", "Changer la durée de la piste ?")) {
            Main.e.setDuration((int) mp.getTotalDuration().toMillis(), i);
        }
        Main.e.stopp();
    }

    public void togglePLay() {
        if (mp != null) {
            if (playing) {
                mp.pause();
            } else {
                mp.play();
            }
            playing = !playing;
        }
    }

    public void reset() {
        if (mp != null) {
            mp.pause();
            mp.seek(new Duration(0));
            playing = false;
            if (!i.bPlayPause.getText().contentEquals("▶")) {
                i.bPlayPause.setText("▶");
            }
        }
    }

    public void changeTime(int t) {
        if (mp != null) {
            mp.seek(new Duration(mp.getCurrentTime().toMillis() + t));
        }
    }
}

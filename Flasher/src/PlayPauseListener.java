import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayPauseListener implements ActionListener {
	Button b;
	boolean c = false;

	PlayerFrame pf;

	public PlayPauseListener(Button flash, PlayerFrame pf1) {
		b = flash;
		pf = pf1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (c) {
			b.setLabel("Play");
			b.setBackground(Color.LIGHT_GRAY);
			b.setForeground(Color.BLACK);
			try {
				pf.go.acquire();
			} catch (InterruptedException e1) {
			}
		} else {
			b.setLabel("Pause");
			b.setBackground(Color.DARK_GRAY);
			b.setForeground(Color.WHITE);
			pf.go.release();
		}
		c = !c;
	}

}

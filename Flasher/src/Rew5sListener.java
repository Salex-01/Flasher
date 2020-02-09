import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rew5sListener implements ActionListener {
	PlayerFrame pf;

	public Rew5sListener(PlayerFrame pf1) {
		pf = pf1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pf.current = Math.max(pf.current - 5000, 0);
	}

}

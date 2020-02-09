import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add5sListener implements ActionListener {
	PlayerFrame pf;

	public Add5sListener(PlayerFrame pf1) {
		pf = pf1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pf.current = Math.min(pf.current + 5000, pf.duration);
	}

}

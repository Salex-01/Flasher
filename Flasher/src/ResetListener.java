import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
	PlayerFrame pf;

	public ResetListener(PlayerFrame pf1) {
		pf = pf1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pf.current = 0;
	}

}

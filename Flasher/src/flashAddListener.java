import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class flashAddListener implements ActionListener {
	PlayerFrame pf;
	JFrame j;
	Font f;

	public flashAddListener(PlayerFrame pf1, JFrame j1, Font f2) {
		pf = pf1;
		j = j1;
		f = f2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame jw = new JFrame();
		Container c = new Container();

		jw.setBounds(j.getX() * 4, j.getY() * 4, j.getWidth() / 4, j.getHeight() / 3);
		jw.add(c);
		jw.setVisible(true);

		Button b = new Button("Current");
		b.setFont(f);
		b.setBounds(0, 0, c.getWidth(), c.getHeight() / 3);
		b.setBackground(Color.LIGHT_GRAY);
		b.setForeground(Color.BLACK);
		
		c.add(b);

	}

}

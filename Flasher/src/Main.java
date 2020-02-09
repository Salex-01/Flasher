import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame jf = new JFrame();
		Font f1;
		Font f2;
		jf.setBounds((int) (screenSize.getWidth() * 0.1), (int) (screenSize.getHeight() * 0.1),
				(int) (screenSize.getWidth() * 0.8), (int) (screenSize.getHeight() * 0.8));

		Container c = new Container();
		jf.add(c);
		jf.setVisible(true);

		f1 = new Font("TimesRoman", 1, (int) (c.getHeight() / 8.25));
		f2 = new Font("TimesRoman", 1, (int) (c.getHeight() / 16.5));

		PlayerFrame pf = new PlayerFrame();
		pf.setBounds(0, c.getHeight() / 2, c.getWidth(), c.getHeight() / 2);
		c.add(pf);

		Button play_pause_button = new Button();
		play_pause_button.setBounds(0, 0, c.getWidth() / 3, c.getHeight() / 4);
		play_pause_button.setBackground(Color.LIGHT_GRAY);
		play_pause_button.setFont(f1);
		play_pause_button.setForeground(Color.BLACK);
		play_pause_button.setLabel("Play");
		play_pause_button.addActionListener(new PlayPauseListener(play_pause_button, pf));
		c.add(play_pause_button);

		Button reset = new Button();
		reset.setBounds(0, c.getHeight() / 4, c.getWidth() / 9, c.getHeight() / 8);
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setFont(f2);
		reset.setForeground(Color.BLACK);
		reset.setLabel("Reset");
		reset.addActionListener(new ResetListener(pf));
		c.add(reset);

		Button rew5s = new Button();
		rew5s.setBounds(c.getWidth() / 9, c.getHeight() / 4, c.getWidth() / 9, c.getHeight() / 8);
		rew5s.setBackground(Color.LIGHT_GRAY);
		rew5s.setFont(f2);
		rew5s.setForeground(Color.BLACK);
		rew5s.setLabel("<< 5");
		rew5s.addActionListener(new Rew5sListener(pf));
		c.add(rew5s);

		Button add5s = new Button();
		add5s.setBounds((c.getWidth() * 2) / 9, c.getHeight() / 4, c.getWidth() / 9, c.getHeight() / 8);
		add5s.setBackground(Color.LIGHT_GRAY);
		add5s.setFont(f2);
		add5s.setForeground(Color.BLACK);
		add5s.setLabel("5 >>");
		add5s.addActionListener(new Add5sListener(pf));
		c.add(add5s);

		
	}

}

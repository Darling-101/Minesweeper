package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

public class NumberLabel extends JLabel {

	private NotificationPanel p;

	private String number;

	public NumberLabel(NotificationPanel p, String number) {
		this.p = p;
		this.number = number;
		setPreferredSize(new Dimension(78, 46));
	}

	@Override
	public void paint(Graphics g) {
		if (number.equals("infinity")) {
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("infinity"), 0, 0, 26, 46, null);
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("infinity"), 26, 0, 26, 46, null);
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("infinity"), 52, 0, 26, 46, null);
		} else {
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(0))),
					0, 0, 26, 46, null);
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(1))),
					26, 0, 26, 46, null);
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get(String.valueOf(number.charAt(2))),
					52, 0, 26, 46, null);
		}
	}

	public NotificationPanel getP() {
		return p;
	}

	public void setP(NotificationPanel p) {
		this.p = p;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

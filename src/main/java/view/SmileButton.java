package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class SmileButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int win = 0;
	public static final int lose = 1;
	public static final int press = 2;
	public static final int wow = 3;
	public static final int now = 4;

	private NotificationPanel p;

	private int state;

	public SmileButton(NotificationPanel p) {
		this.p = p;
		setPreferredSize(new Dimension(50, 50));

		state = now;
	}

	@Override
	public void paint(Graphics g) {
		switch (state) {
		case win:
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smileWin"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case lose:
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smileLose"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case press:
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smilePress"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case wow:
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smilePressPlay"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;
		case now:
			g.drawImage(p.getGame().getGameFrame().getLoadData().getListImage().get("smile"), 0, 0,
					getPreferredSize().width, getPreferredSize().height, null);
			break;

		default:
			break;
		}

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}

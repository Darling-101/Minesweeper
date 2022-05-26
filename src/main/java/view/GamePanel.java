package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.World;

public class GamePanel extends JPanel implements MouseListener {
	private NotificationPanel p1;
	private PlayPanel p2;

	private GameFrame gameFrame;

	private World world;

	private int w;
	private int h;
	private int boom;

	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		this.boom = boom;
		this.w = w;
		this.h = h;

		world = new World(w, h, boom, this);

		setLayout(new BorderLayout(20, 20));

		add(p1 = new NotificationPanel(this), BorderLayout.NORTH);
		add(p2 = new PlayPanel(this), BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		getP1().getSmileButton().setState(SmileButton.wow);
		getP1().getSmileButton().repaint();
		PlayButton[][] arrayButton = p2.getArrayButton();
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !world.getArrayCamCo()[i][j]) {

					if (!getP1().getTime().isRunning()) {
						getP1().getTime().start();
					}

					if (!world.open(i, j)) {

						if (world.isLose()) {

							getP1().getTime().stop();
							getP1().getSmileButton().setState(SmileButton.lose);
							getP1().getSmileButton().repaint();

							int option = JOptionPane.showConfirmDialog(this, "HAHA, lOSER. Play again?", "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							} else {
								world.setFullTrue();
							}
						} else if (world.isEnd()) {

							getP1().getTime().stop();
							getP1().getSmileButton().setState(SmileButton.win);
							getP1().getSmileButton().repaint();

							int option = JOptionPane.showConfirmDialog(this, "You win, play again ?", "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							}
						}
					}
				} else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
					world.camCo(i, j);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getP1().getSmileButton().setState(SmileButton._default);
		getP1().getSmileButton().repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public int getBoom() {
		return boom;
	}

	public void setBoom(int boom) {
		this.boom = boom;
	}

	public NotificationPanel getP1() {
		return p1;
	}

	public void setP1(NotificationPanel p1) {
		this.p1 = p1;
	}

	public PlayPanel getP2() {
		return p2;
	}

	public void setP2(PlayPanel p2) {
		this.p2 = p2;
	}
}

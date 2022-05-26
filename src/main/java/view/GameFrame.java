package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.LoadData;

public class GameFrame extends JFrame {

	private LoadData loadData;

	private GamePanel gamePanel;

	private JMenuBar mnb;
	private JMenu menu;
	private JMenuItem Basic, Normal, Hard, newGame, exit;

	public GameFrame(int w, int h, int boom) {

		loadData = new LoadData();

		setJMenuBar(mnb = new JMenuBar());
		mnb.add(menu = new JMenu("Game"));

		menu.add(newGame = new JMenuItem("New game"));
		menu.addSeparator();
		menu.add(Basic = new JMenuItem("Basic"));
		menu.add(Normal = new JMenuItem("Normal"));
		menu.add(Hard = new JMenuItem("Hard"));
		menu.addSeparator();
		menu.add(exit = new JMenuItem("Exit"));

		if (h == 8) {
			Basic.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
		} else if (h == 16) {
			Normal.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
		} else if (h == 30 ) {
			Hard.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
		}

		Basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(8, 8, 10);
			}
		});

		Normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(16, 16, 40);
			}
		});

		Hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(16, 30, 99);
			}
		});

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFrame(w, h, boom);
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(gamePanel = new GamePanel(w, h, boom, this));

		setIconImage(loadData.getListImage().get("icon"));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame(8, 8, 10);
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

}

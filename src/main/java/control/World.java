package control;

import java.util.Random;

import view.PlayButton;
import view.SmileButton;
import view.GamePanel;
import view.LabelNumber;

public class World {

	private Random rd;

	private PlayButton[][] arrayButton;
	private int[][] boomArray;

	private boolean[][] isPressedArray;

	private boolean[][] arrayCamCo;
	private int co;

	private boolean isLose;
	private boolean isEnd;

	private SmileButton buttonSmile;
	private LabelNumber lbTime, lbBoom;

	private int boom;

	private GamePanel game;

	public World(int w, int h, int boom, GamePanel game) {

		this.game = game;
		this.boom = boom;

		arrayButton = new PlayButton[w][h];
		boomArray = new int[w][h];
		isPressedArray = new boolean[w][h];
		arrayCamCo = new boolean[w][h];

		rd = new Random();

		createBoomArray(boom, w, h);
		displayBoomNumber();

	}

	public void camCo(int i, int j) {
		if (!isPressedArray[i][j]) {
			if (arrayCamCo[i][j]) {
				co--;
				arrayCamCo[i][j] = false;
				arrayButton[i][j].setNumber(-1);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			} else if (co < boom) {
				co++;
				arrayCamCo[i][j] = true;
				arrayButton[i][j].setNumber(9);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			}
		}
	}

	public boolean open(int i, int j) {
		if (!isLose && !isEnd) {
			if (!isPressedArray[i][j]) {
				if (boomArray[i][j] == 0) {
					isPressedArray[i][j] = true;
					arrayButton[i][j].setNumber(0);
					arrayButton[i][j].repaint();

					if (checkWin()) {
						isEnd = true;

						return false;
					}

					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= boomArray.length - 1 && k >= 0 && k <= boomArray[i].length - 1) {
								if (!isPressedArray[l][k]) {
									open(l, k);
								}
							}
						}
					}

					if (checkWin()) {
						isEnd = true;

						return false;
					}

				} else {

					int number = boomArray[i][j];

					if (number != -1) {

						isPressedArray[i][j] = true;

						arrayButton[i][j].setNumber(number);
						arrayButton[i][j].repaint();

						if (checkWin()) {
							isEnd = true;

							return false;
						}

						return true;
					}
				}
			}

			if (boomArray[i][j] == -1) {
				arrayButton[i][j].setNumber(11);
				arrayButton[i][j].repaint();
				isLose = true;

				for (int j2 = 0; j2 < isPressedArray.length; j2++) {
					for (int k = 0; k < isPressedArray[i].length; k++) {
						if (boomArray[j2][k] == -1 && !isPressedArray[j2][k]) {
							if (j2 != i || k != j) {
								arrayButton[j2][k].setNumber(10);
								arrayButton[j2][k].repaint();
							}
						}
					}
				}

				return false;
			} else {

				if (checkWin()) {
					isEnd = true;

					return false;
				}

				return true;
			}
		} else

			return false;

	}

	public boolean checkWin() {
		int count = 0;
		for (int i = 0; i < isPressedArray.length; i++) {
			for (int j = 0; j < isPressedArray[i].length; j++) {
				if (!isPressedArray[i][j]) {
					count++;
				}
			}
		}
		if (count == boom)
			return true;
		else
			return false;
	}

	public void displayBoomNumber() {
		for (int i = 0; i < boomArray.length; i++) {
			for (int j = 0; j < boomArray[i].length; j++) {
				if (boomArray[i][j] == 0) {
					int count = 0;
					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= boomArray.length - 1 && k >= 0 && k <= boomArray[i].length - 1)
								if (boomArray[l][k] == -1) {
									count++;
								}
						}
					}
					boomArray[i][j] = count;
				}
			}
		}
	}

	public void createBoomArray(int boom, int w, int h) {
		int count = 0;
		while (count != boom) {
			int positionX = rd.nextInt(w);
			int positionY = rd.nextInt(h);
			if (boomArray[positionX][positionY] != -1) {
				boomArray[positionX][positionY] = -1;
							count++;
			}
		}

	}

	public void setFullTrue() {
		for (int i = 0; i < isPressedArray.length; i++) {
			for (int j = 0; j < isPressedArray[i].length; j++) {
				if (!isPressedArray[i][j]) {
					isPressedArray[i][j] = true;
				}
			}
		}
	}

	public PlayButton[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(PlayButton[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public SmileButton getButtonSmile() {
		return buttonSmile;
	}

	public void setButtonSmile(SmileButton buttonSmile) {
		this.buttonSmile = buttonSmile;
	}

	public LabelNumber getLbTime() {
		return lbTime;
	}

	public void setLbTime(LabelNumber lbTime) {
		this.lbTime = lbTime;
	}

	public LabelNumber getLbBoom() {
		return lbBoom;
	}

	public void setLbBoom(LabelNumber lbBoom) {
		this.lbBoom = lbBoom;
	}

	public boolean[][] getIsPressedArray() {
		return isPressedArray;
	}

	public void setIsPressedArray(boolean[][] isPressedArray) {
		this.isPressedArray = isPressedArray;
	}

	public boolean isLose() {
		return isLose;
	}

	public void setLose(boolean isLose) {
		this.isLose = isLose;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean[][] getArrayCamCo() {
		return arrayCamCo;
	}

	public void setArrayCamCo(boolean[][] arrayCamCo) {
		this.arrayCamCo = arrayCamCo;
	}

	public int getCo() {
		return co;
	}

	public void setCo(int co) {
		this.co = co;
	}

}

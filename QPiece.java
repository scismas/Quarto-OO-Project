package quarto;

import java.awt.Image;

public class QPiece {

	private Image qPieceImage;
	private int x;
	private int y;

	public QPiece(Image qPieceImage, int x, int y) {
		this.qPieceImage = qPieceImage;
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return qPieceImage;
	}
	
	public int getWidth() {
		return qPieceImage.getHeight(null);
	}

	public int getHeight() {
		return qPieceImage.getHeight(null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}

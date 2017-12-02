package quarto;

import java.awt.Image;

public class QPiece {

	private Image qPieceImage;
	private int x;
	private int y;
	private int color;
	private int type;
	private int height;
	private int holiness;
	private boolean inSlot = false;

	public QPiece(Image qPieceImage, int x, int y, int color, int type, int height, int holiness) {
		this.qPieceImage = qPieceImage;
		this.x = x;
		this.y = y;
		this.color = color;
		this.type = type;
		this.height = height;
		this.holiness = holiness;
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
	
	public int getPHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getColor() {
		return color;
	}
	
	public boolean getInSlot() {
		return inSlot;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHoliness() {
		return holiness;
	}

	public void setHoliness(int holiness) {
		this.holiness = holiness;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setInSlot(boolean inSlot) {
		this.inSlot = inSlot;
	}
}

package quarto;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DnDListener implements MouseListener, MouseMotionListener {

	private List<QPiece> qPieces;
	private QPiece[][] boardArray = new QPiece[4][4];
	private QuartoGame quartoGame;
	
	private static final int startX = 130; //Where the first slot (top left) is
	private static final int startY = 145;
	private static final int offset = 82; // distance between slots, both vertical and horizontal
	private static final int imgOffset = 35;
	private static final int snapDist = 20; // distance to snap to slot
	
	private QPiece pieceDragged;
	private QPiece heldPiece;
	private int xDragOffset;
	private int yDragOffset;
	
	public DnDListener(List<QPiece> qPieces, QuartoGame quartoGame) {
		this.qPieces = qPieces;
		this.quartoGame = quartoGame;
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
	    int y = e.getY();
		//System.out.println("X: " + x + " Y: " + y); //testing where the slots are on the board
	    for (QPiece qPiece : this.qPieces) {
			if (mouseOverPiece(qPiece, x, y) && !qPiece.getInSlot()) {
				this.xDragOffset = x - qPiece.getX();
				this.yDragOffset = y - qPiece.getY();
				this.pieceDragged = qPiece;
				break;
			}
		}
	}

	private boolean checkQuarto() {
		QPiece[][] board= quartoGame.getBoardArray();
		int colorSum = 0;
		int typeSum = 0;
		int heightSum = 0;
		int holinessSum = 0;
		
		for (int i = 0; i < 4; ++i) { //Check Columns
			colorSum = 0;
			typeSum = 0;
			heightSum = 0;
			holinessSum = 0;
			for (int j = 1; j < 4; ++j) { //Starting with 2nd in column, check quality equivalence
				if (board[i][j] != null && board[i][0] != null) {
					if (board[i][0].getColor() == board[i][j].getColor()) {
						++colorSum;
					}
					if (board[i][0].getType() == board[i][j].getType()) {
						++typeSum;
					}
					
					if (board[i][0].getPHeight() == board[i][j].getPHeight()) {
						++heightSum;
					}
					if (board[i][0].getHoliness() == board[i][j].getHoliness()) {
						++holinessSum;
					}
				}
			}
			if (colorSum == 3 || typeSum == 3 || heightSum == 3 || holinessSum == 3) {
				return true;
			}
		}
		
		for (int j = 0; j < 4; ++j) { //Check Rows
			colorSum = 0;
			typeSum = 0;
			heightSum = 0;
			holinessSum = 0;
			for (int i = 1; i < 4; ++i) { //Starting with 2nd in row, check quality equivalence
				if (board[i][j] != null && board[0][j] != null) {
					if (board[0][j].getColor() == board[i][j].getColor()) {
						++colorSum;
					}
					if (board[0][j].getType() == board[i][j].getType()) {
						++typeSum;
					}
					if (board[0][j].getPHeight() == board[i][j].getPHeight()) {
						++heightSum;
					}
					if (board[0][j].getHoliness() == board[i][j].getHoliness()) {
						++holinessSum;
					}
				}
			}
			if (colorSum == 3 || typeSum == 3 || heightSum == 3 || holinessSum == 3) {
				return true;
			}
		}
		
		colorSum = 0;
		typeSum = 0;
		heightSum = 0;
		holinessSum = 0;
		for (int i = 1; i < 4; ++i) { //Check Diagonal l->r
			if (board[i][i] != null && board[0][0] != null) {
				if (board[0][0].getColor() == board[i][i].getColor()) {
					++colorSum;
				}
				if (board[0][0].getType() == board[i][i].getType()) {
					++typeSum;
				}
				if (board[0][0].getPHeight() == board[i][i].getPHeight()) {
					++heightSum;
				}
				if (board[0][0].getHoliness() == board[i][i].getHoliness()) {
					++holinessSum;
				}
			}
		}
		if (colorSum == 3 || typeSum == 3 || heightSum == 3 || holinessSum == 3) {
			return true;
		}
		
		colorSum = 0;
		typeSum = 0;
		heightSum = 0;
		holinessSum = 0;
		for (int i = 1; i < 4; ++i) { //Check Diagonal r->l
			int j = 3 - i;
			if (board[i][j] != null && board[0][3] != null) {
				if (board[0][3].getColor() == board[i][j].getColor()) {
					++colorSum;
				}
				if (board[0][3].getType() == board[i][j].getType()) {
					++typeSum;
				}
				if (board[0][3].getPHeight() == board[i][j].getPHeight()) {
					++heightSum;
				}
				if (board[0][3].getHoliness() == board[i][j].getHoliness()) {
					++holinessSum;
				}
			}
		}
		if (colorSum == 3 || typeSum == 3 || heightSum == 3 || holinessSum == 3) {
			return true;
		}
		
		return false;
	}
	
	private boolean mouseOverPiece(QPiece qPiece, int x, int y) {
		return (qPiece.getX() <= x 
				&& qPiece.getX() < x && x <= qPiece.getX() + qPiece.getWidth()/3
				&& qPiece.getY() <= y
				&& qPiece.getY() < y && y <= qPiece.getY() + qPiece.getHeight()/3);
	}
	
	private int[] closestSlot(int x, int y) { //Finds nearest x/y slot within 40 pixels/ if none within 40 pixels then return same number
		int[] p = new int[2];
		if (x < startX - snapDist || x > startX + 4*offset + snapDist) {
			p[0] = x - 25; //Some weird offset so that the piece doesn't jump to a weird spot if there's not slot
			p[1] = y - 25;
			return p;
		}
		else if (y < startY - snapDist || y > startY + 4*offset + snapDist) {
			p[0] = x - 25;
			p[1] = y - 25;
			return p;
		}
		else {
			if ((x - startX) % offset > offset/2) {
				p[0] = x - imgOffset + (offset - (x - startX) % offset);
			}
			else {
				p[0] = x - imgOffset - (x - startX) % offset;
			}
			
			if ((y - startY) % offset > offset/2) {
				p[1] = y - imgOffset + (offset - (y - startY) % offset);
			}
			else {
				p[1] = y - imgOffset - (y - startY) % offset;
			}
			
			int col = (p[0] - startX + imgOffset) / offset;
			int row = (p[1] - startY + imgOffset) / offset;
			
			if (boardArray[row][col] == null) { //Add piece to board only if slot not filled
				boardArray[row][col] = this.pieceDragged;
				quartoGame.slotPiece(this.pieceDragged, row, col);
				//System.out.println("Placed at: " + row + ", " + col);
				//System.out.println(boardArray[row][col].getColor() + ", " + boardArray[row][col].getType() + ", " + boardArray[row][col].getPHeight() + ", " + boardArray[row][col].getHoliness());
				this.pieceDragged.setInSlot(true);
				//quartoGame.nextPlayer();
			}
			else {
				p[0] = 380; //reset piece to some other place
				p[1] = 500;
			}
		}
		
		return p;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.pieceDragged != null) {
			this.pieceDragged.setX(e.getPoint().x - this.xDragOffset);
			this.pieceDragged.setY(e.getPoint().y - this.yDragOffset);
			this.quartoGame.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (this.pieceDragged != null) {
			if (x >= 170 && x <= 340 && y >= 740 && y <= 820) {
				this.pieceDragged.setX(230);
				this.pieceDragged.setY(750);
				this.quartoGame.repaint();
				this.pieceDragged = null;
				quartoGame.clearFail();
				quartoGame.nextPlayer();
				quartoGame.updatePlacement();
			}
			else {
				int[] newP = closestSlot(e.getPoint().x, e.getPoint().y);
				this.pieceDragged.setX(newP[0]);
				this.pieceDragged.setY(newP[1]);
				this.quartoGame.repaint();
				this.pieceDragged = null;
				quartoGame.updatePlacement();
			}
			/*
			QPiece[][] board= quartoGame.getBoardArray();
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					if (board[i][j] != null) {
						System.out.print(board[i][j].getColor() + ", " + board[i][j].getType() + ", " + board[i][j].getPHeight() + ", " + board[i][j].getHoliness() + " | ");
					}
					else {
						System.out.print("-, -, -, - | ");
					}
				}
				System.out.println("");
			}
			System.out.println("");
			*/
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if (x >= 150 && x <= 350 && y >= 850 && y <= 920) {
	    	if (checkQuarto()) {
	    		//System.out.println("Win!");
	    		quartoGame.updateWin();
	    		quartoGame.repaint();
	    	}
	    	else {
	    		quartoGame.updateFail();
	    		quartoGame.repaint();
	    	}
	    }
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}


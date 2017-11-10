package QuartoPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DnDListener implements MouseListener, MouseMotionListener {

	private List<QPiece> qPieces;
	private QuartoGame quartoGame;
	
	private QPiece pieceDragged;
	private int xDragOffset;
	private int yDragOffset;
	
	public DnDListener(List<QPiece> qPieces, QuartoGame quartoGame) {
		this.qPieces = qPieces;
		this.quartoGame = quartoGame;
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
	    int y = e.getY();
		
	    for (QPiece qPiece : this.qPieces) {
			if (mouseOverPiece(qPiece, x, y)) {
				this.xDragOffset = x - qPiece.getX();
				this.yDragOffset = y - qPiece.getY();
				this.pieceDragged = qPiece;
				break;
			}
		}
	}

	private boolean mouseOverPiece(QPiece qPiece, int x, int y) {
		return (qPiece.getX() <= x 
				&& qPiece.getX() < x && x <= qPiece.getX() + qPiece.getWidth()/3
				&& qPiece.getY() <= y
				&& qPiece.getY() < y && y <= qPiece.getY() + qPiece.getHeight()/3);
			//&& qPiece.getY() + qPiece.getHeight() >= y);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.pieceDragged != null) {
			this.pieceDragged.setX(e.getPoint().x - this.xDragOffset);
			this.pieceDragged.setY(e.getPoint().y - this.yDragOffset);
			this.quartoGame.repaint();
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		this.pieceDragged = null;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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


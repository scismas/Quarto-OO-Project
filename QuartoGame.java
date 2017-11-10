package QuartoPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class QuartoGame extends JPanel {
	
	private static final int WHITE_PIECE = 0;
	private static final int BLACK_PIECE = 1;
	
	private static final int RECT_PIECE = 2;
	private static final int CYLND_PIECE = 3;
	
	private static final int SHORT_PIECE = 4;
	private static final int TALL_PIECE = 5;
	
	private static final int HOLE_PIECE = 6;
	private static final int FILLED_PIECE = 7;
	
	private static final int X_BOARD_START = 50;
	private static final int Y_BOARD_START = 50;

	private static final int PIECE_OFFSET_X = 50;
	private static final int PIECE_OFFSET_Y = 50;

	private Image boardBackground;
	private List<QPiece> tallQPieces = new ArrayList<QPiece>();
	private List<QPiece> shortQPieces = new ArrayList<QPiece>();
	//private List<QPiece> qPieces = new ArrayList<QPiece>();
	
	public QuartoGame() {
		
		URL backgroundImage = getClass().getResource("/QuartoPackage/img/board.png");
        this.boardBackground = new ImageIcon(backgroundImage).getImage();
        
        createAndAddPieceShort(BLACK_PIECE, RECT_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 0, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceShort(BLACK_PIECE, RECT_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 1, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceShort(WHITE_PIECE, RECT_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 2, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceShort(WHITE_PIECE, RECT_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 3, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceShort(WHITE_PIECE, CYLND_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 4, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        createAndAddPieceShort(WHITE_PIECE, CYLND_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 5, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        createAndAddPieceShort(BLACK_PIECE, CYLND_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 6, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        createAndAddPieceShort(BLACK_PIECE, CYLND_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 7, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        
        createAndAddPieceTall(WHITE_PIECE, RECT_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 4, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceTall(WHITE_PIECE, RECT_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 5, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceTall(BLACK_PIECE, RECT_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 6, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceTall(BLACK_PIECE, RECT_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 7, Y_BOARD_START + PIECE_OFFSET_Y * 10);
        createAndAddPieceTall(WHITE_PIECE, CYLND_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 0, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        createAndAddPieceTall(WHITE_PIECE, CYLND_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 1, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        createAndAddPieceTall(BLACK_PIECE, CYLND_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 2, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        createAndAddPieceTall(BLACK_PIECE, CYLND_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 3, Y_BOARD_START + PIECE_OFFSET_Y * 12);
        
    
        
        DnDListener listener = new DnDListener(this.shortQPieces, this);
        DnDListener listener2 = new DnDListener(this.tallQPieces, this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addMouseListener(listener2);
		this.addMouseMotionListener(listener2);
        
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setResizable(false);
		frame.setSize(525, 1000);
		
	} 

        private void createAndAddPieceShort(int color, int type, int height, int holiness, int x, int y) {
    			Image qPieceImage = this.getImageForPiece(color, type, height, holiness);
    			QPiece qPiece = new QPiece(qPieceImage, x, y);
    			this.shortQPieces.add(qPiece);
        }
        
        private void createAndAddPieceTall(int color, int type, int height, int holiness, int x, int y) {
			Image qPieceImage = this.getImageForPiece(color, type, height, holiness);
			QPiece qPiece = new QPiece(qPieceImage, x, y);
			this.tallQPieces.add(qPiece);
			
	/*
	 	private void createAndAddPiece(int color, int type, int height, int holiness, int x, int y) {
			Image qPieceImage = this.getImageForPiece(color, type, height, holiness);
			QPiece qPiece = new QPiece(qPieceImage, x, y);
			this.qPieces.add(qPiece);
	*/
	 	}
        
        private Image getImageForPiece(int color, int type, int height, int holiness) {
    		String filename = "";

    		switch (color) {
    		case WHITE_PIECE:
    			filename += "w";
    			break;
    		case BLACK_PIECE:
    			filename += "b";
    			break;
    		}
    		switch (type) {
    		case RECT_PIECE:
    			filename += "r";
    			break;
    		case CYLND_PIECE:
    			filename += "c";
    			break;
    		}
    		switch (height) {
    		case SHORT_PIECE:
    			filename += "s";
    			break;
    		case TALL_PIECE:
    			filename += "t";
    			break;
    		}
    		switch (holiness) {
    		case HOLE_PIECE:
    			filename += "h";
    			break;
    		case FILLED_PIECE:
    			filename += "f";
    			break;
    		}
    			
    		filename += ".png";
    	    
    		URL urlPieceImg = getClass().getResource("/QuartoPackage/img/" + filename);
    	    return new ImageIcon(urlPieceImg).getImage();
    	}
        
        protected void paintComponent(Graphics g) {
        		g.drawImage(this.boardBackground, 0, 25, 500, 500, null);
      
    			for (QPiece qPiece1 : this.shortQPieces) {
    				g.drawImage(qPiece1.getImage(), qPiece1.getX(), qPiece1.getY(), 40, 50, null);
    			}
    			for (QPiece qPiece2 : this.tallQPieces) {
    				g.drawImage(qPiece2.getImage(), qPiece2.getX(), qPiece2.getY(), 50, 75, null);
    			}
    			
        		
        		/*
    			for (int i = 0; i < (this.qPieces.size()-1)/2; i++) {
    				//QPiece qPiece = this.qPieces.get(i);
    				g.drawImage(this.qPieces.get(i).getImage(), this.qPieces.get(i).getX(), this.qPieces.get(i).getY(), 40, 50, null);
    			}
    			for (int i = (this.qPieces.size()-1)/2; i < this.qPieces.size()-1; i++) {
    				//QPiece qPiece = this.qPieces.get(i);
    				g.drawImage(this.qPieces.get(i).getImage(), this.qPieces.get(i).getX(), this.qPieces.get(i).getY(), 50, 100, null);
    			}
    			*/
    			/*
			for (Piece piece : this.pieces) {
				g.drawImage(piece.getImage(), piece.getX(), piece.getY(), null);
			}
    			*/
    			
        }

    	public static void main(String[] args) {
    		new QuartoGame();
    	}
}

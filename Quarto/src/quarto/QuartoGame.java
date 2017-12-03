package quarto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	
	private String playerOne = "";
	private String playerTwo = "";
	private int charNum = 0;
	private int mode = 0;
	
	private int curPlayer = 1;
	private boolean win = false;
	private boolean fail = false;
	private boolean placed = false;

	private Image boardBackground;
	private List<QPiece> tallQPieces = new ArrayList<QPiece>();
	private List<QPiece> shortQPieces = new ArrayList<QPiece>();
	private QPiece[][] boardArray = new QPiece[4][4];
	private DnDListener listener;
	private DnDListener listener2;

	public QuartoGame() {

		URL backgroundImage = getClass().getResource("/quarto/img/board.png");
		this.boardBackground = new ImageIcon(backgroundImage).getImage();

		createAndAddPieceShort(BLACK_PIECE, RECT_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 0,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceShort(BLACK_PIECE, RECT_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 1,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceShort(WHITE_PIECE, RECT_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 2,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceShort(WHITE_PIECE, RECT_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 3,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceShort(WHITE_PIECE, CYLND_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 4,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);
		createAndAddPieceShort(WHITE_PIECE, CYLND_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 5,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);
		createAndAddPieceShort(BLACK_PIECE, CYLND_PIECE, SHORT_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 6,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);
		createAndAddPieceShort(BLACK_PIECE, CYLND_PIECE, SHORT_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 7,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);

		createAndAddPieceTall(WHITE_PIECE, RECT_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 4,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceTall(WHITE_PIECE, RECT_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 5,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceTall(BLACK_PIECE, RECT_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 6,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceTall(BLACK_PIECE, RECT_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 7,
				Y_BOARD_START + PIECE_OFFSET_Y * 10);
		createAndAddPieceTall(WHITE_PIECE, CYLND_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 0,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);
		createAndAddPieceTall(WHITE_PIECE, CYLND_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 1,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);
		createAndAddPieceTall(BLACK_PIECE, CYLND_PIECE, TALL_PIECE, HOLE_PIECE, X_BOARD_START + PIECE_OFFSET_X * 2,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);
		createAndAddPieceTall(BLACK_PIECE, CYLND_PIECE, TALL_PIECE, FILLED_PIECE, X_BOARD_START + PIECE_OFFSET_X * 3,
				Y_BOARD_START + PIECE_OFFSET_Y * 12);

		listener = new DnDListener(this.shortQPieces, this);
		listener2 = new DnDListener(this.tallQPieces, this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addMouseListener(listener2);
		this.addMouseMotionListener(listener2);

		
		JFrame mainFrame = new JFrame("Welcome");
		JPanel p1 = new JPanel(new GridLayout(7, 1, 10, 10));
		JLabel l1 = new JLabel("Welcome to Quarto!", JLabel.CENTER);
		JTextField t1 = new JTextField("Enter Player 1 Name: ", JLabel.CENTER);
		t1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t1.setText("");
            }
        });
		JTextField t2 = new JTextField("Enter Player 2 Name: ", JLabel.CENTER);
		t2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t2.setText("");
            }
        });
		JComboBox c1 = new JComboBox();
		c1.addItem("Select Number of Characteristics");
		c1.addItem("1");
		c1.addItem("2");
		JComboBox c2 = new JComboBox();
		c2.addItem("Select Pattern Difficulty");
		c2.addItem("Linear");
		c2.addItem("Square");
		c2.addItem("Linear + Square");
		l1.setFont(new Font("Courier New", Font.ITALIC, 25));
		l1.setForeground(Color.RED);
		JButton inst = new JButton("Instructions");
		JButton start = new JButton("Start!");
		start.setFont(new Font("Courier New", Font.BOLD, 20));
		start.setForeground(Color.RED);

		p1.add(l1);
		p1.add(t1);
		p1.add(t2);
		p1.add(c1);
		p1.add(c2);
		p1.add(inst);
		p1.add(start);
		mainFrame.add(p1);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame gameFrame = new JFrame("The Game of Quarto");
				QuartoGame game = new QuartoGame();
				gameFrame.add(game);
				gameFrame.setResizable(false);
				gameFrame.setSize(525, 1000);
				mainFrame.dispose();
				gameFrame.setVisible(true);
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				String p1, p2;
				int charNum, mode;
				charNum = c1.getSelectedIndex();
				mode = c2.getSelectedIndex();
				if (t1.getText().isEmpty() || t1.getText().equals("Enter Player 1 Name: ")) {
					p1 = "Player One";
				}
				else {
					p1 = t1.getText();
				}
				if (t2.getText().isEmpty() || t2.getText().equals("Enter Player 2 Name: ")) {
					p2 = "Player Two";
				}
				else {
					p2 = t2.getText();
				}
				
				game.updateInfo(p1, p2, charNum, mode);
				game.updateDnd();
			}
		});
		inst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame instFrame = new JFrame("Instructions");
				JPanel p = new JPanel();

				String instructions = " Quarto is a two-player game that consists of a 4x4 game board and 16 game pieces.\n "
						+ "The pieces have four unique defining characteristics: height (short or tall),\n "
						+ "shape (circular or square), hollowness (hollow top or solid top), and color\n "
						+ "(white or black). There are eight iterations of each characteristic, so eight\n "
						+ "pieces are white, eight pieces are short, etc. The game is played with each\n "
						+ "player choosing a piece out of the pool of remaining pieces, and giving it to\n "
						+ "their opponent. The opponent then places this piece on one of the 16 remaining\n "
						+ "spots on the board, with the intention of getting a line of four pieces with one\n "
						+ "common trait, i.e. all four pieces are tall, or all four are round, etc. The players\n "
						+ "alternate these steps until a player, on their turn, notices a winning pattern and\n "
						+ "calls ‘Quarto!’.\n"
						+ " You can customize the difficulty to include a square of four pieces as a winning pattern,\n"
						+ " as well as consider up to three characteristics to dictate a winning pattern.\n";

				JTextArea textArea = new JTextArea(6, 25);
				textArea.setText(instructions);
				textArea.setEditable(false);

				p.add(textArea);
				instFrame.add(p);
				instFrame.setResizable(false);
				instFrame.pack();
				instFrame.setVisible(true);
				instFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		mainFrame.setSize(500, 500);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void createAndAddPieceShort(int color, int type, int height, int holiness, int x, int y) {
		Image qPieceImage = this.getImageForPiece(color, type, height, holiness);
		QPiece qPiece = new QPiece(qPieceImage, x, y, color, type, height, holiness);
		this.shortQPieces.add(qPiece);
	}

	private void createAndAddPieceTall(int color, int type, int height, int holiness, int x, int y) {
		Image qPieceImage = this.getImageForPiece(color, type, height, holiness);
		QPiece qPiece = new QPiece(qPieceImage, x, y, color, type, height, holiness);
		this.tallQPieces.add(qPiece);
	}

	private Image getImageForPiece(int color, int type, int height, int holiness) { //Cleanliness is next to holiness.
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

		URL urlPieceImg = getClass().getResource("/quarto/img/" + filename);
		return new ImageIcon(urlPieceImg).getImage();
	}

	public void nextPlayer() {
		if (curPlayer == 1) {
			curPlayer = 2;
		}
		else {
			curPlayer = 1;
		}
	}
	
	public void updateWin() {
		win = true;
		fail = false;
	}
	
	public void updateFail() {
		win = false;
		fail = true;
	}
	
	public void clearFail() {
		fail = false;
	}
	
	public void updatePlacement() {
		if (placed) {
			placed = false;
		}
		else {
			placed = true;
		}
	}
	
	public void updateInfo(String p1, String p2, int charNum, int mode) {
		playerOne = p1;
		playerTwo = p2;
		this.charNum = charNum;
		this.mode = mode;
	}
	
	public void updateDnd() {
		listener.updateDnD();
		listener2.updateDnD();
	}
	
	public void slotPiece(QPiece piece, int i, int j) {
		boardArray[i][j] = piece;
	}
	
	public QPiece[][] getBoardArray() {
		return boardArray;
	}
	
	public String[] getPlayerNames() {
		String[] playerNames = {playerOne, playerTwo};
		return playerNames;
	}
	
	public int[] getGameInfo() {
		int[] gameInfo = {charNum, mode};
		return gameInfo;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D myGraphics = (Graphics2D) g;
		Rectangle2D background = new Rectangle2D.Float(0, 0, 525, 1000);
		myGraphics.draw(background);
		myGraphics.setColor(Color.WHITE);
		myGraphics.fill(background);
		g.drawImage(this.boardBackground, 0, 25, 500, 500, null);
		
		Rectangle2D pieceHolder = new Rectangle2D.Float(180, 750, 150, 80);
		if (placed) {
			myGraphics.setColor(Color.CYAN);
		}
		else {
			myGraphics.setColor(Color.GRAY);
		}
		myGraphics.fill(pieceHolder);
		
		for (QPiece qPiece1 : this.shortQPieces) {
			g.drawImage(qPiece1.getImage(), qPiece1.getX(), qPiece1.getY(), 40, 50, null);
		}
		for (QPiece qPiece2 : this.tallQPieces) {
			g.drawImage(qPiece2.getImage(), qPiece2.getX(), qPiece2.getY(), 50, 75, null);
		}
		
		Ellipse2D player1Turn = new Ellipse2D.Float(75, 750, 85, 85);
		Ellipse2D player2Turn = new Ellipse2D.Float(350, 750, 85, 85);
		Ellipse2D player1Circle = new Ellipse2D.Float(68, 742, 100, 100);
		Ellipse2D player2Circle = new Ellipse2D.Float(343, 742, 100, 100);
		
		Rectangle2D QuartoButton = new Rectangle2D.Float(150, 850, 200, 70);
		myGraphics.setColor(new Color(170, 250, 240));
		myGraphics.fill(QuartoButton);
		
		myGraphics.draw(player1Turn);
		myGraphics.draw(player2Turn);
		
		myGraphics.setColor(Color.LIGHT_GRAY);
		myGraphics.fill(player1Circle);
		myGraphics.fill(player2Circle);
		
		myGraphics.setColor(Color.GREEN);
		if (curPlayer == 1) {
			myGraphics.fill(player1Turn);
		}
		else {
			myGraphics.fill(player2Turn);
		}
		myGraphics.setColor(Color.BLACK);
		myGraphics.setFont(new Font("Times New Roman", Font.BOLD, 50));
		myGraphics.drawString("P1", 89, 810);
		myGraphics.drawString("P2", 364, 810);
		myGraphics.drawString("Quarto!", 165, 900);
		
		Rectangle2D winnerBox = new Rectangle2D.Float(50, 50, 425, 100);
		if (win) {
			myGraphics.setColor(new Color(90, 220, 100));
			myGraphics.fill(winnerBox);
			myGraphics.setColor(Color.BLACK);
			String winString;
			if (curPlayer == 1) {
				winString = playerOne + " won!";
			}
			else {
				winString = playerTwo + " won!";
			}
			myGraphics.drawString(winString, 100, 120);
		}
		if (fail) {
			myGraphics.setColor(Color.RED);
			myGraphics.setFont(new Font("Times New Roman", Font.BOLD, 80));
			myGraphics.drawString("X", 220, 910);
		}
	}

	public static void main(String[] args) {
		new QuartoGame();
	}
}

// link mainFrame parameters to gameFrame (player names, difficulty, winning patterns)

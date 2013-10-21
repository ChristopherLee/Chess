package chess.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.board.Bishop;
import chess.board.Board;
import chess.board.Color;
import chess.board.Constants;
import chess.board.Coordinate;
import chess.board.Knight;
import chess.board.Move;
import chess.board.NonePiece;
import chess.board.Pawn;
import chess.board.Piece;
import chess.board.King;
import chess.board.Queen;
import chess.board.Rook;

public class BoardGUI extends JPanel {
	protected final static int SIZE_OF_SQUARE = 42;
	protected final static int STARTING_X_INDEX = 50;
	protected final static int STARTING_Y_INDEX = 10;
	protected final static java.awt.Color SELECTED_SQUARE_COLOR = java.awt.Color.BLUE;
	private final static java.awt.Color BACKGROUND_COLOR = java.awt.Color.WHITE;
	private final static java.awt.Color BOARD_COLOR = java.awt.Color.BLACK;
	private final static java.awt.Color NOTATION_COLOR = java.awt.Color.BLACK;
	protected final Board board;
	protected Coordinate selectedSquare;
	public BoardGUI(Board board) {
		super();
		this.board = board;
		this.setBackground(BACKGROUND_COLOR);
		setVisible(true);
		
		Font f = new Font ("Serif", Font.PLAIN, 12);
		setFont(f);
	}
	
	private void paintBoard(Graphics graphics) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				graphics.setColor(BOARD_COLOR);
				graphics.drawRect(STARTING_X_INDEX + (j * SIZE_OF_SQUARE), STARTING_Y_INDEX + (i * SIZE_OF_SQUARE), SIZE_OF_SQUARE, SIZE_OF_SQUARE);
			}
		}
	}
	
	private void paintPieces(Graphics graphics) throws IOException{
		Piece[][] pieces = board.getPieces();
		
		
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				Piece piece = pieces[j][i];
				String pieceFileName = "";
				if (piece instanceof King) {
					if (piece.getColor() == Color.WHITE)
						pieceFileName = Constants.WHITE_KING_FILENAME;
					else
						pieceFileName = Constants.BLACK_KING_FILENAME;
				}
				else if (piece instanceof Queen) {
					if (piece.getColor() == Color.WHITE)
						pieceFileName = Constants.WHITE_QUEEN_FILENAME;
					else
						pieceFileName = Constants.BLACK_QUEEN_FILENAME;
				}
				else if (piece instanceof Bishop) {
					if (piece.getColor() == Color.WHITE)
						pieceFileName = Constants.WHITE_BISHOP_FILENAME;
					else
						pieceFileName = Constants.BLACK_BISHOP_FILENAME;
				} 
				else if (piece instanceof Knight) {
					if (piece.getColor() == Color.WHITE)
						pieceFileName = Constants.WHITE_KNIGHT_FILENAME;
					else
						pieceFileName = Constants.BLACK_KNIGHT_FILENAME;
				} 
				else if (piece instanceof Rook) {
					if (piece.getColor() == Color.WHITE)
						pieceFileName = Constants.WHITE_ROOK_FILENAME;
					else
						pieceFileName = Constants.BLACK_ROOK_FILENAME;
				} 
				else if (piece instanceof Pawn) {
					if (piece.getColor() == Color.WHITE)
						pieceFileName = Constants.WHITE_PAWN_FILENAME;
					else
						pieceFileName = Constants.BLACK_PAWN_FILENAME;
				}
				else if (piece instanceof NonePiece){
					continue;
				}
				//graphics.drawString(str, bottomLeftX + (j+1)*SIZE_OF_SQUARE - SIZE_OF_SQUARE/2, bottomLeftY - (i*SIZE_OF_SQUARE) - SIZE_OF_SQUARE/2);

				Image image = ImageIO.read(new File(Constants.IMAGES_PATH + pieceFileName));
				Point topLeftPoint = getTopLeftPointOfSquare(new Coordinate(j, i));
				graphics.drawImage(image, 
								   (int)topLeftPoint.getX(),
								   (int)topLeftPoint.getY(), 
								   null);
			}
		}
	}
	
	protected static Point getTopLeftPointOfSquare(Coordinate coord) {
		int bottomLeftX = STARTING_X_INDEX;
		int bottomLeftY = STARTING_Y_INDEX + (SIZE_OF_SQUARE*8);
		return new Point(bottomLeftX + (coord.getCol())*SIZE_OF_SQUARE, bottomLeftY - ((coord.getRow() + 1)*SIZE_OF_SQUARE));
	}
	
	private void paintNotation(Graphics graphics) {
		int bottomLeftOfRowNotationX = STARTING_X_INDEX - 15;
		int bottomLeftOfRowNotationY = STARTING_Y_INDEX + (SIZE_OF_SQUARE*8);
		
		int bottomLeftOfColNotationX = STARTING_X_INDEX;
		int bottomLeftOfColNotationY = STARTING_Y_INDEX + (SIZE_OF_SQUARE*8) + 15;
		
		Font oldFont = graphics.getFont();
		Font font = new Font("Serif", Font.BOLD, 12);
		
		graphics.setFont(font);
		graphics.setColor(NOTATION_COLOR);
		
		for (int i = 0; i < 8; i++) {
			String number = "" + (i + 1);
			graphics.drawString(number, bottomLeftOfRowNotationX, bottomLeftOfRowNotationY - (i*SIZE_OF_SQUARE) - SIZE_OF_SQUARE/2);
		}
		
		for (int i = 0; i < 8; i++) {
			String letter = Coordinate.convertIndexToLetter(i);
			graphics.drawString(letter, bottomLeftOfColNotationX + (i+1)*SIZE_OF_SQUARE - SIZE_OF_SQUARE/2, bottomLeftOfColNotationY);
		}
		
		graphics.setFont(oldFont);
	}
	
	private void paintPlayersTurn(Graphics graphics) {
		//TODO:
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		paintBoard(graphics);
		try {
			paintPieces(graphics);
		}
		catch (IOException e) {
			System.out.println("Pieces image could not be read");
            System.exit(1);
		}
		paintNotation(graphics);
	}
	
	public static JFrame addToFrame(BoardGUI gui) {
		JFrame frame = new JFrame();
		frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
		frame.setContentPane(gui);
		frame.setSize(new Dimension(430, 430));
		frame.setVisible(true);
		
		return frame;
	}
		
	public static void main(String[] args) throws IOException{
		Board board = new Board();
		BoardGUI boardGUIStart = new BoardGUI(board);
		JFrame frame = addToFrame(boardGUIStart);
		
		List<Move> moves = new ArrayList<Move> ();
		try {
			Move move1 = new Move(new Coordinate("E2"), new Coordinate("E4"));
			Move move2 = new Move(new Coordinate("E7"), new Coordinate("E5"));
			Move move3 = new Move(new Coordinate("G1"), new Coordinate("F3"));
			Move move4 = new Move(new Coordinate("B8"), new Coordinate("C6"));
			
			moves.add(move1);
			moves.add(move2);
			moves.add(move3);
			moves.add(move4);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.in.read();
		frame.setVisible(false);
		for(int i = 0; i < moves.size(); i++) {
			if (board.execute(moves.get(i))) {
				BoardGUI boardGUI = new BoardGUI(board);
				JFrame moveFrame = addToFrame(boardGUI);
				System.in.read();
				moveFrame.setVisible(false);
			}
			else {
				System.out.println("Invalid move");
			}
		}
	}
}

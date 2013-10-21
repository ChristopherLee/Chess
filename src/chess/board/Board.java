package chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * data representation of a chess board
 */
public class Board {
	public static final int NUMBER_OF_ROWS = 8;
	public static final int NUMBER_OF_COLS = 8;
	private final Piece[][] pieces;
	
	private Color currentTurnColor;
	
	private enum GAME_OVER {WHITE_CHECKMATED, BLACK_CHECKMATED, WHITE_RESIGNS, BLACK_RESIGNS, STALEMATE, DRAW, SAME_POSITION} 
	private GAME_OVER game_over;

	public Board() {
		this.pieces = new Piece[NUMBER_OF_COLS][NUMBER_OF_ROWS];
		setupStartingPieces();
		
		currentTurnColor = Color.WHITE;
		game_over = null;
	}
	
	public Board(Map<Coordinate, Piece> piecesMap) {
		this.pieces = new Piece[NUMBER_OF_COLS][NUMBER_OF_ROWS];
		setupNonePieces();
		for(Coordinate coord : piecesMap.keySet()) {
			placePiece(coord, piecesMap.get(coord));
		}
		
		currentTurnColor = Color.WHITE;
	}
	
	public Map<Coordinate, Piece> getPiecesMap() {
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece> ();
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				Piece piece = getPiece(i, j);
				map.put(new Coordinate(i, j), piece);
			}
		}
		return map;
	}
	
	public void setCurrentTurnColor(Color color) {
		this.currentTurnColor = color;
	}
	
	public boolean isGameOver() {
		return game_over != null;
	}
	
	public GAME_OVER getGameOverState() {
		return game_over;
	}
	
	
	public Piece[][] getPieces () {
		return this.pieces;
	}

	private void setupStartingPieces() {
		setupNonePieces();
		setupPawns(Color.WHITE);
		setupPieces(Color.WHITE);
		setupPawns(Color.BLACK);
		setupPieces(Color.BLACK);
	}

	private void setupPieces(Color color) {
		int pieceRow = (color == Color.WHITE) ? 0 : 7;
		pieces[0][pieceRow] = new Rook(color);
		pieces[1][pieceRow] = new Knight(color);
		pieces[2][pieceRow] = new Bishop(color);
		pieces[3][pieceRow] = new Queen(color);
		pieces[4][pieceRow] = new King(color);
		pieces[5][pieceRow] = new Bishop(color);
		pieces[6][pieceRow] = new Knight(color);
		pieces[7][pieceRow] = new Rook(color);
	}

	private void setupPawns(Color color) {
		int pawnRow = (color == Color.WHITE) ? Constants.INITIAL_WHITE_PAWN_RANK : Constants.INITIAL_BLACK_PAWN_RANK;   
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			pieces[i][pawnRow] = new Pawn(color);
		}
	}

	private void setupNonePieces() {
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				pieces[i][j] = new NonePiece();
			}
		}
	}
	
	private void placePiece(Coordinate coord, Piece piece) {
		this.pieces[coord.getCol()][coord.getRow()] = piece;
	}
	
	public boolean isInCheck(Color color) {
		return findThreatenedSquares(color).contains(getKingCoordinate(color));
	}
	
	private King getKing(Color color) {
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				Piece piece = pieces[j][i];
				if (piece.name.equals(Constants.NAME_KING) && piece.getColor() == color)
					return (King)piece;
			}
		}
		System.out.println("There is no " + color + " King on the board");
		return null;
	}
	
	private Coordinate getKingCoordinate(Color color) {
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				Piece piece = pieces[j][i];
				if (piece.name.equals(Constants.NAME_KING) && piece.getColor() == color)
					return new Coordinate(j, i);
			}
		}
		System.out.println("There is no " + color + " King on the board");
		return null;
	}
	
	public boolean isLegal(IMove iMove) {
		if (isGameOver())
			return false;
		if (iMove instanceof Move) {
			Move move = (Move)iMove;
			Piece startPiece = getPiece(move.getStart());
			if (startPiece.isNone())
				return false;
			else if (currentTurnColor != null && startPiece.getColor() != currentTurnColor)
				return false;
			else {
				Board boardCopy = deepCopy(this);
				if (startPiece.getLegalMoves(move.getStart(), this).contains(move.getEnd())) {
					boardCopy.placeMove(move);
					return !boardCopy.isInCheck(startPiece.getColor());
				}
			}
		}
		else if (iMove instanceof Resign) {
			return true;
		}
		return false;
	}
	
	private void placeMove(Move move) {
		Piece piece = pieces[move.getStart().getCol()][move.getStart().getRow()];
		placePiece(move.getStart(), new NonePiece());
		Piece takenPiece = pieces[move.getEnd().getCol()][move.getEnd().getRow()];
		placePiece(move.getEnd(), piece);
	}
	
	public Color getCurrentTurnColor() {
		return currentTurnColor;
	}
	
	private void setCheckmated(Color color) {
		if (color == Color.WHITE)
			game_over = GAME_OVER.BLACK_CHECKMATED;
		else
			game_over = GAME_OVER.WHITE_CHECKMATED;
	}
	
	public boolean execute(IMove move) {
		if (!isLegal(move))
			return false;
		if (move instanceof Move) {
			Move aMove = (Move)move;
			placeMove(aMove);
			
			if (currentTurnColor != null)
				currentTurnColor = currentTurnColor.getOpposite();
			if (isCheckmated(currentTurnColor)) {
				setCheckmated(currentTurnColor);
			}
			return true;
		}
		else if (move instanceof Resign) {
			if (currentTurnColor == Color.WHITE)
				game_over = GAME_OVER.WHITE_RESIGNS;
			else
				game_over = GAME_OVER.BLACK_RESIGNS;
			return true;
		}
		return false;
	}
	
	/**
	 * Is the given color checkmated
	 */
	public boolean isCheckmated(Color color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = getPiece(j, i);
				if (piece instanceof King && piece.getColor() == color) {
					return piece.getLegalMoves(new Coordinate(j, i), this).isEmpty() && 
							this.findThreatenedSquares(color).contains(new Coordinate(j, i));
						
				}
			}
		}
		return false;
	}

	public Set<Move> getAllLegalMoves() {
		Set<Move> legalMoves = new HashSet<Move>(); 
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				Coordinate startCoord = new Coordinate(i, j);
				Piece piece = getPiece(startCoord);
				if (!piece.isNone() && piece.getColor() == currentTurnColor) {
					legalMoves.addAll(getLegalMoves(startCoord));
				}
			}
		}
		return legalMoves;
	} 
	
	public static Set<Move> convertEndingCoordsToMoves(Coordinate startCoord, Set<Coordinate> endCoords) {
		Set<Move> moves = new HashSet<Move>(); 
		for (Coordinate endCoord : endCoords) {
			moves.add(new Move(startCoord, endCoord));
		}
		return moves;
	}
	
	public Board deepCopy(Board board) {
		Map<Coordinate, Piece> map = board.getPiecesMap();
		Board boardCopy = new Board(map);
		boardCopy.setCurrentTurnColor(this.currentTurnColor);
		return boardCopy;
	}
	
	public Set<Move> getLegalMoves(Coordinate startCoord) {
		return convertEndingCoordsToMoves(startCoord, getLegalEndCoords(startCoord.getCol(), startCoord.getRow()));
	}
	
	public Set<Move> getLegalMoves(int col, int row) {
		return convertEndingCoordsToMoves(new Coordinate(col, row), getLegalEndCoords(col, row));
	}
	
	/**
	 * return a list of all legal moves on the board for a given coordinate's piece
	 */
	public Set<Coordinate> getLegalEndCoords(int col, int row) {
		Coordinate startCoord = new Coordinate(col, row);
		Piece piece = getPiece(startCoord);
		if (!piece.isNone() && piece.getColor() == currentTurnColor) {
			Set<Coordinate> endCoords = piece.getLegalMoves(new Coordinate(col, row), this);
			Set<Coordinate> endCoordsNotInCheck = new HashSet<Coordinate>();
			for (Coordinate endCoord : endCoords) {
				/*if (this.getPiece(endCoord).isNone() || this.getPiece(endCoord).getColor() == piece.getColor())
					continue;*/
				Board deepCopyBoard = deepCopy(this);
				Move move = new Move(startCoord, endCoord);
				if (deepCopyBoard.execute(move) && !deepCopyBoard.isInCheck(piece.getColor())) {
					endCoordsNotInCheck.add(endCoord);
				}
			}
			return endCoordsNotInCheck;
		}
		else 
			return new HashSet<Coordinate>();
	}
	
	public Set<Coordinate> getLegalEndCoords(Coordinate coord) {
		return getLegalEndCoords(coord.getCol(), coord.getRow());
	}

	public Piece getPiece(Coordinate coord) {
		return getPiece(coord.getCol(), coord.getRow());
	}

	public Piece getPiece(int col, int row) {
		return pieces[col][row];
	}

	@Override
	public String toString() {
		String str = "Board:\n";
		for (int i = 0; i < NUMBER_OF_COLS; i++) {
			for (int j = 0; j < NUMBER_OF_ROWS; j++) {
				Piece piece = pieces[j][i];
				str += "(Square: " + new Coordinate(j, i).convertToNotation() + " Piece: " + piece + "\n";
			}
		}
		return str;
	}
	
	public boolean isEmpty(int col, int row) {
		if (contains(new Coordinate(col, row)))
			return pieces[col][row].isNone();
		return false;
	}
	
	public boolean contains(Coordinate coord) {
		return coord.getCol() >= 0 && coord.getCol() < NUMBER_OF_COLS &&
			   coord.getRow() >= 0 && coord.getRow() < NUMBER_OF_ROWS;
	}

	public void print() {
		System.out.println(this);
	}

	public static void main (String[] args) {
		Board board = new Board();
		board.print();
	}
	
	/**
	 * returns a set of coordinates that the opposite color threatens
	 * ie. if color == WHITE, then a set of coordinates which black threatens a square
	 */
	public Set<Coordinate> findThreatenedSquares(Color color) {
		Set<Coordinate> threatenedSquaresSet = new LinkedHashSet<Coordinate>();
		for (int col = 0; col < NUMBER_OF_COLS; col++) {
			for (int row = 0; row < NUMBER_OF_ROWS; row++) {
				Piece currPiece = pieces[col][row];
				if (currPiece.isNone())
					continue;
				else if (currPiece.getColor() != color) {
					Set<Coordinate> currThreatenedSquares = currPiece.threatens(new Coordinate(col, row), this);
					threatenedSquaresSet.addAll(currThreatenedSquares);
				}
			}
		}
		return threatenedSquaresSet;
	}
}

package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Rook extends Piece{
	public Rook(Color color) {
		super(Constants.NAME_ROOK, color);
	}
	
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		return getPossibleCoordinates(startingCoord, board, false);
	}
	
	public Set<Coordinate> threatens(Coordinate startingCoord, Board board) {
		return getPossibleCoordinates(startingCoord, board, true);
	}
	
	public static Set<Coordinate> getPossibleCoordinates(Coordinate startingCoord, Board board, boolean includeThreateningOwnPiece) {
		Set<Coordinate> possibleCoordinates = new LinkedHashSet<Coordinate>();
		
		Color startingColor = board.getPiece(startingCoord).getColor();
		// add entries to the left
		for (int i = startingCoord.getCol() - 1; i >= 0; i--) {
			Coordinate coord = new Coordinate(i, startingCoord.getRow());
			Piece piece = board.getPiece(coord);
			if (piece.isNone()) {
				possibleCoordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				possibleCoordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				possibleCoordinates.add(coord);
				break;
			}
		}
		
		// add entries to the right
		for (int i = startingCoord.getCol() + 1; i < 8; i++) {
			Coordinate coord = new Coordinate(i, startingCoord.getRow());
			Piece piece = board.getPiece(coord);
			if (board.getPiece(coord).isNone()) {
				possibleCoordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				possibleCoordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				possibleCoordinates.add(coord);
				break;
			}
		}
		
		// add entries up
		for (int i = startingCoord.getRow() + 1; i < 8; i++) {
			Coordinate coord = new Coordinate(startingCoord.getCol(), i);
			Piece piece = board.getPiece(coord);
			if (board.getPiece(coord).isNone()) {
				possibleCoordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				possibleCoordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				possibleCoordinates.add(coord);
				break;
			}
		}
		
		// add entries down
		for (int i = startingCoord.getRow() - 1; i >= 0; i--) {
			Coordinate coord = new Coordinate(startingCoord.getCol(), i);
			Piece piece = board.getPiece(coord);
			if (board.getPiece(coord).isNone()) {
				possibleCoordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				possibleCoordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				possibleCoordinates.add(coord);
				break;
			}
		}
		
		return possibleCoordinates;
	}
}

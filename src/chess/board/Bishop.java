package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Bishop extends Piece{
	public Bishop(Color color) {
		super(Constants.NAME_BISHOP, color);
	}
	
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		return getPossibleCoordinates(startingCoord, board, false);
	}
	
	public Set<Coordinate> threatens(Coordinate startingCoord, Board board) {
		return getPossibleCoordinates(startingCoord, board, true);
	}
	
	public static Set<Coordinate> getPossibleCoordinates(Coordinate startingCoord, Board board, boolean includeThreateningOwnPiece) {
		Set<Coordinate> coordinates = new LinkedHashSet<Coordinate>();
		Color startingColor = board.getPiece(startingCoord).getColor();
		
		// add entries to the top left
		for (int i = 1; i < 8; i++) {
			Coordinate coord = startingCoord.transform(-i, +i);
			if (!board.contains(coord))
				break;
			Piece piece = board.getPiece(coord);
			if (piece.isNone()) {
				coordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				coordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				coordinates.add(coord);
				break;
			}
		}
		
		// add entries to the top right
		for (int i = 1; i < 8; i++) {
			Coordinate coord = startingCoord.transform(+i, +i);
			if (!board.contains(coord))
				break;
			Piece piece = board.getPiece(coord);
			if (piece.isNone()) {
				coordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				coordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				coordinates.add(coord);
				break;
			}
		}
		
		// add entries to the bottom left
		for (int i = 1; i < 8; i++) {
			Coordinate coord = startingCoord.transform(-i, -i);
			if (!board.contains(coord))
				break;
			Piece piece = board.getPiece(coord);
			if (piece.isNone()) {
				coordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				coordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				coordinates.add(coord);
				break;
			}
		}
		
		// add entries to the bottom right
		for (int i = 1; i < 8; i++) {
			Coordinate coord = startingCoord.transform(+i, -i);
			if (!board.contains(coord))
				break;
			Piece piece = board.getPiece(coord);
			if (piece.isNone()) {
				coordinates.add(coord);
			}
			else if (includeThreateningOwnPiece && piece.getColor() == startingColor) {
				coordinates.add(coord);
				break;
			}
			else if (!includeThreateningOwnPiece && piece.getColor() == startingColor) {
				break;
			}
			else {
				coordinates.add(coord);
				break;
			}
		}
		return coordinates;
	}
}


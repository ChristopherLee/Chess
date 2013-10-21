package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Queen extends Piece{
	public Queen(Color color) {
		super(Constants.NAME_QUEEN, color);
	}
	
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		return getPossibleCoordinates(startingCoord, board, false);
	}
	
	public Set<Coordinate> threatens(Coordinate startingCoord, Board board) {
		return getPossibleCoordinates(startingCoord, board, true);
	}
	
	public Set<Coordinate> getPossibleCoordinates(Coordinate startingCoord, Board board, boolean includeThreateningOwnPiece) {
		Set<Coordinate> coordinates = new LinkedHashSet<Coordinate>();
		coordinates.addAll(Rook.getPossibleCoordinates(startingCoord, board, includeThreateningOwnPiece));
		coordinates.addAll(Bishop.getPossibleCoordinates(startingCoord, board, includeThreateningOwnPiece));
		
		return coordinates;
	}
}


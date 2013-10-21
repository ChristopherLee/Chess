package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece{
	public Pawn(Color color) {
		super(Constants.NAME_PAWN, color);
	}
	
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		// three types of legal moves:
		//	-take diagonally (if opposing color piece is present)
		//	-move up one
		//	-move up two (in starting position)
		//	-ampasant - dont handle this yet
		Set<Coordinate> legalLocations = new LinkedHashSet<Coordinate> ();
		
		Set<Coordinate> possibleTakes = possibleTakes(startingCoord);
		for (Coordinate coord : possibleTakes) {
			if (board.contains(coord) && 
				!board.isEmpty(coord.getCol(), coord.getRow()) &&
				 board.getPiece(coord).color != color)
				legalLocations.add(coord);
		}
				
		Set<Coordinate> possibleMoves = possibleMoves(startingCoord);
		for (Coordinate coord : possibleMoves) {
			if (board.contains(coord) &&
				board.isEmpty(coord.getCol(), coord.getRow()))
				legalLocations.add(coord);
			else
				break;
		}
		return legalLocations;
	}
	
	private Set<Coordinate> possibleTakes(Coordinate startingCoord) {
		Set<Coordinate> coords = new LinkedHashSet<Coordinate>();
		if (color == Color.WHITE) {
			coords.add(startingCoord.transform(-1, 1));
			coords.add(startingCoord.transform(1, 1));
		}
		else {
			coords.add(startingCoord.transform(-1, -1));
			coords.add(startingCoord.transform(1, -1));
		}
		return coords;
	}
	
	private Set<Coordinate> possibleMoves(Coordinate startingCoord) {
		Set<Coordinate> coords = new LinkedHashSet<Coordinate>();
		if (color == Color.WHITE) {
			coords.add(startingCoord.transform(0, 1));
			if (startingCoord.getRow() == Constants.INITIAL_WHITE_PAWN_RANK)
				coords.add(startingCoord.transform(0, 2));
		}
		else {
			coords.add(startingCoord.transform(0, -1));
			if (startingCoord.getRow() == Constants.INITIAL_BLACK_PAWN_RANK)
				coords.add(startingCoord.transform(0, -2));
		}	
		return coords;
	}

	@Override
	public Set<Coordinate> threatens(Coordinate startingCoord, Board board) {
		return possibleTakes(startingCoord);
	}
}


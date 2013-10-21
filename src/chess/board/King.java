package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class King extends Piece{
	public King(Color color) {
		super(Constants.NAME_KING, color);
	}
	
	public Set<Coordinate> threatens(Coordinate startingCoord, Board board) {
		return possibleMoves(startingCoord);
	}
	
	/**
	 * A king can only move one square in any direction (including diagonally), and cannot
	 * move to a square where it will be threatened 
	 */
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		Set<Coordinate> legalMoves = new LinkedHashSet<Coordinate>();
		Set<Coordinate> possibleMoves = possibleMoves(startingCoord);
		Set<Coordinate> threatenedSquares = board.findThreatenedSquares(color);
		for (Coordinate endCoord : possibleMoves) {
			if (!board.contains(endCoord))
				continue;
			Piece endPiece = board.getPiece(endCoord);
			if (endPiece.isNone() && !threatenedSquares.contains(endCoord))
				legalMoves.add(endCoord);
			else if (!endPiece.isNone() && 
					 endPiece.getColor() != color &&
					 !threatenedSquares.contains(endCoord))
				legalMoves.add(endCoord);
				
			/*Board copyBoard = board.deepCopy(board);
			if (copyBoard.execute(new Move(startingCoord, endCoord)) && 
				!copyBoard.isInCheck(copyBoard.getCurrentTurnColor()))
				legalMoves.add(endCoord);*/
		}
		return legalMoves;
	}
	
	private Set<Coordinate> possibleMoves(Coordinate startingCoord) {
		LinkedHashSet<Coordinate> coords = new LinkedHashSet<Coordinate>();
		coords.add(startingCoord.transform(0, 1));
		coords.add(startingCoord.transform(1, 1));
		coords.add(startingCoord.transform(1, 0));
		coords.add(startingCoord.transform(1, -1));
		coords.add(startingCoord.transform(0, -1));
		coords.add(startingCoord.transform(-1, -1));
		coords.add(startingCoord.transform(-1, 0));
		coords.add(startingCoord.transform(-1, 1));
		return coords;
	}
}


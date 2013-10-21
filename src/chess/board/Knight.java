package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Knight extends Piece{
	public Knight(Color color) {
		super(Constants.NAME_KNIGHT, color);
	}
	
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		Set<Coordinate> legalMoves = new LinkedHashSet<Coordinate>();
		Color color = board.getPiece(startingCoord).getColor();
		int[] deltaX = {1, 2, 2, 1, -1, -2, -2, -1};
		int[] deltaY = {2, 1, -1, -2, -2, -1, 1, 2};
		
		for (int i = 0; i < deltaX.length; i++) {
			Coordinate coord = startingCoord.transform(deltaX[i], deltaY[i]);
			if (board.contains(coord) && 
				(board.getPiece(coord).isNone() || board.getPiece(coord).getColor() != color))
				legalMoves.add(coord);
		}
		return legalMoves;
	}
}


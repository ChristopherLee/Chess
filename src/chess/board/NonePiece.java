package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class NonePiece extends Piece{
	public NonePiece() {
		super(Constants.NAME_NONE, null);
	}
	@Override
	public Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board) {
		throw new RuntimeException("Cannot get threatens for a NonePiece");
	}
	
	@Override
	public Color getColor() {
		throw new RuntimeException("Cannot get color of NonePiece");
	}
	
	@Override
	public boolean isNone() {
		return true;
	}
}

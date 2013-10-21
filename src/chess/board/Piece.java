package chess.board;

import java.util.Set;

public abstract class Piece {
	protected final String name;
	protected final Color color; 
	public Piece(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	public abstract Set<Coordinate> getLegalMoves(Coordinate startingCoord, Board board);
	
	public Set<Coordinate> threatens(Coordinate startingCoord, Board board) {
		return getLegalMoves(startingCoord, board);
	}
	
	public String toString() {
		return color + "-" + name;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public boolean isNone() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Piece) {
			Piece otherPiece = (Piece)obj;
			return this.name.equals(otherPiece.name) &&
				   this.color == otherPiece.color;
		}
		return false;
	}
	
}

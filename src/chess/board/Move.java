package chess.board;

/**
 * represents a move.  This may not be a valid move because there may not be a piece at the starting location, 
 * and the piece at the starting position may not be able to move to the end coordinate
 */
public class Move implements IMove{
	private final Coordinate start;
	private final Coordinate end;
	
	public Move(String start, String end) throws Exception{
		this(new Coordinate(start), new Coordinate(end));
	}
	
	public Move(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
	}
	
	public Coordinate getStart() {
		return start;
	}
	public Coordinate getEnd() {
		return end;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Move) {
			Move otherMove = (Move)obj;
			return this.start.equals(otherMove.start) &&
				   this.end.equals(otherMove.end);
		}
		return false;
	}
	
	@Override 
	public int hashCode() {
        return (41 * (41 + start.hashCode()) + end.hashCode());
    }
	
	public String toString() {
		return "Move: " + start + " to " + end;
	}
	
}

package chess.board;

/**
 * represents a square on the board
 */
public class Coordinate {
	private final int row;
	private final int col;
	public Coordinate(int col, int row) {
		this.row = row;
		this.col = col;
	}
	
	public Coordinate(String notation) throws Exception {
		int col = convertCharToIndex(notation.charAt(0));
		if (col == -1)
			throw new Exception("Invalid coordinate: " + notation);
		this.col = col;
		int row = Integer.parseInt(notation.substring(1, 2)) - 1;
		if (row < 0 || row > 7)
			throw new Exception("Invalid coordinate: " + notation);
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public Coordinate transform(int deltaCol, int deltaRow) {
		return new Coordinate(col + deltaCol, row + deltaRow);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate otherCoord = (Coordinate)obj;
			return this.row == otherCoord.row &&
				   this.col == otherCoord.col;
		}
		return false;
	}
	
	@Override 
	public int hashCode() {
        return (41 * (41 + row) + col);
    }
	
	private static int convertCharToIndex (char c) {
		int col;
		switch(c) {
		case 'A': 
			col=0;
			break;
		case 'B': 
			col=1;
			break;
		case 'C': 
			col=2;
			break;
		case 'D': 
			col=3;
			break;
		case 'E': 
			col=4;
			break;
		case 'F': 
			col=5;
			break;
		case 'G': 
			col=6;
			break;
		case 'H': 
			col=7;
			break;
		default:
			col=-1;
		}
		return col;
	}
	
	public static String convertIndexToLetter (int index) {
		String letter;
		switch(index) {
		case 0: 
			letter="A";
			break;
		case 1: 
			letter="B";
			break;
		case 2: 
			letter="C";
			break;
		case 3: 
			letter="D";
			break;
		case 4: 
			letter="E";
			break;
		case 5: 
			letter="F";
			break;
		case 6: 
			letter="G";
			break;
		case 7: 
			letter="H";
			break;
		default:
			letter="";
		}
		return letter;
	}
		
	public String convertToNotation() {
		String letter = "";
		switch(col) {
		case 0: 
			letter="A";
			break;
		case 1: 
			letter="B";
			break;
		case 2: 
			letter="C";
			break;
		case 3: 
			letter="D";
			break;
		case 4: 
			letter="E";
			break;
		case 5: 
			letter="F";
			break;
		case 6:
			letter="G";
			break;
		case 7: 
			letter="H";
			break;
		}
		return letter + (row + 1);
	}
	
	@Override
	public String toString() {
		return convertToNotation();
	}
}

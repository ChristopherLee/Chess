package chess.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chess.board.Board;
import chess.board.Coordinate;
import chess.board.Move;

public class HumanPlayer implements IPlayer{

	@Override
	public Move getMove(Board board) {
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	    BufferedReader reader = new BufferedReader(inputStreamReader);

	    try {
	    	System.out.print("Starting coord: ");
	    	String s1 = reader.readLine();
	    	Coordinate startingCoord = new Coordinate(s1);
	    	
	    	System.out.print("Ending coord: ");
	    	String s2 = reader.readLine();
	    	Coordinate endingCoord = new Coordinate(s2);
	    	
	    	Move move = new Move(startingCoord, endingCoord);
	    	return move;
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception e) {
	    	return getMove(board);
	    }
	    return null;
	}

}

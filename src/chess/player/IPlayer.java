package chess.player;

import chess.board.Board;
import chess.board.Move;

public interface IPlayer {
	
	public Move getMove(Board board);
}

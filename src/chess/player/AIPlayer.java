package chess.player;

import java.util.Set;

import chess.board.Board;
import chess.board.Color;
import chess.board.Coordinate;
import chess.board.Move;
import chess.board.Piece;

public class AIPlayer implements IPlayer{
	@Override
	public Move getMove(Board board) {
		Set<Move> legalMoves = board.getAllLegalMoves();
		if (legalMoves.isEmpty()) {
			return null;
		}
		else
			return legalMoves.iterator().next();
	}

}

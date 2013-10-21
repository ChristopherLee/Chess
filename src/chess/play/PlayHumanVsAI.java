package chess.play;

import javax.swing.JFrame;

import chess.board.Board;
import chess.board.Color;
import chess.board.Move;
import chess.gui.BoardGUI;
import chess.gui.HumanBoardGUI;
import chess.player.AIPlayer;
import chess.player.HumanPlayer;
import chess.player.IPlayer;

public class PlayHumanVsAI {
	public static void main(String[] args) throws Exception{
		Board board = new Board();
		IPlayer humanPlayer = new HumanPlayer();
		IPlayer AIPlayer = new AIPlayer();
		IPlayer currentPlayer = humanPlayer;
		while (!board.isGameOver()) {
			if (humanPlayer == currentPlayer) {
				HumanBoardGUI gui = new HumanBoardGUI(board);
				JFrame frame = BoardGUI.addToFrame(gui);
				Move move = gui.getHumanMove();
				boolean moveSuccessful = board.execute(move);
				if (moveSuccessful)
					currentPlayer = AIPlayer;
				else
					System.out.println("Human made bad move");
				frame.setVisible(false);
			}
			else {
				Move move = AIPlayer.getMove(board);
				boolean moveSuccessful = board.execute(move);
				if (moveSuccessful)
					currentPlayer = humanPlayer;
				else
					System.out.println("AIPlayer made bad move: " + move);
			}
		}
	}
}

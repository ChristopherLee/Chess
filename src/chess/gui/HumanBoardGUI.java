package chess.gui;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import chess.board.Board;
import chess.board.Coordinate;
import chess.board.Move;

public class HumanBoardGUI extends BoardGUI implements MouseListener{
	private boolean enabled;
	private Move move;
	
	public HumanBoardGUI(Board board) {
		super(board);
		this.enabled = false;
		addMouseListener(this);
	}
	
	public Move getHumanMove() throws InterruptedException{
		this.enabled = true;
		synchronized(this) {
			this.wait();
		}
		return this.move;
	}

	private void paintSelectedPieceBorder(Graphics graphics) {
		if (selectedSquare != null) {
			Point topLeftPoint = super.getTopLeftPointOfSquare(selectedSquare);
			graphics.setColor(SELECTED_SQUARE_COLOR);
			graphics.drawRect((int)topLeftPoint.getX(), (int)topLeftPoint.getY(), SIZE_OF_SQUARE, SIZE_OF_SQUARE);
			graphics.drawRect((int)topLeftPoint.getX()-1, (int)topLeftPoint.getY()-1, SIZE_OF_SQUARE + 2, SIZE_OF_SQUARE + 2);
			graphics.drawRect((int)topLeftPoint.getX()-2, (int)topLeftPoint.getY()-2, SIZE_OF_SQUARE + 4, SIZE_OF_SQUARE + 4);
		}
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		paintSelectedPieceBorder(graphics);
	}
		
	private Coordinate getCoordinate(Point point) {
		int xCoordinate = (int)(point.getX() - STARTING_X_INDEX)/SIZE_OF_SQUARE;
		int yCoordinate = (int)(Board.NUMBER_OF_ROWS - ((point.getY() - STARTING_Y_INDEX)/SIZE_OF_SQUARE));
		System.out.println("getCoordinate: x:" + xCoordinate + " y: " + yCoordinate);
		if (xCoordinate >= 0 && xCoordinate <= 7 &&
			yCoordinate >= 0 && yCoordinate <= 7)
			return new Coordinate(xCoordinate, yCoordinate);
		else
			return null;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Mouse clicked: " + arg0.getPoint());
		if (this.enabled && arg0.getButton() == MouseEvent.BUTTON1) {
			Coordinate clickedSquare = getCoordinate(arg0.getPoint());
			System.out.println("Square clicked: " + clickedSquare);
			if (selectedSquare == null) {
				if (clickedSquare == null || board.getPiece(clickedSquare).isNone()) {
					// do nothing
				}
				else {
					if (!board.getLegalMoves(clickedSquare).isEmpty())
						highlightSquare(clickedSquare);
				}
			}
			else {
				if (clickedSquare == null)
					unhighlightSquare();
				else if (board.getPiece(clickedSquare).isNone()) {
					Move move = new Move(selectedSquare, clickedSquare);
					if (board.isLegal(move)) {
						this.move = move;
						synchronized(this) {
							this.notify();
						}
						unhighlightSquare();
					}
					else
						unhighlightSquare();
				}
				else {
					Move move = new Move(selectedSquare, clickedSquare);
					if (board.isLegal(move)) {
						this.move = move;
						synchronized(this) {
							this.notify();
						}
						unhighlightSquare();
					}
					else if (!board.getLegalMoves(clickedSquare).isEmpty())
						highlightSquare(clickedSquare);
					else
						unhighlightSquare();
				}
			}
			/*
			if (clickedSquare == null || 
				clickedSquare.equals(selectedSquare))
				selectedSquare = null;
			else if (selectedSquare == null && board.getPiece(clickedSquare).isNone())
				selectedSquare = null;
			else if (selectedSquare == null || !board.getLegalMoves(clickedSquare).isEmpty()) {
				selectedSquare = clickedSquare;
			}
			else {
				Move move = new Move(selectedSquare, clickedSquare);
				System.out.println("isLegal: " + move);
				if (board.isLegal(move)) {
					board.execute(move);
					selectedSquare = null;
				}
				else
					selectedSquare = clickedSquare;
			}*/
			repaint();
		}
	}
	
	private void highlightSquare(Coordinate coord) {
		selectedSquare = coord;
	}
	
	private void unhighlightSquare() {
		selectedSquare = null;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}
}

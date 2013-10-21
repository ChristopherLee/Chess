package chess.board.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chess.board.Bishop;
import chess.board.Board;
import chess.board.Color;
import chess.board.Constants;
import chess.board.Coordinate;
import chess.board.King;
import chess.board.Knight;
import chess.board.Move;
import chess.board.Pawn;
import chess.board.Piece;
import chess.board.Queen;
import chess.board.Rook;
import junit.framework.TestCase;

public class BoardTest extends TestCase{
	Board startingBoard;
	Board boardWithE4E5;
	Board boardWithKingAtE4;
	Board boardWithKingAtE4AndPawnAtE2;
	Board boardWithKingAtE4AndKingAtE2;
	Board boardWithKingAtE4AndPawnAtE6Alt;
	Board boardWithKingBlockingPawn;
	Board boardWithPawnTakePawn;
	Board boardWithPawnTakePawnEdge;
	Board boardWithPawnTakePawnSameColor;
	Board boardWithRookAtE4;
	@Override
	protected void setUp() throws Exception {
		startingBoard = new Board();
		boardWithE4E5 = new Board();
		boardWithE4E5.execute(new Move(new Coordinate("E2"), new Coordinate("E4")));
		boardWithE4E5.execute(new Move(new Coordinate("E7"), new Coordinate("E5")));
		
		Map<Coordinate, Piece> kingAtE4Map = new HashMap<Coordinate, Piece>();
		kingAtE4Map.put(new Coordinate("E4"), new King(Color.WHITE));
		boardWithKingAtE4 = new Board(kingAtE4Map);
		
		Map<Coordinate, Piece> kingAtE4AndPawnAtE2Map = new HashMap<Coordinate, Piece>();
		kingAtE4AndPawnAtE2Map.put(new Coordinate("E4"), new King(Color.BLACK));
		kingAtE4AndPawnAtE2Map.put(new Coordinate("E2"), new Pawn(Color.WHITE));
		boardWithKingAtE4AndPawnAtE2 = new Board(kingAtE4AndPawnAtE2Map);
		
		Map<Coordinate, Piece> kingAtE4AndPawnAtE6MapAlt = new HashMap<Coordinate, Piece>();
		kingAtE4AndPawnAtE6MapAlt.put(new Coordinate("E4"), new King(Color.WHITE));
		kingAtE4AndPawnAtE6MapAlt.put(new Coordinate("E6"), new Pawn(Color.BLACK));
		boardWithKingAtE4AndPawnAtE6Alt = new Board(kingAtE4AndPawnAtE6MapAlt);
		
		Map<Coordinate, Piece> kingAtE4AndKingAtE2Map = new HashMap<Coordinate, Piece>();
		kingAtE4AndKingAtE2Map.put(new Coordinate("E4"), new King(Color.WHITE));
		kingAtE4AndKingAtE2Map.put(new Coordinate("E2"), new King(Color.BLACK));
		boardWithKingAtE4AndKingAtE2 = new Board(kingAtE4AndKingAtE2Map);
		
		Map<Coordinate, Piece> pawnTakePawnMap = new HashMap<Coordinate, Piece>();
		pawnTakePawnMap.put(new Coordinate("E2"), new Pawn(Color.WHITE));
		pawnTakePawnMap.put(new Coordinate("D3"), new Pawn(Color.BLACK));
		boardWithPawnTakePawn = new Board(pawnTakePawnMap);
		
		Map<Coordinate, Piece> pawnTakePawnSameColorMap = new HashMap<Coordinate, Piece>();
		pawnTakePawnSameColorMap.put(new Coordinate("E2"), new Pawn(Color.WHITE));
		pawnTakePawnSameColorMap.put(new Coordinate("D3"), new Pawn(Color.WHITE));
		boardWithPawnTakePawnSameColor = new Board(pawnTakePawnSameColorMap);
		
		Map<Coordinate, Piece> pawnTakePawnEdgeMap = new HashMap<Coordinate, Piece>();
		pawnTakePawnEdgeMap.put(new Coordinate("A6"), new Pawn(Color.BLACK));
		pawnTakePawnEdgeMap.put(new Coordinate("B4"), new Pawn(Color.WHITE));
		boardWithPawnTakePawnEdge = new Board(pawnTakePawnEdgeMap);
		boardWithPawnTakePawnEdge.execute(new Move(new Coordinate("B4"), new Coordinate("B5")));
		
		Map<Coordinate, Piece> kingBlockingPawnMap = new HashMap<Coordinate, Piece>();
		kingBlockingPawnMap.put(new Coordinate("E4"), new King(Color.WHITE));
		kingBlockingPawnMap.put(new Coordinate("E2"), new Pawn(Color.WHITE));
		boardWithKingBlockingPawn = new Board(kingBlockingPawnMap);
		
		Map<Coordinate, Piece> rookAtE4Map = new HashMap<Coordinate, Piece>();
		rookAtE4Map.put(new Coordinate("E4"), new Rook(Color.WHITE));
		boardWithRookAtE4 = new Board(rookAtE4Map);
	}

	@Override
	protected void tearDown() throws Exception {

	}
	
	public void testInitialStartingPieces() {
		assertEquals(startingBoard.getPiece(0, 0), new Rook(Color.WHITE));
		assertEquals(startingBoard.getPiece(1, 0), new Knight(Color.WHITE));
		assertEquals(startingBoard.getPiece(2, 0), new Bishop(Color.WHITE));
		assertEquals(startingBoard.getPiece(3, 0), new Queen(Color.WHITE));
		assertEquals(startingBoard.getPiece(4, 0), new King(Color.WHITE));
		assertEquals(startingBoard.getPiece(5, 0), new Bishop(Color.WHITE));
		assertEquals(startingBoard.getPiece(6, 0), new Knight(Color.WHITE));
		assertEquals(startingBoard.getPiece(7, 0), new Rook(Color.WHITE));
		
		assertEquals(startingBoard.getPiece(0, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(1, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(2, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(3, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(4, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(5, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(6, 1), new Pawn(Color.WHITE));
		assertEquals(startingBoard.getPiece(7, 1), new Pawn(Color.WHITE));
		
		assertEquals(startingBoard.getPiece(0, 7), new Rook(Color.BLACK));
		assertEquals(startingBoard.getPiece(1, 7), new Knight(Color.BLACK));
		assertEquals(startingBoard.getPiece(2, 7), new Bishop(Color.BLACK));
		assertEquals(startingBoard.getPiece(3, 7), new Queen(Color.BLACK));
		assertEquals(startingBoard.getPiece(4, 7), new King(Color.BLACK));
		assertEquals(startingBoard.getPiece(5, 7), new Bishop(Color.BLACK));
		assertEquals(startingBoard.getPiece(6, 7), new Knight(Color.BLACK));
		assertEquals(startingBoard.getPiece(7, 7), new Rook(Color.BLACK));
		
		assertEquals(startingBoard.getPiece(0, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(1, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(2, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(3, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(4, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(5, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(6, 6), new Pawn(Color.BLACK));
		assertEquals(startingBoard.getPiece(7, 6), new Pawn(Color.BLACK));
	}
	
	public void testBoardContains() {
		assertTrue(startingBoard.contains(new Coordinate(0, 0)));
		assertTrue(startingBoard.contains(new Coordinate(7, 7)));
		assertTrue(startingBoard.contains(new Coordinate(0, 7)));
		assertTrue(startingBoard.contains(new Coordinate(7, 0)));
		assertFalse(startingBoard.contains(new Coordinate(-1, 0)));
		assertFalse(startingBoard.contains(new Coordinate(0, 8)));
	}
	
	public void testBoardIsEmpty() {
		assertFalse(startingBoard.isEmpty(0, 0));
		assertFalse(startingBoard.isEmpty(0, 1));
		assertTrue(startingBoard.isEmpty(0, 2));
	}
	
	public void testPawnGetLegalMoves() throws Exception{
		Coordinate startCoord = new Coordinate("A2");
		Coordinate endCoord = new Coordinate("A3");
		assertTrue(startingBoard.getPiece(startCoord).getLegalMoves(startCoord, startingBoard).contains(endCoord));
	}
	
	public void testPawnGetLegalMoves2() throws Exception{
		Coordinate startCoord = new Coordinate("A7");
		Coordinate endCoord = new Coordinate("A5");
		assertTrue(startingBoard.getPiece(startCoord).getLegalMoves(startCoord, startingBoard).contains(endCoord));
	}
	
	public void testExecuteWhitePawnMove1() throws Exception{
		Coordinate startCoord = new Coordinate("A2");
		Coordinate endCoord = new Coordinate("A3");
		assertTrue(startingBoard.execute(new Move(startCoord, endCoord)));
		assertTrue(startingBoard.getPiece(startCoord).isNone());
		assertTrue(startingBoard.getPiece(endCoord).equals(new Pawn(Color.WHITE)));
	}
	
	public void testExecuteWhitePawnMove2() throws Exception{
		Coordinate startCoord = new Coordinate("A2");
		Coordinate endCoord = new Coordinate("A4");
		assertTrue(startingBoard.execute(new Move(startCoord, endCoord)));
		assertTrue(startingBoard.getPiece(startCoord).isNone());
		assertTrue(startingBoard.getPiece(endCoord).equals(new Pawn(Color.WHITE)));
	}
	
	public void testExecuteWhitePawnMove3() {
		Coordinate startCoord = new Coordinate(0, 1);
		Coordinate endCoord = new Coordinate(0, 4);
		assertFalse(startingBoard.execute(new Move(startCoord, endCoord)));
	}
	
	public void testExecuteWhitePawnTake1() {
		Coordinate startCoord = new Coordinate(0, 1);
		Coordinate endCoord = new Coordinate(1, 2);
		assertFalse(startingBoard.execute(new Move(startCoord, endCoord)));
	}
	
	public void testExecuteBlackPawnMove2() throws Exception{
		Coordinate startCoord = new Coordinate("A2");
		Coordinate endCoord = new Coordinate("A3");
		assertTrue(startingBoard.execute(new Move(startCoord, endCoord)));
		Coordinate startCoord2 = new Coordinate("H7");
		Coordinate endCoord2 = new Coordinate("H5");
		assertTrue(startingBoard.execute(new Move(startCoord2, endCoord2)));
		assertTrue(startingBoard.getPiece(startCoord2).isNone());
		assertTrue(startingBoard.getPiece(endCoord2).equals(new Pawn(Color.BLACK)));
	}
	
	public void testExecuteBlackPawnMove1() throws Exception{
		Coordinate startCoord = new Coordinate("A2");
		Coordinate endCoord = new Coordinate("A3");
		assertTrue(startingBoard.execute(new Move(startCoord, endCoord)));
		Coordinate startCoord2 = new Coordinate("G7");
		Coordinate endCoord2 = new Coordinate("G6");
		assertTrue(startingBoard.execute(new Move(startCoord2, endCoord2)));
		assertTrue(startingBoard.getPiece(startCoord2).isNone());
		assertTrue(startingBoard.getPiece(endCoord2).equals(new Pawn(Color.BLACK)));
	}
	
	public void testExecutePawnMove7() throws Exception{
		Coordinate startCoord = new Coordinate("E2");
		Coordinate endCoord = new Coordinate("E4");
		assertFalse(boardWithKingAtE4AndPawnAtE2.execute(new Move(startCoord, endCoord)));
	}
	
	public void testExecutePawnMove8() throws Exception{
		Coordinate startCoord = new Coordinate("E2");
		Coordinate endCoord = new Coordinate("E4");
		assertFalse(boardWithKingBlockingPawn.execute(new Move(startCoord, endCoord)));
	}
	
	public void testExecutePawnMove9() throws Exception{
		Coordinate startCoord = new Coordinate("E2");
		Coordinate endCoord = new Coordinate("F3");
		Coordinate endCoord2 = new Coordinate("D3");
		assertFalse(boardWithPawnTakePawn.execute(new Move(startCoord, endCoord)));
		assertTrue(boardWithPawnTakePawn.execute(new Move(startCoord, endCoord2)));
	}
	
	public void testExecutePawnMove10() throws Exception{
		Coordinate startCoord = new Coordinate("A6");
		Coordinate endCoord = new Coordinate("B5");
		assertTrue(boardWithPawnTakePawnEdge.execute(new Move(startCoord, endCoord)));
	}
	
	public void testExecutePawnMove11() throws Exception{
		Coordinate startCoord = new Coordinate("E2");
		Coordinate endCoord = new Coordinate("D3");
		assertFalse(boardWithPawnTakePawnSameColor.execute(new Move(startCoord, endCoord)));
	}
	
	
	public void testKingThreatened1() throws Exception{
		Set<Coordinate> expected = new LinkedHashSet<Coordinate>();
		assertEquals(expected, boardWithKingAtE4.findThreatenedSquares(Color.WHITE));
	}
	
	public void testKingThreatened2() throws Exception{
		Set<Coordinate> expected = new LinkedHashSet<Coordinate>();
		expected.add(new Coordinate("D3"));
		expected.add(new Coordinate("F3"));
		Set<Coordinate> actual = boardWithKingAtE4AndPawnAtE2.findThreatenedSquares(Color.BLACK);
		assertEquals(expected, actual);
		
	}
	
	public void testLegalKingMove1() throws Exception{
		Set<Move> expected = new LinkedHashSet<Move>();
		Coordinate startCoord = new Coordinate("E4");
		expected.add(new Move(startCoord, new Coordinate("E5")));
		expected.add(new Move(startCoord, new Coordinate("F5")));
		expected.add(new Move(startCoord, new Coordinate("F4")));
		expected.add(new Move(startCoord, new Coordinate("F3")));
		expected.add(new Move(startCoord, new Coordinate("E3")));
		expected.add(new Move(startCoord, new Coordinate("D3")));
		expected.add(new Move(startCoord, new Coordinate("D4")));
		expected.add(new Move(startCoord, new Coordinate("D5")));
		
		assertEquals(expected, boardWithKingAtE4.getLegalMoves(startCoord));
	}
	
	public void testLegalKingMove2() throws Exception{
		Set<Move> expected = new LinkedHashSet<Move>();
		Coordinate startCoord = new Coordinate("E4");
		expected.add(new Move(startCoord, new Coordinate("E5")));
		expected.add(new Move(startCoord, new Coordinate("F4")));
		expected.add(new Move(startCoord, new Coordinate("F3")));
		expected.add(new Move(startCoord, new Coordinate("E3")));
		expected.add(new Move(startCoord, new Coordinate("D3")));
		expected.add(new Move(startCoord, new Coordinate("D4")));
		
		assertEquals(expected, boardWithKingAtE4AndPawnAtE6Alt.getLegalMoves(startCoord));
	}	
	
	public void testLegalKingMove3() throws Exception{
		Set<Move> expected = new LinkedHashSet<Move>();
		Coordinate startCoord = new Coordinate("E4");
		expected.add(new Move(startCoord, new Coordinate("E5")));
		expected.add(new Move(startCoord, new Coordinate("F5")));
		expected.add(new Move(startCoord, new Coordinate("D5")));
		expected.add(new Move(startCoord, new Coordinate("D4")));
		expected.add(new Move(startCoord, new Coordinate("F4")));
		
		assertEquals(expected, boardWithKingAtE4AndKingAtE2.getLegalMoves(new Coordinate("E4")));
	}	
	
	public void testExecuteKingMove1() throws Exception{
		assertFalse(boardWithE4E5.execute(new Move(new Coordinate("E1"), new Coordinate("D1"))));
		assertFalse(boardWithE4E5.execute(new Move(new Coordinate("E1"), new Coordinate("F1"))));
		assertFalse(boardWithE4E5.execute(new Move(new Coordinate("E1"), new Coordinate("D2"))));
		assertFalse(boardWithE4E5.execute(new Move(new Coordinate("E1"), new Coordinate("F1"))));
		assertTrue(boardWithE4E5.execute(new Move(new Coordinate("E1"), new Coordinate("E2"))));
		assertTrue(boardWithE4E5.execute(new Move(new Coordinate("E8"), new Coordinate("E7"))));
		assertTrue(boardWithE4E5.execute(new Move(new Coordinate("E2"), new Coordinate("D3"))));
	}	
	
	public void testLegalRookMove1() throws Exception{
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("E5"));
		expectedCoords.add(new Coordinate("E6"));
		expectedCoords.add(new Coordinate("E7"));
		expectedCoords.add(new Coordinate("E8"));
		expectedCoords.add(new Coordinate("E3"));
		expectedCoords.add(new Coordinate("E2"));
		expectedCoords.add(new Coordinate("E1"));
		expectedCoords.add(new Coordinate("D4"));
		expectedCoords.add(new Coordinate("C4"));
		expectedCoords.add(new Coordinate("B4"));
		expectedCoords.add(new Coordinate("A4"));
		expectedCoords.add(new Coordinate("F4"));
		expectedCoords.add(new Coordinate("G4"));
		expectedCoords.add(new Coordinate("H4"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, boardWithRookAtE4.getLegalMoves(startCoord));
	}
	
	public void testLegalRookMove2() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new Rook(Color.WHITE));
		map.put(new Coordinate("E2"), new Rook(Color.WHITE));
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("E5"));
		expectedCoords.add(new Coordinate("E6"));
		expectedCoords.add(new Coordinate("E7"));
		expectedCoords.add(new Coordinate("E8"));
		expectedCoords.add(new Coordinate("E3"));
		expectedCoords.add(new Coordinate("D4"));
		expectedCoords.add(new Coordinate("C4"));
		expectedCoords.add(new Coordinate("B4"));
		expectedCoords.add(new Coordinate("A4"));
		expectedCoords.add(new Coordinate("F4"));
		expectedCoords.add(new Coordinate("G4"));
		expectedCoords.add(new Coordinate("H4"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testLegalRookMove3() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new Rook(Color.WHITE));
		map.put(new Coordinate("E2"), new Rook(Color.BLACK));
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("E5"));
		expectedCoords.add(new Coordinate("E6"));
		expectedCoords.add(new Coordinate("E7"));
		expectedCoords.add(new Coordinate("E8"));
		expectedCoords.add(new Coordinate("E3"));
		expectedCoords.add(new Coordinate("E2"));
		expectedCoords.add(new Coordinate("D4"));
		expectedCoords.add(new Coordinate("C4"));
		expectedCoords.add(new Coordinate("B4"));
		expectedCoords.add(new Coordinate("A4"));
		expectedCoords.add(new Coordinate("F4"));
		expectedCoords.add(new Coordinate("G4"));
		expectedCoords.add(new Coordinate("H4"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testLegalKnightMove1() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new Knight(Color.WHITE));		
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("D6"));
		expectedCoords.add(new Coordinate("F6"));
		expectedCoords.add(new Coordinate("G5"));
		expectedCoords.add(new Coordinate("G3"));
		expectedCoords.add(new Coordinate("F2"));
		expectedCoords.add(new Coordinate("D2"));
		expectedCoords.add(new Coordinate("C3"));
		expectedCoords.add(new Coordinate("C5"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testLegalKnightMove2() throws Exception{	
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("B1"), new Knight(Color.WHITE));		
		map.put(new Coordinate("A3"), new Pawn(Color.BLACK));
		map.put(new Coordinate("C3"), new Rook(Color.WHITE));
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("A3"));
		expectedCoords.add(new Coordinate("D2"));
		
		Coordinate startCoord = new Coordinate("B1");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testLegalBishopMove1() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new Bishop(Color.WHITE));		
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("D5"));
		expectedCoords.add(new Coordinate("C6"));
		expectedCoords.add(new Coordinate("B7"));
		expectedCoords.add(new Coordinate("A8"));
		expectedCoords.add(new Coordinate("F5"));
		expectedCoords.add(new Coordinate("G6"));
		expectedCoords.add(new Coordinate("H7"));
		expectedCoords.add(new Coordinate("D3"));
		expectedCoords.add(new Coordinate("C2"));
		expectedCoords.add(new Coordinate("B1"));
		expectedCoords.add(new Coordinate("F3"));
		expectedCoords.add(new Coordinate("G2"));
		expectedCoords.add(new Coordinate("H1"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testLegalBishopMove2() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new Bishop(Color.WHITE));
		map.put(new Coordinate("C2"), new Bishop(Color.BLACK));	
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("D5"));
		expectedCoords.add(new Coordinate("C6"));
		expectedCoords.add(new Coordinate("B7"));
		expectedCoords.add(new Coordinate("A8"));
		expectedCoords.add(new Coordinate("F5"));
		expectedCoords.add(new Coordinate("G6"));
		expectedCoords.add(new Coordinate("H7"));
		expectedCoords.add(new Coordinate("D3"));
		expectedCoords.add(new Coordinate("C2"));
		expectedCoords.add(new Coordinate("F3"));
		expectedCoords.add(new Coordinate("G2"));
		expectedCoords.add(new Coordinate("H1"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testLegalQueenMove1() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new Queen(Color.WHITE));		
		Board board = new Board(map);
		
		Set<Coordinate> expectedCoords = new LinkedHashSet<Coordinate>();
		expectedCoords.add(new Coordinate("D5"));
		expectedCoords.add(new Coordinate("C6"));
		expectedCoords.add(new Coordinate("B7"));
		expectedCoords.add(new Coordinate("A8"));
		expectedCoords.add(new Coordinate("F5"));
		expectedCoords.add(new Coordinate("G6"));
		expectedCoords.add(new Coordinate("H7"));
		expectedCoords.add(new Coordinate("D3"));
		expectedCoords.add(new Coordinate("C2"));
		expectedCoords.add(new Coordinate("B1"));
		expectedCoords.add(new Coordinate("F3"));
		expectedCoords.add(new Coordinate("G2"));
		expectedCoords.add(new Coordinate("H1"));
		expectedCoords.add(new Coordinate("E5"));
		expectedCoords.add(new Coordinate("E6"));
		expectedCoords.add(new Coordinate("E7"));
		expectedCoords.add(new Coordinate("E8"));
		expectedCoords.add(new Coordinate("E3"));
		expectedCoords.add(new Coordinate("E2"));
		expectedCoords.add(new Coordinate("E1"));
		expectedCoords.add(new Coordinate("D4"));
		expectedCoords.add(new Coordinate("C4"));
		expectedCoords.add(new Coordinate("B4"));
		expectedCoords.add(new Coordinate("A4"));
		expectedCoords.add(new Coordinate("F4"));
		expectedCoords.add(new Coordinate("G4"));
		expectedCoords.add(new Coordinate("H4"));
		Coordinate startCoord = new Coordinate("E4");
		Set<Move> expected = Board.convertEndingCoordsToMoves(startCoord, expectedCoords);
		assertEquals(expected, board.getLegalMoves(startCoord));
	}	
	
	public void testCheckmatedStartingBoard() {
		assertFalse(startingBoard.isCheckmated(Color.BLACK));
		assertFalse(startingBoard.isCheckmated(Color.WHITE));
	}	
	
	public void testCheckmatedOnTrappedKingNoCheck() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("D1"), new Rook(Color.BLACK));
		map.put(new Coordinate("F1"), new Rook(Color.BLACK));
		map.put(new Coordinate("A3"), new Rook(Color.BLACK));
		map.put(new Coordinate("A5"), new Rook(Color.BLACK));
		Board board = new Board(map);
		assertFalse(board.isCheckmated(Color.BLACK));
		assertFalse(board.isCheckmated(Color.WHITE));
	}
	
	public void testCheckmatedOnTrappedKingWithCheck() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("E1"), new Rook(Color.BLACK));
		map.put(new Coordinate("D1"), new Rook(Color.BLACK));
		map.put(new Coordinate("F1"), new Rook(Color.BLACK));
		map.put(new Coordinate("A3"), new Rook(Color.BLACK));
		map.put(new Coordinate("A5"), new Rook(Color.BLACK));
		Board board = new Board(map);
		assertFalse(board.isCheckmated(Color.BLACK));
		assertTrue(board.isCheckmated(Color.WHITE));
	}
	
	public void testCheckmatedOnTrappedKingWithLegalMove() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("E3"), new Rook(Color.BLACK));
		map.put(new Coordinate("D1"), new Rook(Color.BLACK));
		map.put(new Coordinate("F1"), new Rook(Color.BLACK));
		map.put(new Coordinate("A5"), new Rook(Color.BLACK));
		Board board = new Board(map);
		assertFalse(board.isCheckmated(Color.BLACK));
		assertFalse(board.isCheckmated(Color.WHITE));
	}	
	
	public void testCheckmatedOnTrappedKingNotInCheck() {
		assertFalse(startingBoard.isCheckmated(Color.BLACK));
		assertFalse(startingBoard.isCheckmated(Color.WHITE));
	}	
	
	public void testKingInCheck() throws Exception{
		assertFalse(startingBoard.isInCheck(Color.WHITE));
		assertFalse(startingBoard.isInCheck(Color.BLACK));
		
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("E2"), new Rook(Color.BLACK));
		Board board = new Board(map);
		assertTrue(board.isInCheck(Color.WHITE));
	}
	
	public void testKingMovingIntoCheck() throws Exception{
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("D2"), new Rook(Color.BLACK));
		Board board = new Board(map);
		assertFalse(board.isLegal(new Move(new Coordinate("E4"), new Coordinate("D4"))));
		assertFalse(board.isLegal(new Move(new Coordinate("E4"), new Coordinate("D3"))));
	}	
	
	public void testKingInCheckButAnotherPieceCanTake() throws Exception{
		assertFalse(startingBoard.isInCheck(Color.WHITE));
		assertFalse(startingBoard.isInCheck(Color.BLACK));
		
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("E1"), new Rook(Color.BLACK));
		map.put(new Coordinate("D1"), new Rook(Color.BLACK));
		map.put(new Coordinate("F1"), new Rook(Color.BLACK));
		map.put(new Coordinate("A3"), new Rook(Color.BLACK));
		map.put(new Coordinate("A5"), new Rook(Color.BLACK));
		map.put(new Coordinate("G3"), new Bishop(Color.WHITE));
		Board board = new Board(map);
		assertTrue(board.isInCheck(Color.WHITE));
		assertTrue(board.getAllLegalMoves().size() == 1);
		assertTrue(board.getAllLegalMoves().contains(new Move(new Coordinate("G3"), new Coordinate("E1"))));
	}
	
	public void testKingCannotTakeProtectedPiece() throws Exception{		
		Map<Coordinate, Piece> map = new HashMap<Coordinate, Piece>();
		map.put(new Coordinate("E4"), new King(Color.WHITE));
		map.put(new Coordinate("E3"), new Rook(Color.BLACK));
		map.put(new Coordinate("D1"), new Rook(Color.BLACK));
		map.put(new Coordinate("F1"), new Rook(Color.BLACK));
		map.put(new Coordinate("A3"), new Rook(Color.BLACK));
		map.put(new Coordinate("A5"), new Rook(Color.BLACK));
		
		Board board = new Board(map);
		assertTrue(board.isInCheck(Color.WHITE));
		assertTrue(board.getAllLegalMoves().isEmpty());
		assertTrue(board.isCheckmated(Color.WHITE));
		assertTrue(board.isGameOver());
	}
	
	public void testKingCheckmated() throws Exception{		
		startingBoard.execute(new Move("E2", "E4"));
		startingBoard.execute(new Move("A7", "A6"));
		startingBoard.execute(new Move("F1", "C4"));
		startingBoard.execute(new Move("A6", "A5"));
		startingBoard.execute(new Move("D1", "F3"));
		startingBoard.execute(new Move("A5", "A4"));
		startingBoard.execute(new Move("F3", "F7"));
		assertTrue(startingBoard.isInCheck(Color.BLACK));
		assertTrue(startingBoard.getAllLegalMoves().isEmpty());
		assertTrue(startingBoard.isCheckmated(Color.BLACK));
		assertTrue(startingBoard.isGameOver());
	}
}

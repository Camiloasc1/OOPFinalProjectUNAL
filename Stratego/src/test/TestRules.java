/**
 * File: TestRules.java
 * Package: Stratego.test.TestRules
 * Creation: 17/01/2014 at 16:28:21
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Board;
import entities.Piece;
import entities.pieces.Bomb;
import entities.pieces.Captain;
import entities.pieces.Colonel;
import entities.pieces.Flag;
import entities.pieces.General;
import entities.pieces.Lieutenant;
import entities.pieces.Major;
import entities.pieces.Marshal;
import entities.pieces.Miner;
import entities.pieces.Scout;
import entities.pieces.Sergeant;
import entities.pieces.Spy;
import game.Engine;
import gui.GUI;
import gui.states.GameStates;

/**
 * @author camiloasc1
 * 
 */
public class TestRules
{
	private static Board board;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		System.setProperty("java.library.path", "lib");
		// Init libraries
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl-2.9.0/native/linux/").getAbsolutePath());
		
		GUI.init();
		
		GameStates.SetState(GameStates.MAINMENU);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		board = Board.getInstance();
		board.clear();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	
	/**
	 * Test method for {@link game.Rules#isValidMove(entities.Piece, int, int)}.
	 */
	@Test
	public final void testIsValidMove()
	{
		Piece[] pieces = new Piece[6];
		
		pieces[0] = new Scout(true);
		board.addPiece(pieces[0]);
		board.setPiecePos(pieces[0], 0, 0);
		
		pieces[1] = new Scout(true);
		board.addPiece(pieces[1]);
		board.setPiecePos(pieces[1], 0, 1);
		
		pieces[2] = new Flag(true);
		board.addPiece(pieces[2]);
		board.setPiecePos(pieces[2], 1, 0);
		
		pieces[3] = new Flag(false);
		board.addPiece(pieces[3]);
		board.setPiecePos(pieces[3], 0, 9);
		
		pieces[4] = new Scout(false);
		board.addPiece(pieces[4]);
		board.setPiecePos(pieces[4], 0, 5);
		
		pieces[5] = new Bomb(false);
		board.addPiece(pieces[5]);
		board.setPiecePos(pieces[5], 9, 6);
		
		// Do not over ally pieces
		assertTrue(!board.movePiece(pieces[0], 0, 0));
		assertTrue(!board.movePiece(pieces[0], 0, 1));
		assertTrue(!board.movePiece(pieces[0], 1, 0));
		// Do not diagonal
		assertTrue(!board.movePiece(pieces[0], 1, 1));
		// Do not move
		assertTrue(!board.movePiece(pieces[2], 1, 1));
		assertTrue(!board.movePiece(pieces[3], 0, 0));
		// Not Jump
		assertTrue(!board.movePiece(pieces[1], 0, 8));
		assertTrue(board.movePiece(pieces[4], 9, 5));
		// Not remote attack
		assertTrue(!board.movePiece(pieces[1], 0, 9));
		// Valid Movements
		assertTrue(board.movePiece(pieces[1], 0, 2));
		assertTrue(board.movePiece(pieces[1], 0, 4));
		assertTrue(board.movePiece(pieces[1], 0, 8));
		// move&attack
		assertTrue(board.movePiece(pieces[1], 0, 9));
		// TODO Test over lake
		// TODO Test Scout backward
	}
	
	/**
	 * Test method for {@link game.Engine#battle(entities.Piece, entities.Piece)}.
	 */
	@Test
	public final void testBattle()
	{
		// Friend Fire
		assertEquals(Engine.battle(new Scout(true), new Miner(true)), 0);
		assertEquals(Engine.battle(new Marshal(true), new Flag(true)), 0);
		// BOOOM!!!
		assertEquals(Engine.battle(new Scout(true), new Bomb(false)), -1);
		// Deactivate
		assertEquals(Engine.battle(new Miner(true), new Bomb(false)), 1);
		// Spy attack
		assertEquals(Engine.battle(new Spy(true), new Marshal(false)), 1);
		// Spy die
		assertEquals(Engine.battle(new Marshal(true), new Spy(false)), 1);
		assertEquals(Engine.battle(new Scout(true), new Spy(false)), 1);
		assertEquals(Engine.battle(new Miner(true), new Spy(false)), 1);
		assertEquals(Engine.battle(new Spy(true), new Scout(false)), -1);
		assertEquals(Engine.battle(new Spy(true), new Miner(false)), -1);
		assertEquals(Engine.battle(new Spy(true), new Bomb(false)), -1);
		// Invalid
		assertEquals(Engine.battle(new Bomb(true), new Flag(false)), 0);
		assertEquals(Engine.battle(new Bomb(true), new Miner(false)), 0);
		assertEquals(Engine.battle(new Flag(true), new Bomb(false)), 0);
		assertEquals(Engine.battle(new Flag(true), new Marshal(false)), 0);
		// Sequence
		assertEquals(Engine.battle(new Scout(true), new Spy(false)), 1);
		assertEquals(Engine.battle(new Miner(true), new Scout(false)), 1);
		assertEquals(Engine.battle(new Sergeant(true), new Miner(false)), 1);
		assertEquals(Engine.battle(new Lieutenant(true), new Sergeant(false)), 1);
		assertEquals(Engine.battle(new Captain(true), new Lieutenant(false)), 1);
		assertEquals(Engine.battle(new Major(true), new Captain(false)), 1);
		assertEquals(Engine.battle(new Colonel(true), new Major(false)), 1);
		assertEquals(Engine.battle(new General(true), new Colonel(false)), 1);
		assertEquals(Engine.battle(new Marshal(true), new General(false)), 1);
		// Important
		assertEquals(Engine.battle(new Marshal(true), new Scout(false)), 1);
		assertEquals(Engine.battle(new Marshal(true), new Miner(false)), 1);
		// Same
		assertEquals(Engine.battle(new Scout(true), new Scout(false)), 2);
		assertEquals(Engine.battle(new Miner(true), new Miner(false)), 2);
		assertEquals(Engine.battle(new Marshal(true), new Marshal(false)), 2);
		// End
		assertEquals(Engine.battle(new Spy(true), new Flag(false)), 1);
		assertEquals(Engine.battle(new Miner(true), new Flag(false)), 1);
		assertEquals(Engine.battle(new Marshal(true), new Flag(false)), 1);
	}
}

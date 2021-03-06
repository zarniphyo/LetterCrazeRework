/**
 * 
 */
package player.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import doubles.TestBoard;
import entity.LetterGenerator;

/**
 * @author Zarni Phyo
 *
 */
public class BoardTest {

	private TestBoard tb;
	
	@Before
	public void setUp()
	{
		tb = new TestBoard();
	}
	
	@Test
	public void firstTimeFillRandLetters()
	{
		assertFalse(tb.noEmptyTilesLeft());
		tb.fillRandomLetters();
		assertTrue(tb.noEmptyTilesLeft());
	}
	
	@Test
	public void adjacency()
	{
		assertTrue(tb.isAdjacent(14, 13));		// left
		assertTrue(tb.isAdjacent(14, 15));		// right
		assertTrue(tb.isAdjacent(14, 8));		// top
		assertTrue(tb.isAdjacent(14, 20));		// bottom
		assertTrue(tb.isAdjacent(14, 7));		// top left
		assertTrue(tb.isAdjacent(14, 21));		// bottom right
		assertTrue(tb.isAdjacent(14, 9));		// top right
		assertTrue(tb.isAdjacent(14, 19));		// bottom left
		
		assertFalse(tb.isAdjacent(14, 14));		// self
		assertFalse(tb.isAdjacent(14, 12));		// other
	}
	
	@Test
	public void isValidWord()
	{
		populateBoard();
		selectFRAGMENT();
		assertTrue(tb.isValidWord());
	}
	
	@Test
	public void invalidSelectionLessThan3()
	{
		tb.select(0);
		tb.select(7);
		assertFalse(tb.isValidSelection());
	}
	
	@Test
	public void invalidSelectionNotAdjacent()
	{
		tb.select(0);
		tb.select(7);
		tb.select(15);
		assertFalse(tb.isValidSelection());
	}
	
	@Test
	public void validSelection()
	{
		tb.select(0);
		tb.select(7);
		tb.select(14);
		assertTrue(tb.isValidSelection());
	}
	
	@Test
	public void isTheWordValid_Valid()
	{
		populateBoard();
		selectFRAGMENT();
		assertTrue(tb.isTheWordValid());
	}
	
	@Test
	public void isTheWordValid_NonValid()
	{
		populateBoard();
		selectFRAGMENT();
		tb.select(6);
		assertFalse(tb.isTheWordValid());
	}
	
	@Test
	public void removeWord()
	{
		populateBoard();
		selectFRAGMENT();
		assertEquals(19, tb.removeWord());
	}
	
	public void populateBoard()
	{
		LetterGenerator lg = LetterGenerator.getInstance();
		tb.setLetterToTile(lg, "F", 0);
		tb.setLetterToTile(lg, "R", 6);
		tb.setLetterToTile(lg, "A", 12);
		tb.setLetterToTile(lg, "G", 13);
		tb.setLetterToTile(lg, "M", 19);
		tb.setLetterToTile(lg, "E", 14);
		tb.setLetterToTile(lg, "N", 8);
		tb.setLetterToTile(lg, "T", 2);
		tb.fillRandomLetters();
	}
	
	public void selectFRAGMENT()
	{
		tb.select(0);
		tb.select(6);
		tb.select(12);
		tb.select(13);
		tb.select(19);
		tb.select(14);
		tb.select(8);
		tb.select(2);
	}
	
	
}

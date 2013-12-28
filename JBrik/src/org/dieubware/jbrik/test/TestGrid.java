package org.dieubware.jbrik.test;

import org.dieubware.jbrik.Grid;

/**
 * Test class for the Grid class
 * @author tornoz
 *
 */
public class TestGrid {

	public static Grid grid;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		grid = new Grid(5, 10);
		grid.fill();
		System.out.println("Test fill : ");
		System.out.println(grid.toStringWithPaths());
		testFind4P();

	}
	
	public static void testFind4P() {
		System.out.println("####################");
		System.out.println("Test find4Path");
		System.out.println(grid.find4Paths());
	}
	
	public static void test4neb() {
		System.out.println("####################");
		System.out.println("Test 4neightbors");
		System.out.println("The neighbor of 4,5 ("+grid.get(4, 5)+") are : ");
		System.out.println(grid.neighbors(4, 5, 4));
	}

}

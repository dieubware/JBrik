package org.dieubware.jbrik;
import java.util.HashSet;
import java.util.Set;

/**
 * Multiple tiles with matching color
 * @author tornoz
 *
 */

public class Path {
	Set<Cell> cells;
	int t;
	
	public Path() {
		cells = new HashSet<Cell>();
	}
	
	public void addCell(Cell c) {
		cells.add(c);
	}
	public void addCell(int val, int x, int y) {
		cells.add(new Cell(val, x, y));
	}
	public void addCell(int val, Point p) {
		cells.add(new Cell(val, p));
	}
	
	public boolean contains(Cell c) {
		return cells.contains(c);
	}
	
	public int[] getCells() {
		int[] tab = new int[cells.size()];
		return tab;
	}
	
	public String toString() {
		return cells.toString();
		
	}
	
	@Override
	public boolean equals(Object o) {
	    Path p = (Path)o;
	    if(p.t != t)
	        return false;
	    if(p.cells.size() != cells.size()) {
	        return false;
	    }
	    return true;/*
	    for(Cell c : cells) {
	        TODO : check all cells
	    }*/
	        
	
	}

	/**
	 * Check if the path containsthe given point
	 * @param p point
	 * @return true if the point is contained in the path, false otherwise
	 */
    public boolean containsPoint(Point p) {
        for(Cell c : cells) {
            if(c.getPoint().equals(p)) {
                return true;
            }   
        }
        return false;
    }
}

package org.dieubware.jbrik;
/**
 * Cell representation in a path
 * 
 * @author tornoz
 *
 */

public class Cell {

	private Point p;
	private int val;
	
	public Cell(int val, int x, int y) {
		this.val = val;
		p= new Point(x,y); 
	}
	
	public Cell(int val, Point p) {
		this.val = val;
		this.p = p;
	}
	
	
	@Override
	public boolean equals(Object o) {
	    Cell c = (Cell)o;
	    if(c.p == this.p)
	        return true;
	    if(c.val == this.val)
	        return true;
	    return false;
	}
	
	public Point getPoint() {
	    return p;
	}
	

	public int getVal() {
		return val;
	}
	
	public String toString() {
		return p + " : " + val;
	}
}

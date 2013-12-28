package org.dieubware.jbrik;
/**
 * Simple point representation
 * It contains useful and simple to use methods to set/get coordinates of a point
 * @author tornoz
 *
 */
public class Point 
{

	public int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this(p.x, p.y);
	}

	public Point() {
		x = y = 0;
	}
	
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoord(Point p) {
		this.setCoord(p.x, p.y);
	}
	
	@Override
	public boolean equals(Object o) {
	    Point p = (Point)o;
	    return (this.x == p.x &&
	            this.y == p.y);
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

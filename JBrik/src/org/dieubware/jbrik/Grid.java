package org.dieubware.jbrik;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
/**
 * Grid representation
 * @author tornoz
 *
 */
public class Grid extends Observable  {


    protected boolean gravity; //TODO choose gravity direction
    protected int width;
    protected int height;
    protected int nbColors;
    protected Collection<Point> justDeleted;

    /**
     * Grid representation, of size width*height
     * Stored from left to right, up to down
     */
    protected int[] grid;

    public Grid(int nbColors, int width, int height, boolean gravity) {
        this.width = width;
        this.height = height;
        this.gravity = gravity;
        this.nbColors = nbColors;
        grid = new int[width*height];
        justDeleted = new ArrayList<Point>();
    }

    public Grid(int nbColors, int width, int height) {
        this(nbColors, width,height,true);
    }

    /**
     * Square grid
     * @param length
     */
    public Grid(int nbColors, int length) {
        this(nbColors, length, length);
    }

    /**
     * Square grid with specific gravity
     * @param length
     * @param gravity
     */
    public Grid(int nbColors, int length, boolean gravity) {
        this(nbColors, length, length, gravity);
    }
    
    public void initGrid() {
    	for(int i = 0; i < width*height; i++) {
            grid[i] = 0;

        }
    }

    /**
     * Fills the grid with random colors
     */
    public void fill() {
        for(int i = 0; i < width*height; i++) {
            grid[i] = (int)(Math.random()*nbColors);

        }

    }
 
    public void delete4Paths(int minSize) {
    	for(Path p : find4Paths().pathList) {
    		if(p.cells.size() >= minSize) {
    			deletePath(p);
    		}
    	}
    }
    
    /**
     * Returns a list containig the paths (4-neighbors) contained in the grid
     * @return path list
     */
    public PathList find4Paths() {
        PathList pList = new PathList();
        for(int j = 0; j< height; j++) {
            for(int i = 0; i< width; i++) {
                int current = grid[getIndex(i,j)];
                for(Cell c : neighbors(i, j, 4)) {
                    if(c.getVal() == current && current != 0) {
                        pList.addToPath(new Cell(grid[getIndex(i,j)], i, j), c);
                    }
                }
            }


        }

        return pList;
    }
    public void deletePath(Path path) {
    	System.out.println("---");
    	for(Cell c : path.cells) {
    		int val = c.getVal();
    		Point p = c.getPoint();
    		System.out.println("treating : " + p);
    		while(val != 0 && p.y < height-1) {
    			p.y++;
    			val = grid[getIndex(p)];
    			grid[getIndex(p.x, p.y-1)] = val;
    			
    		}
    	}
    }

    

     /**
     * Returns the neighbors of the given cell
     * @param x x value of the cell
     * @param y y value of the cell
     * @param dimension 4 or 8 neighbors
     * @return a list of cells
     */
    public List<Cell> neighbors(int x, int y,int dimension) {
        if(dimension != 4 && dimension != 8)
            return null; //TODO : throw exception
        List<Cell> result = new ArrayList<Cell>();

        if(dimension == 4) {

            //UP
            if(y>0) {
                result.add(new Cell(grid[getIndex(x, y-1)], x, y-1));
            }
            //DOWN
            if(y < height-1) {
                result.add(new Cell(grid[getIndex(x, y+1)], x, y+1));
            }
            //LEFT
            if(x > 0) {
                result.add(new Cell(grid[getIndex(x-1, y)], x-1, y));
            }
            //RIGHT
            if(x < width-1) {
                result.add(new Cell(grid[getIndex(x+1, y)], x+1, y));
            }
        }
        else {
            return null; //TODO implement 8 dimension
        }
        return result;
    }

    /**
     * Returns the index of the cell designated by the x and y
     * @param x
     * @param y
     * @return
     */
    protected int getIndex(int x, int y) {
    	if(x > width-1 || x < 0 || y > height-1 || y <0)
    		return -1;
        return y*width + x;
    }
    
    /**
     * 
     * @param p
     * @return
     */
    protected int getIndex(Point p) {
    	return getIndex(p.x, p.y);
    }

    /**
     * Returns the Point (x and y values) corresponding to the given index
     * @param i
     * @return
     */
    protected Point getCoord(int i) {
        return new Point(getX(i), getY(i));
    }
    
    protected int getY(int i) {
    	return i/width;
    }
    
    protected int getX(int i) {
    	 return i%width;
    }



    public Path find8Paths() {
        return null;
    }


    public int get(int x, int y) {
        if(x < 0 || x>=width || y<0 || y>=height)
            return -1;

        return grid[getIndex(x,y)];
    }

    
    @Override
    public String toString() {
    	return toString(this.grid);
    }
    
    public String toString(int[] grid) {
        String str = "   ";
        for(int i =0; i< width; i++)
            str += " " + i + " ";
        str+="\n\n";

        for(int i =height-1; i >=0; i--) {
        	for(int j = 0; j < width; j++) {
	            if(j == 0)
	                str+="\n" + i + "  ";
	            str+= " "+grid[getIndex(j,i)]+" ";
	            
        	}
        }
        return str;
    }

    public String toStringWithPaths() {
        String str = "   ";
        char symbol = 'a';
        for(int i =0; i< width; i++)
            str += " " + i + " ";
        str+="\n\n";
        PathList pList = find4Paths();
        for(int i =0; i < width*height; i++) {
            if(i%width == 0)
                str+=i/height + "  ";

            if(pList.containsPoint(getCoord(i))) {
                str += symbol;
                symbol++;
            } else
                str+=" ";


            str+= grid[i]+" ";
            if((i+1)%width == 0) {
                str+="\n";
            }
        }
        return str;

    }
    
    /**
     * Find the lines that does not contain empty cells and delete them
     * This method is originallt used by Tetris but can serve for other brik-based games
     * @return number of deleted lines
     */
	protected int checkForLines() {
		justDeleted.clear();
		System.out.println("Checking for lines...");
		int line = 0;
		int nbLines = 0;
		for(int i = 0; i<grid.length; i++) {
			if(i%width == 0)
				line = 0;
			if(grid[i] != 0)
				line++;
			if(line == width) {
				System.out.println("Line found !");
				deleteLine(i/width);
				i=i-width;
				nbLines++;
			}
		}
		return nbLines;
	}

	/**
	 * Delete the given line by copying the lines above (last line is duplicated)
	 * @param line line to delete
	 */
	protected void deleteLine(int line) {
		for(int i = (line+1)*width; i<grid.length; i++) {
			//We put down every fucking square
			Point p = new Point(getCoord(i).x, getCoord(i).y-1);
			grid[getIndex(p)] = grid[i];
			justDeleted.add(p);

		}
	}

    
    public int[] getArray() {
    	return grid;
    }

    public Collection<Point> getJustDeleted() {
    	return justDeleted;
    }
    
}

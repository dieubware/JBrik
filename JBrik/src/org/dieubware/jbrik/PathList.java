package org.dieubware.jbrik;
import java.util.HashSet;
import java.util.Set;

/**
 * List of paths
 * @author tornoz
 *
 */
public class PathList {

	Set<Path> pathList;
	
	public PathList () {
		pathList = new HashSet<Path>();
	}
	
	public void addToPath(Cell c1,Cell c2) {
		boolean contains = false;
		for(Path p : pathList) {
			contains = p.containsPoint(c1.p());
			if(contains) {
				p.addUniqueCell(c2);
				return;
			}
		}
		if(!contains) {
			Path p = new Path();
			p.addCell(c1);
			p.addCell(c2);
			pathList.add(p);
		}
	}
	
	
	
	public boolean containsCell(Cell c) {
	    for(Path tmp : pathList) {
            if(tmp.contains(c)) {
                return true;
            }
        }
	    return false;
	}
	
	public boolean contains(Path p) {
	    for(Path tmp : pathList) {
	        if(tmp.equals(p)) {
	            return true;
	        }
	    }
	    return false;
	    
	}
	
	public String toString() {
		String str = "";
		for(Path p : pathList) {
			str += p + "\n";
		}
		return str;
	}

    public boolean containsPoint(Point p) {
        for(Path tmp : pathList) {
            if(tmp.containsPoint(p)) {
                return true;
            }
        }
        return false;
    }
}

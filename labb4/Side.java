package mountain;

public class Side{
	private Point p1, p2;
	
	public Side(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return p1;
	}
	
	public Point getP2() {
		return p2;
	}
	@Override
	public boolean equals(Object o) {
		Side s = (Side) o;
		if(s.getP1().equals(p1) && s.getP2().equals(p2) || s.getP1().equals(p2) && s.getP2().equals(p1) ) {
			return true;
		}else {
			return false;
		}
	} 
//	public boolean equals(Side s) {
//		if(s.getP1().equals(p1) && s.getP2().equals(p2) || s.getP1().equals(p2) && s.getP2().equals(p1) ) {
//			return true;
//		}else {
//			return false;
//		}
//	} 
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	
	
}

package mountain;

import java.util.HashMap;

import fractal.*;

public class MountainFractal extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private double dev;
	private HashMap<Side, Point> SideMap;

	public MountainFractal(Point p1, Point p2, Point p3, double dev) {
		super(); // what diz though?
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		SideMap = new HashMap<Side, Point>();
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return "Bergsfraktal";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle
	 *            the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(p1.getX(), p1.getY());
		fractalLine(turtle, order, p1, p2, p3, dev);
	}

	/*
	 * Recursive method: Draws a recursive line of the triangle.
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double dev) {
		// RandomUtilities rand = new RandomUtilities();
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.penDown();
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
			turtle.penUp();
		} else {
			Side sa = new Side(p1, p3);
			Side sb = new Side(p2, p3);
			Side sc = new Side(p1, p2);
			Point p4, p5, p6;

			if (SideMap.containsKey(sa)) {
				p4 = SideMap.get(sa);
				SideMap.remove(sa);
			} else {
				p4 = new Point(p1.getX() + (p3.getX() - p1.getX()) / 2,
						(int) (p1.getY() + RandomUtilities.randFunc(dev) + (p3.getY() - p1.getY()) / 2));
				SideMap.put(sa, p4);
			}
			
			if (SideMap.containsKey(sc)) {
				p5 = SideMap.get(sc);
				SideMap.remove(sc);
			} else {
				p5 = new Point(p1.getX() + (p2.getX() - p1.getX()) / 2,
						(int) (p2.getY() + RandomUtilities.randFunc(dev) + (p1.getY() - p2.getY()) / 2));
				SideMap.put(sc, p5);
			}

			if (SideMap.containsKey(sb)) {
				p6 = SideMap.get(sb);
				SideMap.remove(sb);
			} else {
				p6 = new Point(p2.getX() + (p3.getX() - p2.getX()) / 2,
						(int) (p2.getY() + RandomUtilities.randFunc(dev) + (p3.getY() - p2.getY()) / 2));
				SideMap.put(sb, p6);
			}

			fractalLine(turtle, order - 1, p1, p4, p5, dev / 2);
			fractalLine(turtle, order - 1, p4, p3, p6, dev / 2);
			fractalLine(turtle, order - 1, p5, p6, p2, dev / 2);
			fractalLine(turtle, order - 1, p4, p6, p5, dev / 2); // <-- Remove for extra fractalibility with extra
																	// cheese.
		}
	}

}

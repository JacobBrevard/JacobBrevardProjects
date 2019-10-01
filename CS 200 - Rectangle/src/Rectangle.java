
public class Rectangle extends Object { // All classes extends objects by default

	private double height = 0.0;
	private double width = 0.0;

	private static int numRectangles = 0;

	public Rectangle(double height, double width) { // Constructor for Rectangles
		this.height = height;
		this.width = width;
		numRectangles++;
	}

	public static int getNumRectangles() { // Accessor/Getter to see the total number of rectangles
		return numRectangles;
	}

	public double getHeight() { // Accessor/Getter to see the height for a given rectangle
		return this.height;
	}

	public double getWidth() { // Accessor/Getter to see the width for a given rectangle
		return this.width;
	}

	public double getPerimeter() { // Accessor/Getter to see the perimeter for a given rectangle
		return (this.height * 2) + (this.width * 2);
	}

	public double getArea() { // Accessor/Getter to see the area for a given rectangle
		return this.width * this.height;
	}

	public void setHeight(double newHeight) { // Mutator/Setter to change the height for a given
												// rectangle
		height = newHeight;
	}

	 public String toString() { // toString Method for printing out the values
		return "Height = " + height + " Width = " + width;
	}
}


public class Shapes {

	public static void main(String[] args) {
		Rectangle first = new Rectangle(2.0, 5.0); // Creates a rectangle with the constructor

		System.out.println(first.getArea()); // Gets the area and then prints out
		System.out.println(first.getPerimeter()); // Gets the perimeter and then prints out

		System.out.println(first); // Prints out the Height and Width

		System.out.println(first.getHeight());

		first.setHeight(10.0); // Sets the height to 10

		System.out.println(first.getHeight());
	}
}

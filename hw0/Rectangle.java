package hw0;

public class Rectangle{
	double width = 0;
	double length = 0;
	public Rectangle(double x, double y) {
		width = x;
		length = y;
	}
	public static void main(String[] args) {
		Rectangle r = new Rectangle(1,5);
		System.out.println(r.width);
	}
}
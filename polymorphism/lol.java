package polymorphism;

public class lol {
    public static void main(String[] args) {
        Circle c1 = new Cylinder(1.1,2.2); // base class object with derived class's constructor. diff. args, no error
        System.out.println(c1.getRadius()); // ok because cylinder's constructor sets radius.
        //c1.getHeight(); // Exception thrown. Circle has no method getHeight().
        //c1.getVolume(); // Exception thrown. Circle has no method getVolume().
        System.out.println(c1.toString()); // uses Cylinder's toString(), overridden from Circle's.
        System.out.println(c1.getArea()); // uses Cylinder's getArea(), overridden from Circle's.

        //Cylinder c2 = new Circle(2.2); // cannot convert. die.
        
    }
}

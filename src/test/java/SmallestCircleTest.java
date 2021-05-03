import org.junit.Test;

public class SmallestCircleTest {

    @Test
    public void SmallestCircleForOne() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle = new Circle(5,6,10);
        smallestCircle.addCircle(circle);
        assert (smallestCircle.minimumEnclosingCircle.equals(circle));
    }

    @Test
    public void SmallestCircleForTwo() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(5,6,10);
        Circle circle2 = new Circle(40,30,7);
        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        System.out.println(smallestCircle.minimumEnclosingCircle);
        Circle result = new Circle(21.26290, 17.15170, 29.71909);
        assert (smallestCircle.minimumEnclosingCircle.equals(result));
    }

    @Test
    public void SmallestCircleForThree() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(5,6,10);
        Circle circle2 = new Circle(40,30,7);
        Circle circle3 = new Circle(50,0,11);
        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        smallestCircle.addCircle(circle3);
        System.out.println(smallestCircle.boundaryCircles.size());
        System.out.println(smallestCircle.minimumEnclosingCircle);
        Circle result = new Circle(28.41053, 6.11146, 33.43780);
        assert (smallestCircle.minimumEnclosingCircle.equals(result));
    }

    @Test
    public void CirclesInSmallestCircle() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(5,6,10);
        Circle circle2 = new Circle(40,30,7);
        Circle circle3 = new Circle(70,22,15);
        Circle circle4 = new Circle(100,0,24);
        Circle circle5 = new Circle(50,0,1000);
        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        smallestCircle.addCircle(circle3);
        smallestCircle.addCircle(circle4);
        smallestCircle.addCircle(circle5);
        System.out.println(smallestCircle.boundaryCircles.size());
        System.out.println(smallestCircle.minimumEnclosingCircle);
        Circle result = new Circle(50,0,1000);
        assert (smallestCircle.minimumEnclosingCircle.equals(result));
    }

    //Тесты на количество баундари
}

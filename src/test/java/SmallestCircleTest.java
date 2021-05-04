import org.junit.Test;

public class SmallestCircleTest {

    @Test
    public void SmallestCircleForOne() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle = new Circle(5, 6, 10);
        smallestCircle.addCircle(circle);
        assert (smallestCircle.getMinimumEnclosingCircle().equals(circle));
    }

    @Test
    public void SmallestCircleForTwo() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(5, 6, 10);
        Circle circle2 = new Circle(40, 30, 7);
        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        Circle result = new Circle(21.26290, 17.15170, 29.71909);
        assert (smallestCircle.getMinimumEnclosingCircle().equals(result));
    }

    @Test
    public void SmallestCircleForThree() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(5, 6, 10);
        Circle circle2 = new Circle(40, 30, 7);
        Circle circle3 = new Circle(50, 0, 11);
        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        smallestCircle.addCircle(circle3);
        Circle result = new Circle(28.43926, 6.22105, 33.44030);
        assert (smallestCircle.getMinimumEnclosingCircle().equals(result));
    }

    @Test
    public void CirclesInSmallestCircle() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(5, 6, 10);
        Circle circle2 = new Circle(40, 30, 7);
        Circle circle3 = new Circle(70, 22, 15);
        Circle circle4 = new Circle(100, 0, 24);
        Circle circle5 = new Circle(50, 0, 1000);
        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        smallestCircle.addCircle(circle3);
        smallestCircle.addCircle(circle4);
        smallestCircle.addCircle(circle5);
        Circle result = new Circle(50, 0, 1000);
        assert (smallestCircle.getMinimumEnclosingCircle().equals(result));
    }

    @Test
    public void SmallestCircleForMany() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(1, 43, 76);
        Circle circle2 = new Circle(47, 43, 77);
        Circle circle3 = new Circle(56, 84, 82);
        Circle circle4 = new Circle(44, 66, 88);
        Circle circle5 = new Circle(76, 98, 87);
        Circle circle6 = new Circle(24, 53, 71);
        Circle circle7 = new Circle(46, 67, 90);
        Circle circle8 = new Circle(43, 87, 92);
        Circle circle9 = new Circle(46, 66, 88);

        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        smallestCircle.addCircle(circle3);
        smallestCircle.addCircle(circle4);
        smallestCircle.addCircle(circle5);
        smallestCircle.addCircle(circle6);
        smallestCircle.addCircle(circle7);
        smallestCircle.addCircle(circle8);
        smallestCircle.addCircle(circle9);
        System.out.println(smallestCircle.getMinimumEnclosingCircle());
        Circle result = new Circle(42.93522, 73.75250, 128.00268);
        assert (smallestCircle.getMinimumEnclosingCircle().equals(result));
    }

    @Test
    public void SmallestCircleFourSquare() {
        SmallestCircle smallestCircle = new SmallestCircle();
        Circle circle1 = new Circle(1, 1, 1);
        Circle circle2 = new Circle(1, -1, 1);
        Circle circle3 = new Circle(-1, 1, 1);
        Circle circle4 = new Circle(-1, -1, 1);

        smallestCircle.addCircle(circle1);
        smallestCircle.addCircle(circle2);
        smallestCircle.addCircle(circle3);
        smallestCircle.addCircle(circle4);
        System.out.println(smallestCircle.getMinimumEnclosingCircle());
        Circle result = new Circle(0, 0, Math.sqrt(2) + 1);
        assert (smallestCircle.getMinimumEnclosingCircle().equals(result));
    }
}

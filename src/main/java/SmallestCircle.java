import java.util.*;

public class SmallestCircle {

    Set<Circle> whole;
    Circle minimumEnclosingCircle;
    Set<Circle> boundaryCircles;

    public SmallestCircle() {
        whole = new HashSet<>();
        minimumEnclosingCircle = new Circle();
        boundaryCircles = new HashSet<>();
    }

    private boolean isCircleIn(Circle c, Circle minc) {
        return minc.r - dist(c.x,c.y,minc.x,minc.y)-c.r > 0;
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    private Circle getCircleByTwo (Circle a, Circle b) {
        double r = dist(a.x,a.y, b.x, b.y);
        double newx1 = a.x+a.r/r*(a.x-b.x);
        double newy1 = a.y+a.r/r*(a.y-b.y);

        double newx2 = b.x-b.r/r*(a.x-b.x);
        double newy2 = b.y-b.r/r*(a.y-b.y);

        return new Circle((newx1+newx2)/2, (newy1+newy2)/2, dist(newx1,newy1,newx2,newy2)/2);
    }

    // Gradient descent

    private double rho(double x, double y, Circle c) {
        return dist(x,y,c.x,c.y) + c.r;
    }

    private double F(double x, double y, Circle a, Circle b, Circle c) {
        return (rho(x,y,a)-rho(x,y,b)) * (rho(x,y,a)-rho(x,y,b)) +
                (rho(x,y,a)-rho(x,y,c)) * (rho(x,y,a)-rho(x,y,c)) +
                (rho(x,y,b)-rho(x,y,c)) * (rho(x,y,b)-rho(x,y,c));
    }

    private double sign(double x) {
        return x > 0 ? 1: -1;
    }

    private Circle getCircleByThree (Circle a, Circle b, Circle c) {
        Circle guess = new Circle((a.x+b.x+c.x)/3, (a.y+b.y+c.y)/3, 0);
        double h = 0.5;
        int n = 1;
        while (F(guess.x, guess.y,a,b,c) > 0.1) {
            double dx = (F(guess.x - h, guess.y, a, b, c) - F(guess.x + h, guess.y, a, b, c)) / (2 * h);
            double dy = (F(guess.x, guess.y - h, a, b, c) - F(guess.x, guess.y + h, a, b, c)) / (2 * h);
            guess.x += sign(dx)/Math.sqrt(n);
            guess.y += sign(dy)/Math.sqrt(n);
            n++;
        }
        guess.r = dist(guess.x,guess.y,a.x, a.y) + a.r;
        return guess;
    }

    // End Gradient descent

    private void setBoundaryCircles(Set<Circle> circles) {
        boundaryCircles.clear();
        boundaryCircles.addAll(circles);
    }

    private Circle trivialCase(Set<Circle> s) {
        if (s.isEmpty()) {
            Circle buf = new Circle(0,0,0);
            setBoundaryCircles(new HashSet<>(Collections.singletonList(buf)));
            return buf;
        }

        List<Circle> buf = new ArrayList<>(s);

        if (s.size() == 1) {
            setBoundaryCircles(s);
            return buf.get(0);
        }

        if (s.size() == 2) {
            setBoundaryCircles(s);
            return getCircleByTwo(buf.get(0), buf.get(1));
        }

        for (Circle circle : buf) {
            s.remove(circle);
            var it = s.iterator();
            Circle c = getCircleByTwo(it.next(), it.next());
            if (isCircleIn(circle, c)) {
                setBoundaryCircles(s);
                return c;
            }
            s.add(circle);
        }

        setBoundaryCircles(s);
        return getCircleByThree(buf.get(0), buf.get(1), buf.get(2));
    }

    private Circle Welzl(Set<Circle> wh, Set<Circle> bo) {

        if (wh.isEmpty() || bo.size() == 3) {
            return trivialCase(bo);
        }

        Circle c = wh.iterator().next();
        wh.remove(c);
        Circle minCircle = Welzl(wh, bo);

        if (isCircleIn(c, minCircle)) {
            wh.add(c);
            return minCircle;
        }

        bo.add(c);
        Circle w = Welzl(wh, bo);
        bo.remove(c);
        wh.add(c);
        return w;
    }

    public void addCircle(Circle c) {
        if (isCircleIn(c, minimumEnclosingCircle)) {
            whole.add(c);
            return;
        }
        minimumEnclosingCircle = Welzl(whole, new HashSet<>(Collections.singletonList(c)));
        whole.add(c);
    }

    public void removeCircle(Circle c) {
        whole.remove(c);
        if (!boundaryCircles.contains(c)) {
            return;
        }
        minimumEnclosingCircle = Welzl(whole, new HashSet<>());
    }
}
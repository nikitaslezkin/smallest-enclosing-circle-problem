import java.util.*;

public class SmallestCircle {

    private final Set<Circle> whole;
    private Circle minimumEnclosingCircle;
    private final Set<Circle> boundaryCircles;
    private Circle obligatoryCircle;

    public SmallestCircle() {
        whole = new HashSet<>();
        minimumEnclosingCircle = new Circle();
        boundaryCircles = new HashSet<>();
    }

    public Circle getMinimumEnclosingCircle() {
        return minimumEnclosingCircle;
    }

    public Set<Circle> getBoundaryCircles() {
        return boundaryCircles;
    }

    private boolean isCircleIn(Circle c, Circle minc) {
        return dist(c.x(), c.y(), minc.x(), minc.y()) + c.r() < minc.r();
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    private Circle getCircleByTwo(Circle a, Circle b) {
        double r = dist(a.x(), a.y(), b.x(), b.y());
        double newx1 = a.x() + a.r() / r * (a.x() - b.x());
        double newy1 = a.y() + a.r() / r * (a.y() - b.y());

        double newx2 = b.x() - b.r() / r * (a.x() - b.x());
        double newy2 = b.y() - b.r() / r * (a.y() - b.y());

        return new Circle((newx1 + newx2) / 2, (newy1 + newy2) / 2, dist(newx1, newy1, newx2, newy2) / 2);
    }

    private Circle getCircleByThree(Circle z1, Circle z2, Circle z3) {

        double c = 2 * (z2.x() - z1.x());
        double d = 2 * (z2.y() - z1.y());
        double e = 2 * (z1.r() - z2.r());
        double f = z1.x() * z1.x() + z1.y() * z1.y() - z1.r() * z1.r() - z2.x() * z2.x() - z2.y() * z2.y() + z2.r() * z2.r();

        double g = 2 * (z3.x() - z1.x());
        double h = 2 * (z3.y() - z1.y());
        double i = 2 * (z1.r() - z3.r());
        double j = z1.x() * z1.x() + z1.y() * z1.y() - z1.r() * z1.r() - z3.x() * z3.x() - z3.y() * z3.y() + z3.r() * z3.r();

        double m = (d * j - f * h) / (c * h - d * g);
        double n = (d * i - e * h) / (c * h - d * g);

        double p = (g * f - j * c) / (c * h - d * g);
        double q = (g * e - i * c) / (c * h - d * g);

        double a = m - z1.x();
        double b = p - z1.y();

        double aa = n * n + q * q - 1;
        double bb = 2 * (a * n + b * q + z1.r());
        double cc = a * a + b * b - z1.r() * z1.r();

        double disc = bb * bb - 4 * aa * cc;
        double rs = Math.max((-bb - Math.sqrt(disc)) / (2 * aa), (-bb + Math.sqrt(disc)) / (2 * aa));

        double xs = m + n * rs;
        double ys = p + q * rs;

        return new Circle(xs, ys, rs);
    }

    private void setBoundaryCircles(Set<Circle> circles) {
        boundaryCircles.clear();
        boundaryCircles.addAll(circles);
    }

    private Circle trivialCase(Set<Circle> s) {
        if (s.isEmpty()) {
            Circle buf = new Circle(0, 0, 0);
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
            Circle a = it.next(), b = it.next();
            if (obligatoryCircle == null || (obligatoryCircle.equals(a) || obligatoryCircle.equals(b))) {
                Circle c = getCircleByTwo(a, b);
                if (isCircleIn(circle, c)) {
                    setBoundaryCircles(s);
                    return c;
                }
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
        obligatoryCircle = c;
        minimumEnclosingCircle = Welzl(whole, new HashSet<>(Collections.singletonList(c)));
        whole.add(c);
    }

    public void removeCircle(Circle c) {
        whole.remove(c);
        if (!boundaryCircles.contains(c)) {
            return;
        }
        obligatoryCircle = null;
        minimumEnclosingCircle = Welzl(whole, new HashSet<>());
    }
}
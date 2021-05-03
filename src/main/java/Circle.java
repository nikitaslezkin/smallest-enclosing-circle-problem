public class Circle {

    public double x, y, r;
    private long timeCreation, lifetime;

    public long getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(long timeCreation) {
        this.timeCreation = timeCreation;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public Circle() {
    }

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean equals(Object obj) {
        final double eps = 0.0001;
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Circle circle = (Circle) obj;
        return Math.abs(x-circle.x)<eps && Math.abs(y - circle.y)<eps && Math.abs(r-circle.r)<eps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Double) x).hashCode();
        result = prime * result + ((Double) y).hashCode();
        result = prime * result + ((Double) r).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Circle{" + "x=" + x + ", y=" + y + ", r=" + r + '}';
    }
}

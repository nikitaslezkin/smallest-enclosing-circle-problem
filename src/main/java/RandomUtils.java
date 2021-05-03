import java.util.Random;

public class RandomUtils {

    public enum Distribution {
        Normal, Uniform
    }

    private final Random random;
    private final Distribution distribution;
    private final double a, b;

    public RandomUtils(Distribution distribution, double a, double b) {

        random = new Random();
        this.distribution = distribution;
        this.a = a;
        this.b = b;
    }

    public int getRandom() {
        if (distribution == Distribution.Uniform) {
            return (int) ((random.nextDouble() * (b - a)) + a);
        } else {
            return (int) (a + random.nextGaussian() * b);
        }
    }

}

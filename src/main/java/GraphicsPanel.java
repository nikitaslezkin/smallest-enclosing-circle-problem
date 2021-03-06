import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

public class GraphicsPanel extends JPanel {

    final int frameSizeX = 1930;
    final int frameSizeY = 1024;
    final int circleLeftBoundX = 500;
    final int circleRightBoundX = 1400;
    final int circleUpBoundY = 200;
    final int circleDownBoundY = 800;
    final int circleRadiusBoundFrom = 55;
    final int circleRadiusBoundTo = 60;
    final int circleLifetimeBoundFrom = 5000;
    final int circleLifetimeBoundTo = 5000;
    final RandomUtils circleX = new RandomUtils(RandomUtils.Distribution.Uniform, circleLeftBoundX, circleRightBoundX);
    final RandomUtils circleY = new RandomUtils(RandomUtils.Distribution.Uniform, circleUpBoundY, circleDownBoundY);
    //final RandomUtils circleRadius = new RandomUtils(RandomUtils.Distribution.Uniform, circleRadiusBoundFrom, circleRadiusBoundTo);
    final RandomUtils circleRadius = new RandomUtils(RandomUtils.Distribution.Normal, circleRadiusBoundFrom + (circleRadiusBoundTo-circleRadiusBoundFrom)/2.0, 10);
    final RandomUtils circleLifetime = new RandomUtils(RandomUtils.Distribution.Uniform, circleLifetimeBoundFrom, circleLifetimeBoundTo);
    final RandomUtils appearance = new RandomUtils(RandomUtils.Distribution.Uniform, 1, 300);

    SmallestCircle smallestCircle;
    Graphics2D g2;

    public GraphicsPanel() {
        smallestCircle = new SmallestCircle();
    }

    public void drawCircle(Circle c, boolean isFill, boolean isRed) {
        if (isRed) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
        }
        g2.drawOval((int) (c.x() - c.r()), (int) (c.y() - c.r()), (int) (2 * c.r()), (int) (2 * c.r()));
        if (isFill) {
            Ellipse2D.Double circle = new Ellipse2D.Double((int) (c.x() - c.r()), (int) (c.y() - c.r()), (int) (2 * c.r()), (int) (2 * c.r()));
            g2.fill(circle);
        }
        if (isRed) {
            g2.setColor(Color.BLACK);
        }
    }

    public void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;
        g2.clearRect(0, 0, frameSizeX, frameSizeY);
        Set<Circle> deleted = new HashSet<>();

        //Delete old circles and draw remaining
        for (Circle circle : smallestCircle.getCircles()) {
            if ((circle.getTimeCreation() + circle.getLifetime()) < System.currentTimeMillis()) {
                deleted.add(circle);
            } else {
                drawCircle(circle, false, false);
            }
        }

        //Remove old circles
        for (Circle circle : deleted) {
            smallestCircle.removeCircle(circle);
        }

        //Draw boundary circles
        for (Circle circle : smallestCircle.getBoundaryCircles()) {
            drawCircle(circle, true, false);
        }

        //Draw minimum enclosing circle
        drawCircle(smallestCircle.getMinimumEnclosingCircle(), false, true);

        //Probability of appearance and create new circle
        if (appearance.getRandom() == 1) {
            Circle newCircle = new Circle(circleX.getRandom(), circleY.getRandom(), circleRadius.getRandom());
            newCircle.setTimeCreation(System.currentTimeMillis());
            newCircle.setLifetime(circleLifetime.getRandom());
            smallestCircle.addCircle(newCircle);
        }

        repaint();
    }
}

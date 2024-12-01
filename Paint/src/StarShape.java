import java.awt.*;

public class StarShape extends Shape {
    public StarShape(int x, int y, int width, int height, Color color, int strokeWidth) {
        super(x, y, width, height, color, strokeWidth);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth));

        int[] xPoints = {
                x + width / 2, x + (int) (width * 0.6), x + width, x + (int) (width * 0.7), x + (int) (width * 0.8),
                x + width / 2, x + (int) (width * 0.2), x + (int) (width * 0.3), x, x + (int) (width * 0.4)
        };
        int[] yPoints = {
                y, y + (int) (height * 0.4), y + (int) (height * 0.4), y + (int) (height * 0.6), y + height,
                y + (int) (height * 0.75), y + height, y + (int) (height * 0.6), y + (int) (height * 0.4), y + (int) (height * 0.4)
        };
        g2.drawPolygon(xPoints, yPoints, 10);
    }
}

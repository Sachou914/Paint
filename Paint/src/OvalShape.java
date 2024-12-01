import java.awt.*;

public class OvalShape extends Shape {
    public OvalShape(int x, int y, int width, int height, Color color, int strokeWidth) {
        super(x, y, width, height, color, strokeWidth);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.drawOval(x, y, width, height);
    }
}

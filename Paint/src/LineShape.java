import java.awt.*;

public class LineShape extends Shape {
    public LineShape(int x, int y, int width, int height, Color color, int strokeWidth) {
        super(x, y, width, height, color, strokeWidth);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.drawLine(x, y, x + width, y + height);
    }
}

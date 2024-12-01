import java.awt.*;

public class SmileyShape extends Shape {
    public SmileyShape(int x, int y, int width, int height, Color color, int strokeWidth) {
        super(x, y, width, height, color, strokeWidth);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth));

        g2.drawOval(x, y, width, height);
        g2.fillOval(x + width / 4, y + height / 3, width / 10, height / 10);
        g2.fillOval(x + 3 * width / 4 - width / 10, y + height / 3, width / 10, height / 10);
        g2.drawArc(x + width / 4, y + height / 2, width / 2, height / 4, 0, -180);
    }
}

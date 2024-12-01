import java.awt.*;

public class BrushShape extends Shape {
    public BrushShape(int x, int y, int width, int height, Color color, int strokeWidth) {
        super(x, y, width, height, color, strokeWidth);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillOval(x, y, width, height);
    }
}

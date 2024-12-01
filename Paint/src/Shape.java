import java.awt.*;

public abstract class Shape {
    protected int x, y, width, height;
    protected Color color;
    protected int strokeWidth;

    public Shape(int x, int y, int width, int height, Color color, int strokeWidth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public abstract void draw(Graphics g);

    public boolean contains(int px, int py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    public boolean isInResizeHandle(int px, int py) {
        return px >= x + width - 8 && px <= x + width && py >= y + height - 8 && py <= y + height;
    }

    public void resize(int px, int py) {
        width = Math.max(10, px - x);
        height = Math.max(10, py - y);
    }

    public void moveTo(int px, int py) {
        x = px - width / 2;
        y = py - height / 2;
    }

    public void drawHandles(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x + width - 8, y + height - 8, 8, 8);
    }
}

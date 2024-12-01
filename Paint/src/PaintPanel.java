import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PaintPanel extends JPanel {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private String currentTool = "Gomme";
    private Color currentColor = Color.BLACK;
    private int strokeWidth = 2;
    private int brushSize = 10;
    private Shape selectedShape = null;
    private boolean resizing = false;
    private static final int HANDLE_SIZE = 8;

    public PaintPanel() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (currentTool.equals("Selectionner")) {
                    if (selectedShape != null && isInResizeHandle(e.getX(), e.getY(), selectedShape)) {
                        resizing = true;
                    } else {
                        selectedShape = getShapeAt(e.getX(), e.getY());
                        resizing = false;
                    }
                } else if (currentTool.equals("Gomme")) {
                    shapes.add(new BrushShape(e.getX(), e.getY(), brushSize, brushSize, Color.WHITE, strokeWidth));
                } else {
                    Shape newShape = createShape(e.getX(), e.getY(), 0, 0);
                    if (newShape != null) {
                        shapes.add(newShape);
                        selectedShape = newShape;
                    }
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                resizing = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (currentTool.equals("Selectionner") && selectedShape != null) {
                    if (resizing) {
                        selectedShape.resize(e.getX(), e.getY());
                    } else {
                        selectedShape.moveTo(e.getX(), e.getY());
                    }
                } else if (currentTool.equals("Pinceau")) {
                    shapes.add(new BrushShape(e.getX(), e.getY(), brushSize, brushSize, currentColor, strokeWidth));
                } else if (currentTool.equals("Gomme")) {
                    shapes.add(new BrushShape(e.getX(), e.getY(), brushSize, brushSize, Color.WHITE, strokeWidth));
                } else if (selectedShape != null) {
                    selectedShape.resize(e.getX(), e.getY());
                }
                repaint();
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            s.draw(g);
            if (s == selectedShape && currentTool.equals("Selectionner")) {
                s.drawHandles(g);
            }
        }
    }

    public void setTool(String tool) {
        currentTool = tool;
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    public void setBrushSize(int size) {
        brushSize = size;
    }

    public void setStrokeWidth(int width) {
        strokeWidth = width;
    }

    public void deleteSelectedShape() {
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            selectedShape = null;
            repaint();
        }
    }

    public void clearShapes() {
        shapes.clear();
        selectedShape = null;
        repaint();
    }

    private Shape getShapeAt(int x, int y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(x, y)) {
                return shapes.get(i);
            }
        }
        return null;
    }

    private boolean isInResizeHandle(int x, int y, Shape shape) {
        return shape.isInResizeHandle(x, y);
    }

    private Shape createShape(int x, int y, int width, int height) {
        return switch (currentTool) {
            case "Rectangle" -> new RectangleShape(x, y, width, height, currentColor, strokeWidth);
            case "Oval" -> new OvalShape(x, y, width, height, currentColor, strokeWidth);
            case "Ligne" -> new LineShape(x, y, width, height, currentColor, strokeWidth);
            case "Triangle" -> new TriangleShape(x, y, width, height, currentColor, strokeWidth);
            case "Etoile" -> new StarShape(x, y, width, height, currentColor, strokeWidth);
            case "Smiley" -> new SmileyShape(x, y, width, height, currentColor, strokeWidth);
            default -> null;
        };
    }

    public void saveAsPNG() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();
                paint(g2d);
                g2d.dispose();
                ImageIO.write(image, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadPNG() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                Graphics g = getGraphics();
                g.drawImage(image, 0, 0, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

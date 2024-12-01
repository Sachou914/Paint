import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Paint - Java");
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PaintPanel mainPanel = new PaintPanel();
        ToolPanel toolPanel = new ToolPanel(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Document");
        JMenuItem saveItem = new JMenuItem("Sauvegarder au format PNG");
        JMenuItem loadItem = new JMenuItem("Charger une image PNG");

        saveItem.addActionListener(e -> mainPanel.saveAsPNG());
        loadItem.addActionListener(e -> mainPanel.loadPNG());

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);

        window.setJMenuBar(menuBar);
        window.setLayout(new BorderLayout());
        window.add(toolPanel, BorderLayout.NORTH);
        window.add(mainPanel, BorderLayout.CENTER);

        window.setVisible(true);
    }
}

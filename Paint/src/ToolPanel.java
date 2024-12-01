    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.util.HashMap;
    import java.util.Map;

    public class ToolPanel extends JPanel {
        private final Map<String, JButton> toolButtons = new HashMap<>();
        private JButton activeButton = null;

        public ToolPanel(PaintPanel paintPanel) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            String[] tools = {"Pinceau", "Gomme", "Rectangle", "Oval", "Ligne", "Triangle", "Etoile", "Smiley", "Selectionner"};
            for (String tool : tools) {
                JButton button = createStyledButton(tool);
                button.addActionListener((ActionEvent e) -> {
                    paintPanel.setTool(tool);
                    highlightActiveButton(button);
                });
                toolButtons.put(tool, button);
                add(button);
            }

            JButton colorButton = createStyledButton("Couleur");
            colorButton.addActionListener((ActionEvent e) -> {
                Color chosenColor = JColorChooser.showDialog(null, "Choisir une couleur", paintPanel.getBackground());
                if (chosenColor != null) {
                    paintPanel.setColor(chosenColor);
                }
            });
            add(colorButton);

            JLabel brushSizeLabel = new JLabel("Pinceau épaisseur:");
            add(brushSizeLabel);

            JSpinner brushSizeSpinner = new JSpinner(new SpinnerNumberModel(10, 5, 50, 1));
            brushSizeSpinner.addChangeListener(e -> paintPanel.setBrushSize((int) brushSizeSpinner.getValue()));
            add(brushSizeSpinner);

            JLabel strokeLabel = new JLabel("Forme épaisseur:");
            add(strokeLabel);

            JSpinner strokeSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 20, 1));
            strokeSpinner.addChangeListener(e -> paintPanel.setStrokeWidth((int) strokeSpinner.getValue()));
            add(strokeSpinner);

            JButton clearButton = createStyledButton("Supprimer tout");
            clearButton.addActionListener((ActionEvent e) -> paintPanel.clearShapes());
            add(clearButton);

            JButton deleteButton = createStyledButton("Supprimer la forme");
            deleteButton.addActionListener((ActionEvent e) -> paintPanel.deleteSelectedShape());
            add(deleteButton);
        }

        private JButton createStyledButton(String text) {
            JButton button = new JButton(text);
            button.setFocusPainted(false);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            return button;
        }

        private void highlightActiveButton(JButton button) {
            if (activeButton != null) {
                activeButton.setBackground(Color.WHITE);
                activeButton.setForeground(Color.BLACK);
            }
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            activeButton = button;
        }
    }

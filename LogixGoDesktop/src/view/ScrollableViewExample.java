package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import utils.Constants;

import java.awt.*;
import java.util.ArrayList;

public class ScrollableViewExample {
    public static void main(String[] args) {
        // Create the main JFrame
        JFrame frame = new JFrame("Scrollable View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Main panel for the frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top panel with text fields and a button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.setBackground(Constants.background_color);
        
        JLabel lproduct = new JLabel("Product: ", SwingConstants.RIGHT);
        lproduct.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        topPanel.add(lproduct);

        JTextField textField1 = new JTextField();
        textField1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        textField1.setPreferredSize(new Dimension(200, 30));
        topPanel.add(textField1);

        JLabel lqty = new JLabel("Qty: ", SwingConstants.RIGHT);
        lqty.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        topPanel.add(lqty);
        

        JSpinner spinnerField2 = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        spinnerField2.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        spinnerField2.setPreferredSize(new Dimension(100, 30));
        topPanel.add(spinnerField2);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topPanel.add(addButton);

        JPanel scrollablePanel = new JPanel(new GridBagLayout());
        scrollablePanel.setAlignmentY(Component.TOP_ALIGNMENT); // Align content to the top

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST; // Anchor rows to the top-left
        constraints.weightx = 1.0;
        constraints.gridx = 0;

        // Create a wrapper panel to enforce top alignment
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollablePanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setPreferredSize(new Dimension(580, 350));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling

        // Add top panel and scrollable panel to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Add button listener to insert a new row
        addButton.addActionListener(e -> {
            String field1Text = textField1.getText().trim();
            int field2Value = (int) spinnerField2.getValue();

            if (!field1Text.isEmpty()) {
                constraints.gridy = scrollablePanel.getComponentCount(); // Add at the next row
                JPanel newRow = createRow(scrollablePanel, field1Text, field2Value);
                scrollablePanel.add(newRow, constraints);
                scrollablePanel.revalidate();
                scrollablePanel.repaint();
                textField1.setText("");
                spinnerField2.setValue(0);
            } else {
                JOptionPane.showMessageDialog(frame, "Field 1 must not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Method to create a row with a border
    private static JPanel createRow(JPanel scrollablePanel, String field1, int field2) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        rowPanel.setBorder(new LineBorder(Color.GRAY, 1, true)); // Add a border
        rowPanel.setPreferredSize(new Dimension(550, 40)); // Set a fixed height

        // Label for field1
        JLabel label1 = new JLabel(field1);
        label1.setPreferredSize(new Dimension(200, 30));

        // Spinner for field2
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(field2, 0, 100, 1));
        spinner.setPreferredSize(new Dimension(100, 30));

        // Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(e -> {
            scrollablePanel.remove(rowPanel);
            scrollablePanel.revalidate();
            scrollablePanel.repaint();
        });

        // Add components to the rowPanel
        rowPanel.add(label1);
        rowPanel.add(spinner);
        rowPanel.add(deleteButton);

        return rowPanel;
    }

    // Method to get all current values in the scrollablePanel
    private static ArrayList<String> getAllValues(JPanel scrollablePanel) {
        ArrayList<String> values = new ArrayList<>();
        for (Component component : scrollablePanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel rowPanel = (JPanel) component;
                JLabel label1 = (JLabel) rowPanel.getComponent(0); // First label
                JSpinner spinner = (JSpinner) rowPanel.getComponent(1); // Spinner
                values.add(label1.getText() + ", " + spinner.getValue());
            }
        }
        return values;
    }
}

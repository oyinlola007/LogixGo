package utils;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ComponentsGenerator {
	
	private static int main_panel_width;
	private static int entry_layout_height;
	
	public ComponentsGenerator(int main_panel_width, int entry_layout_height) {
		ComponentsGenerator.main_panel_width = main_panel_width;
		ComponentsGenerator.entry_layout_height = entry_layout_height;
	}

    public LayoutComponents createEntryLayout(String labelText, double labelRatio, double textFieldRatio, boolean isPassword) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Constants.background_color);

        JLabel label = new JLabel(labelText, SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, Constants.input_field_size));
        textField.setBorder(new LineBorder(Constants.border_color, 2));

        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, Constants.input_field_size));
        passwordField.setBorder(new LineBorder(Constants.border_color, 2));

        panel.setSize(new Dimension(main_panel_width, entry_layout_height));
        
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();
        
        int labelWidth = (int) (main_panel_width * labelRatio);
        int textFieldWidth = (int) (panelWidth * textFieldRatio);
        
        int labelHeight = panelHeight;
        int textFieldHeight = panelHeight;
        int verticalCenter = (panelHeight - labelHeight) / 2;
        
        label.setBounds(0, verticalCenter , labelWidth, labelHeight);
        textField.setBounds(labelWidth, verticalCenter + 15, textFieldWidth - 30, textFieldHeight - 30);
        passwordField.setBounds(labelWidth, verticalCenter + 15, textFieldWidth - 30, textFieldHeight - 30);

        panel.add(label);
        if (isPassword) {
        	panel.add(passwordField);
        } else {
        	panel.add(textField);
        }

        return new LayoutComponents(panel, label, textField, passwordField);
    }
    
    public LayoutComponents createButtonLayout(String buttonText, double spaceRatio, double buttonRatio, double sizeRatio, boolean isSolidButton, int y_offset, int h_offset) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Constants.background_color);

        JLabel space = new JLabel(" ", SwingConstants.RIGHT);
        space.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));

        JButton button = new JButton(buttonText);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        
        if (!isSolidButton) {
	        button.setBorderPainted(false);
	        button.setFocusPainted(false);
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setForeground(Constants.text_button_color);
        }

        panel.setSize(new Dimension(main_panel_width, entry_layout_height));
        
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();
        
        int labelWidth = (int) (main_panel_width * spaceRatio);
        int textFieldWidth = (int) (panelWidth * buttonRatio * sizeRatio);
        
        int labelHeight = panelHeight;
        int textFieldHeight = panelHeight;
        int verticalCenter = (panelHeight - labelHeight) / 2;

        space.setBounds(0, verticalCenter , labelWidth, labelHeight);
        button.setBounds(labelWidth, verticalCenter + y_offset, textFieldWidth, textFieldHeight + h_offset);

        panel.add(space);
        panel.add(button);

        return new LayoutComponents(panel, button);
    }
    
    public LayoutComponents createLabelLayout(String labelText, double spaceRatio, double labelRatio, double sizeRatio, int y_offset) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Constants.background_color);

        JLabel space = new JLabel(" ", SwingConstants.LEFT);
        space.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));

        JLabel label = new JLabel(labelText, SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        
        panel.setSize(new Dimension(main_panel_width, entry_layout_height));
        
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();
        
        int labelWidth = (int) (main_panel_width * spaceRatio);
        int textFieldWidth = (int) (panelWidth * labelRatio * sizeRatio);
        
        int labelHeight = panelHeight;
        int textFieldHeight = panelHeight;
        int verticalCenter = (panelHeight - labelHeight) / 2;

        space.setBounds(0, verticalCenter , labelWidth, labelHeight);
        label.setBounds(labelWidth, verticalCenter + y_offset, textFieldWidth, textFieldHeight);

        panel.add(space);
        panel.add(label);

        return new LayoutComponents(panel, label);
    }
    
    public LayoutComponents createDropdownLayout(String labelText, double labelRatio, double dropdownRatio, String[] dropdownValues) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Constants.background_color);

        JLabel label = new JLabel(labelText, SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
        
        JComboBox<String> dropdown = new JComboBox<>(dropdownValues);
        dropdown.setFont(new Font("Arial", Font.PLAIN, Constants.input_field_size));
        dropdown.setBorder(new LineBorder(Constants.border_color, 2));

        panel.setSize(new Dimension(main_panel_width, entry_layout_height));
        
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();
        
        int labelWidth = (int) (main_panel_width * labelRatio);
        int dropdownWidth = (int) (panelWidth * dropdownRatio);
        
        int labelHeight = panelHeight;
        int dropdownHeight = panelHeight;
        int verticalCenter = (panelHeight - labelHeight) / 2;

        label.setBounds(0, verticalCenter , labelWidth, labelHeight);
        dropdown.setBounds(labelWidth, verticalCenter + 15, dropdownWidth - 30, dropdownHeight - 30);

        dropdown.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                comboBox.setPreferredSize(new Dimension(comboBox.getWidth(), dropdownHeight - 30));
            }
        });
        
        panel.add(label);
        panel.add(dropdown);

        return new LayoutComponents(panel, label, dropdown);
    }



}

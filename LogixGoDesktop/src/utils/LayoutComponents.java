package utils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LayoutComponents {
    public JPanel panel;
    public JLabel label;
    public JTextField textField;
    public JPasswordField passwordField;
    public JButton button;
    public JComboBox<String> dropdown;
    

    public LayoutComponents(JPanel panel, JLabel label, JTextField textField, JPasswordField passwordField) {
        this.panel = panel;
        this.label = label;
        this.textField = textField;
        this.passwordField = passwordField;
    }
    
    public LayoutComponents(JPanel panel, JLabel label, JComboBox<String> dropdown) {
        this.panel = panel;
        this.label = label;
        this.dropdown = dropdown;
    }
    
    public LayoutComponents(JPanel panel, JLabel label) {
        this.panel = panel;
        this.label = label;
    }
    
    public LayoutComponents(JPanel panel, JButton button) {
        this.panel = panel;
        this.button = button;
    }
}
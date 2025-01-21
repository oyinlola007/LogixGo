package utils;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Helper {

	public void clearField(JTextField textField) {
		textField.setText("");
	}

	public void setPanelVisibility(JPanel panel, boolean visibility) {
		panel.setVisible(visibility);
	}

	public void showErrorMessage(Component context, String message) {
		JOptionPane.showMessageDialog(context, message, "Attention!!", JOptionPane.ERROR_MESSAGE);
	}

	public void showInfoMessage(Component context, String message, String title) {
		JOptionPane.showMessageDialog(context, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public int showConfirmDialog(Component context, String message, String title) {
		return JOptionPane.showConfirmDialog(context, message, title, JOptionPane.YES_NO_OPTION);
	}

	public String showConfirmDialogWithDropDown(Component context, String message, String title,
			ArrayList<String> options) {

		// Create a panel to hold the message and dropdown
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		// Create and center the message label
		JLabel messageLabel = new JLabel(message);

		// Create and size the comboBox
		JComboBox<String> comboBox = new JComboBox<>(options.toArray(new String[0]));
		Dimension comboBoxSize = new Dimension((int) (comboBox.getPreferredSize().width),
				comboBox.getPreferredSize().height);
		comboBox.setPreferredSize(comboBoxSize);
		comboBox.setMaximumSize(comboBoxSize);
		// comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add components to the panel
		panel.add(messageLabel);
		panel.add(Box.createVerticalStrut(15));
		panel.add(comboBox);

		// Show the dialog
		int result = JOptionPane.showConfirmDialog(context, panel, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			return (String) comboBox.getSelectedItem();
		} else {
			return null;
		}
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}

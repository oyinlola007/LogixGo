package utils;

import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

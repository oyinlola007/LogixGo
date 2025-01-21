package view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import model.User;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.Helper;
import utils.LayoutComponents;
import utils.SessionManager;

public class ChangePassword extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	private JPasswordField told_password;
	private JPasswordField tnew_password;
	private JPasswordField tconfirm_new_password;
	private JButton update_password;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.4);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 90;

	User user;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();

	public ChangePassword() {
		try {
			user = db.getUserById(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Change Password");
		title.setFont(new Font("Arial", Font.BOLD, Constants.title_size));
		title.setSize(300, 40);
		title.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin);
		c.add(title);

		back = new JButton("Go back");
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		back.addActionListener(this);
		back.setHorizontalAlignment(SwingConstants.LEFT);
		back.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		back.setForeground(Constants.text_button_color);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setSize(140, 40);
		back.setLocation((int) (main_panel_width + (0.5 * (screen_width - main_panel_width)) - 140), y_start_margin);
		c.add(back);

		mainPanel = new JPanel(new GridLayout(4, 1, 0, 0));
		mainPanel.setBorder(new LineBorder(Constants.border_color, 2));
		mainPanel.setBackground(Constants.background_color);
		mainPanel.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin + title.getHeight() + 10);
		mainPanel.setSize(main_panel_width, main_panel_height);
		c.add(mainPanel);

		ComponentsGenerator componentsGenerator = new ComponentsGenerator(main_panel_width, entry_layout_height);

		LayoutComponents old_passwordComponents = componentsGenerator.createEntryLayout("Old password: ", 0.4, 0.6,
				true);
		mainPanel.add(old_passwordComponents.panel);
		told_password = old_passwordComponents.passwordField;

		LayoutComponents new_passwordComponents = componentsGenerator.createEntryLayout("New password: ", 0.4, 0.6,
				true);
		mainPanel.add(new_passwordComponents.panel);
		tnew_password = new_passwordComponents.passwordField;

		LayoutComponents confirm_new_passwordComponents = componentsGenerator
				.createEntryLayout("Confirm new password: ", 0.4, 0.6, true);
		mainPanel.add(confirm_new_passwordComponents.panel);
		tconfirm_new_password = confirm_new_passwordComponents.passwordField;

		LayoutComponents update_passwordComponents = componentsGenerator.createButtonLayout("Update password", 0.4, 0.6,
				0.6, true, 0, -15);
		mainPanel.add(update_passwordComponents.panel);
		update_password = update_passwordComponents.button;
		update_password.addActionListener(this);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update_password) {
			String sold_password = told_password.getText();
			String snew_password = tnew_password.getText();
			String sconfirm_new_password = tconfirm_new_password.getText();

			// validate no entry
			if (sold_password.isBlank() || snew_password.isBlank() || sconfirm_new_password.isBlank()) {
				helper.showErrorMessage(this, "Please fill all required fields");
				return;
			}

			int user_id = -1;
			try {
				user_id = db.validateEmailAndPassword(user.getEmail(), sold_password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (user_id == -1) {
				helper.showErrorMessage(this, "Old password is incorrect");
				return;
			}

			// validate password match
			if (!snew_password.equals(sconfirm_new_password)) {
				helper.showErrorMessage(this, "New passwords do not match");
				return;
			}

			if (helper.showConfirmDialog(this, "Are you sure you want to change your password?", "") == 0) {
				try {
					db.updateUserPassword(user.getId(), snew_password);

					this.dispose();
					new UpdateDetails();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

		if (e.getSource() == back) {
			this.dispose();
			new UpdateDetails();
		}

	}

}

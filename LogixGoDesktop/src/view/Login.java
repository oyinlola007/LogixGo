package view;

import java.awt.Container;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.Helper;
import utils.LayoutComponents;
import utils.SessionManager;

public class Login extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JPanel mainPanel;
	private JTextField temail;
	private JPasswordField tpassword;
	private JButton login;
	private JButton signup;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.6);
	static int main_panel_height = (int) (screen_height * 0.45);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 90;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	Integer user_id = SessionManager.getSession();

	public Login() {
		if (user_id != null) {
			System.out.println("Session exists for user: " + user_id);
			new HomePage();
			return;
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Login");
		title.setFont(new Font("Arial", Font.BOLD, Constants.title_size));
		title.setSize(300, 40);
		title.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin);
		c.add(title);

		mainPanel = new JPanel(new GridLayout(5, 1, 0, 0));
		mainPanel.setBorder(new LineBorder(Constants.border_color, 2));
		mainPanel.setBackground(Constants.background_color);
		mainPanel.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin + title.getHeight() + 10);
		mainPanel.setSize(main_panel_width, main_panel_height);
		c.add(mainPanel);

		ComponentsGenerator componentsGenerator = new ComponentsGenerator(main_panel_width, entry_layout_height);

		LayoutComponents emailComponents = componentsGenerator.createEntryLayout("Email: ", 0.4, 0.6, false);
		mainPanel.add(emailComponents.panel);
		temail = emailComponents.textField;

		LayoutComponents passwordComponents = componentsGenerator.createEntryLayout("Password: ", 0.4, 0.6, true);
		mainPanel.add(passwordComponents.panel);
		tpassword = passwordComponents.passwordField;

		LayoutComponents loginComponents = componentsGenerator.createButtonLayout("Login", 0.4, 0.6, 0.6, true, 10,
				-15);
		mainPanel.add(loginComponents.panel);
		login = loginComponents.button;
		login.addActionListener(this);

		LayoutComponents orComponents = componentsGenerator.createLabelLayout("-------------- OR --------------", 0.4,
				0.6, 1, 0);
		mainPanel.add(orComponents.panel);

		LayoutComponents signupComponents = componentsGenerator.createButtonLayout("Not a member? Sign up", 0.4, 0.6, 1,
				false, -15, 0);
		mainPanel.add(signupComponents.panel);
		signup = signupComponents.button;
		signup.addActionListener(this);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login) {
			String semail = temail.getText();
			String spassword = tpassword.getText();

			// validate email no entry
			if (semail.isBlank()) {
				helper.showErrorMessage(this, "Email is required");
				return;
			}

			// validate email correct
			if (!helper.isValidEmailAddress(semail)) {
				helper.showErrorMessage(this, "Please enter a valid email address");
				return;
			}

			// validate password no entry
			if (spassword.isBlank()) {
				helper.showErrorMessage(this, "Password is required");
				return;
			}

			int user_id = -1;
			try {
				user_id = db.validateEmailAndPassword(semail, spassword);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (user_id == -1) {
				helper.showErrorMessage(this, "Incorrect email or password");
				return;
			}

			SessionManager.saveSession(user_id);

			this.dispose();
			new HomePage();

		}

		if (e.getSource() == signup) {
			this.dispose();
			new Signup();
		}

	}

	public static void main(String[] args) {
		new Login();
	}

}

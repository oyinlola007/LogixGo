package view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.Helper;
import utils.LayoutComponents;
import utils.SessionManager;

public class Signup extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	private JTextField tfirst_name;
	private JTextField tlast_name;
	private JTextField temail;
	private JTextField tphone;
	private JPasswordField tpassword;
	private JPasswordField tconfirm_password;
	private JTextField ttruck_reg_no;
	private JTextField ttruck_capacity;
	private JButton signup;
	private JComboBox<String> role;

	static String[] options = { "Customer", "Driver", "Scheduler" };
	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.85);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 20;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();

	public Signup() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Sign up");
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

		mainPanel = new JPanel(new GridLayout(10, 1, 0, 0));
		mainPanel.setBorder(new LineBorder(Constants.border_color, 2));
		mainPanel.setBackground(Constants.background_color);
		mainPanel.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin + title.getHeight() + 10);
		mainPanel.setSize(main_panel_width, main_panel_height);
		c.add(mainPanel);

		ComponentsGenerator componentsGenerator = new ComponentsGenerator(main_panel_width, entry_layout_height);

		LayoutComponents first_nameComponents = componentsGenerator.createEntryLayout("First name: ", 0.4, 0.6, false);
		mainPanel.add(first_nameComponents.panel);
		tfirst_name = first_nameComponents.textField;

		LayoutComponents last_nameComponents = componentsGenerator.createEntryLayout("Last name: ", 0.4, 0.6, false);
		mainPanel.add(last_nameComponents.panel);
		tlast_name = last_nameComponents.textField;

		LayoutComponents emailComponents = componentsGenerator.createEntryLayout("Email: ", 0.4, 0.6, false);
		mainPanel.add(emailComponents.panel);
		temail = emailComponents.textField;

		LayoutComponents phoneComponents = componentsGenerator.createEntryLayout("Telephone: ", 0.4, 0.6, false);
		mainPanel.add(phoneComponents.panel);
		tphone = phoneComponents.textField;

		LayoutComponents passwordComponents = componentsGenerator.createEntryLayout("Password: ", 0.4, 0.6, true);
		mainPanel.add(passwordComponents.panel);
		tpassword = passwordComponents.passwordField;

		LayoutComponents confirm_passwordComponents = componentsGenerator.createEntryLayout("Confirm Password: ", 0.4,
				0.6, true);
		mainPanel.add(confirm_passwordComponents.panel);
		tconfirm_password = confirm_passwordComponents.passwordField;

		LayoutComponents dropdownComponents = componentsGenerator.createDropdownLayout("Role: ", 0.4, 0.6, options);
		mainPanel.add(dropdownComponents.panel);
		role = dropdownComponents.dropdown;

		LayoutComponents truck_reg_noComponents = componentsGenerator.createEntryLayout("Truck registration number: ",
				0.4, 0.6, false);
		mainPanel.add(truck_reg_noComponents.panel);
		ttruck_reg_no = truck_reg_noComponents.textField;
		helper.setPanelVisibility(truck_reg_noComponents.panel, false);

		LayoutComponents truck_capacityComponents = componentsGenerator.createEntryLayout("Truck capacity (Kg): ", 0.4,
				0.6, false);
		mainPanel.add(truck_capacityComponents.panel);
		ttruck_capacity = truck_capacityComponents.textField;
		helper.setPanelVisibility(truck_capacityComponents.panel, false);

		LayoutComponents signupComponents = componentsGenerator.createButtonLayout("Sign up", 0.4, 0.6, 0.6, true, 0,
				-15);
		mainPanel.add(signupComponents.panel);
		signup = signupComponents.button;
		signup.addActionListener(this);

		role.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String srole = role.getSelectedItem().toString();

				if (srole.equals("Driver")) {
					helper.setPanelVisibility(truck_reg_noComponents.panel, true);
					helper.setPanelVisibility(truck_capacityComponents.panel, true);
				} else {
					helper.setPanelVisibility(truck_reg_noComponents.panel, false);
					helper.setPanelVisibility(truck_capacityComponents.panel, false);

					helper.clearField(ttruck_reg_no);
					helper.clearField(ttruck_capacity);
				}
			}
		});

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == signup) {

			String sfirst_name = tfirst_name.getText();
			String slast_name = tlast_name.getText();
			String semail = temail.getText();
			String sphone = tphone.getText();
			String spassword = tpassword.getText();
			String sconfirm_password = tconfirm_password.getText();
			String srole = role.getSelectedItem().toString();
			String struck_reg_no = ttruck_reg_no.getText();
			String struck_capacity = ttruck_capacity.getText();

			// validate no entry
			if (sfirst_name.isBlank() || slast_name.isBlank() || semail.isBlank() || sphone.isBlank()
					|| spassword.isBlank() || sconfirm_password.isBlank() || srole.isBlank() || spassword.isBlank()) {
				helper.showErrorMessage(this, "Please fill all required fields");
				return;
			}

			// validate email correct
			if (!helper.isValidEmailAddress(semail)) {
				helper.showErrorMessage(this, "Please enter a valid email address");
				return;
			}

			// validate email not exist
			try {
				if (db.doesEmailExist(semail)) {
					helper.showErrorMessage(this, "Email already exists");
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// validate correct phone
			if (!Helper.isNumeric(sphone) || (sphone.length() != 10)) {
				helper.showErrorMessage(this, "Please enter a valid phone number (10 digits only)");
				return;
			}

			// validate password match
			if (!spassword.equals(sconfirm_password)) {
				helper.showErrorMessage(this, "Passwords do not match");
				return;
			}

			if (srole.equals("Driver")) {
				// validate no entry
				if (struck_reg_no.isBlank() || struck_capacity.isBlank()) {
					helper.showErrorMessage(this, "Please enter valid truck details for the Driver role");
					return;
				}

				// validate correct phone
				if (!Helper.isNumeric(struck_capacity)) {
					helper.showErrorMessage(this, "Please enter a valid truck capacity");
					return;
				}
			}

			try {
				int user_id = db.insertUser(sfirst_name, slast_name, semail, sphone, spassword, srole);
				if (srole.equals("Driver")) {
					db.insertDriver(user_id, struck_reg_no, Integer.parseInt(struck_capacity));
				}
				
				SessionManager.saveSession(user_id);

				this.dispose();
				new HomePage();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource() == back) {
			this.dispose();
			new Login();
		}

	}

}

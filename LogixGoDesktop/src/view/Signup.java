package view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.LayoutComponents;

public class Signup extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	private JLabel first_name;
	private JTextField tfirst_name;
	private JLabel last_name;
	private JTextField tlast_name;
	private JLabel email;
	private JTextField temail;
	private JLabel phone;
	private JTextField tphone;
	private JLabel password;
	private JPasswordField tpassword;
	private JLabel confirm_password;
	private JPasswordField tconfirm_password;
	private JLabel truck_reg_no;
	private JTextField ttruck_reg_no;
	private JLabel truck_capacity;
	private JTextField ttruck_capacity;
	private JButton signup;
	private JComboBox<String> role;
	
	static String[] options = {"Customer", "Driver", "Scheduler"};
	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.85);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 20;

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
		first_name = first_nameComponents.label;
		tfirst_name = first_nameComponents.textField;
		
		LayoutComponents last_nameComponents = componentsGenerator.createEntryLayout("Last name: ", 0.4, 0.6, false);
		mainPanel.add(last_nameComponents.panel);
		last_name = last_nameComponents.label;
		tlast_name = last_nameComponents.textField;
		
		LayoutComponents emailComponents = componentsGenerator.createEntryLayout("Email: ", 0.4, 0.6, false);
		mainPanel.add(emailComponents.panel);
		email = emailComponents.label;
		temail = emailComponents.textField;
		
		LayoutComponents phoneComponents = componentsGenerator.createEntryLayout("Telephone: ", 0.4, 0.6, false);
		mainPanel.add(phoneComponents.panel);
		phone = phoneComponents.label;
		tphone = phoneComponents.textField;
		
		LayoutComponents passwordComponents = componentsGenerator.createEntryLayout("Password: ", 0.4, 0.6, true);
		mainPanel.add(passwordComponents.panel);
		password = passwordComponents.label;
		tpassword = passwordComponents.passwordField;

		LayoutComponents confirm_passwordComponents = componentsGenerator.createEntryLayout("Confirm Password: ", 0.4, 0.6, true);
		mainPanel.add(confirm_passwordComponents.panel);
		confirm_password = confirm_passwordComponents.label;
		tconfirm_password = confirm_passwordComponents.passwordField;
		
		LayoutComponents dropdownComponents = componentsGenerator.createDropdownLayout("Role: ", 0.4, 0.6, options);
		mainPanel.add(dropdownComponents.panel);
		role = dropdownComponents.dropdown;
		
		LayoutComponents truck_reg_noComponents = componentsGenerator.createEntryLayout("Truck registration number: ", 0.4, 0.6, false);
		mainPanel.add(truck_reg_noComponents.panel);
		truck_reg_no = truck_reg_noComponents.label;
		ttruck_reg_no = truck_reg_noComponents.textField;
		
		LayoutComponents truck_capacityComponents = componentsGenerator.createEntryLayout("Truck capacity (Kg): ", 0.4, 0.6, false);
		mainPanel.add(truck_capacityComponents.panel);
		truck_capacity = truck_capacityComponents.label;
		ttruck_capacity = truck_capacityComponents.textField;
		
		LayoutComponents signupComponents = componentsGenerator.createButtonLayout("Sign up", 0.4, 0.6, 0.6, true, 0, - 15);
		mainPanel.add(signupComponents.panel);
		signup = signupComponents.button;
		signup.addActionListener(this);
		
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.dispose();
			new Login();
		}

		if (e.getSource() == signup) {
			this.dispose();
			new UpdateDetails();
		}

	}

	public static void main(String[] args) {
		new Signup();
	}

}

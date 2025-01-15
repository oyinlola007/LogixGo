package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.LayoutComponents;

public class Login extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JPanel mainPanel;
	private JLabel email;
	private JTextField temail;
	private JLabel password;
	private JPasswordField tpassword;
	private JButton login;
	private JLabel or;
	private JButton signup;
	

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.6);
	static int main_panel_height = (int) (screen_height * 0.45);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 90;

	public Login() {
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
		email = emailComponents.label;
		temail = emailComponents.textField;
		
		LayoutComponents passwordComponents = componentsGenerator.createEntryLayout("Password: ", 0.4, 0.6, true);
		mainPanel.add(passwordComponents.panel);
		password = passwordComponents.label;
		tpassword = passwordComponents.passwordField;
		
		LayoutComponents loginComponents = componentsGenerator.createButtonLayout("Login", 0.4, 0.6, 0.6, true, 10, -15);
		mainPanel.add(loginComponents.panel);
		login = loginComponents.button;
		login.addActionListener(this);
		
		LayoutComponents orComponents = componentsGenerator.createLabelLayout("-------------- OR --------------", 0.4, 0.6, 1, 0);
		mainPanel.add(orComponents.panel);
		or = orComponents.label;
		
		LayoutComponents signupComponents = componentsGenerator.createButtonLayout("Not a member? Sign up", 0.4, 0.6, 1, false, -15, 0);
		mainPanel.add(signupComponents.panel);
		signup = signupComponents.button;
		signup.addActionListener(this);
		

		this.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == signup) {
			this.dispose();
			new Signup();
		}

	}

	
	public static void main(String[] args) {
		new Login();
	}

}

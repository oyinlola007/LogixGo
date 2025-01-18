package view;

import controller.DBManagement;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import model.User;
import utils.Constants;
import utils.Helper;
import utils.SessionManager;

public class HomePage extends JFrame implements ActionListener {

	private Container c;
	private JLabel welcome;
	private JButton logout;
	private JLabel todo;
	private JButton btn_1;
	private JButton btn_2;
	private JButton btn_3;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int y_start_margin = 90;
	static int x_start_margin = 90;
	static int entry_layout_height = Constants.entry_layout_height;

	User user;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();

	public HomePage() {
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

		welcome = new JLabel("Welcome, " + user.getFirstName());
		welcome.setFont(new Font("Arial", Font.BOLD, Constants.title_size));
		welcome.setSize(screen_width, 40);
		welcome.setLocation(x_start_margin, y_start_margin);
		c.add(welcome);

		logout = new JButton("Log out");
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		logout.addActionListener(this);
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		logout.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		logout.setForeground(Constants.text_button_color);
		logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logout.setSize(140, 40);
		logout.setLocation((int) screen_width - 230, y_start_margin);
		c.add(logout);

		todo = new JLabel("what do you want to do today?");
		todo.setFont(new Font("Arial", Font.PLAIN, Constants.normal_text_size));
		todo.setSize(screen_width, 40);
		todo.setLocation(x_start_margin, y_start_margin + welcome.getHeight() + 20);
		c.add(todo);

		btn_1 = new JButton("Create new delivery");
		btn_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		btn_1.setSize((int) (screen_width * 0.4), entry_layout_height);
		btn_1.setLocation((int) (screen_width * 0.5), todo.getLocation().y + todo.getHeight() + 30);
		btn_1.addActionListener(this);
		c.add(btn_1);

		btn_2 = new JButton("View deliveries");
		btn_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_2.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		btn_2.setSize((int) (screen_width * 0.4), entry_layout_height);
		btn_2.setLocation((int) (screen_width * 0.5), btn_1.getLocation().y + btn_1.getHeight() + 20);
		btn_2.addActionListener(this);
		c.add(btn_2);

		btn_3 = new JButton("Update details");
		btn_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_3.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		btn_3.setSize((int) (screen_width * 0.4), entry_layout_height);
		btn_3.setLocation((int) (screen_width * 0.5), btn_2.getLocation().y + btn_2.getHeight() + 20);
		btn_3.addActionListener(this);
		c.add(btn_3);

		if (user.getRole().equals("Scheduler")) {
			btn_1.setText("Assign Delivery");
			btn_2.setText("Generate schedule doc");
		} else if (user.getRole().equals("Driver")) {
			btn_1.setText("View pending missions");
			btn_2.setText("View past missions");
		}

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_1) {
			if (user.getRole().equals("Customer")) {
				this.dispose();
				new NewDeliveryProducts(new ArrayList<>());
			} else if (user.getRole().equals("Driver")) {
			} else if (user.getRole().equals("Scheduler")) {
			}
		}
		
		if (e.getSource() == btn_3) {
			this.dispose();
			new UpdateDetails();
		}

		if (e.getSource() == logout) {
			SessionManager.clearSession();
			
			this.dispose();
			new Login();
		}

	}

}

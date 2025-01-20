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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import model.DeliveryDetails;
import model.User;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.Helper;
import utils.LayoutComponents;
import utils.SessionManager;

public class CreateNewRoute extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	private JTextField tdate;
	private JComboBox<String> driver;
	private JButton create_route;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.6);
	static int main_panel_height = (int) (screen_height * 0.3);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 120;

	User user;
	String[] drivers_list;
	DeliveryDetails delivery;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();

	public CreateNewRoute(DeliveryDetails delivery) {
		try {
			user = db.getUserById(user_id);
			drivers_list = db.getDriversList();
			this.delivery = delivery;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Create New Route");
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

		mainPanel = new JPanel(new GridLayout(3, 1, 0, 0));
		mainPanel.setBorder(new LineBorder(Constants.border_color, 2));
		mainPanel.setBackground(Constants.background_color);
		mainPanel.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin + title.getHeight() + 10);
		mainPanel.setSize(main_panel_width, main_panel_height);
		c.add(mainPanel);

		ComponentsGenerator componentsGenerator = new ComponentsGenerator(main_panel_width, entry_layout_height);

		LayoutComponents dateComponents = componentsGenerator.createEntryLayout("Delivery Date: ", 0.4, 0.6, false);
		mainPanel.add(dateComponents.panel);
		tdate = dateComponents.textField;
		tdate.setText(delivery.getDate());
		tdate.setEditable(false);

		LayoutComponents dropdownComponents = componentsGenerator.createDropdownLayout("Driver: ", 0.4, 0.6,
				drivers_list);
		mainPanel.add(dropdownComponents.panel);
		driver = dropdownComponents.dropdown;

		LayoutComponents saveComponents = componentsGenerator.createButtonLayout("Create Route", 0.4, 0.6, 0.6, true, 0,
				-15);
		mainPanel.add(saveComponents.panel);
		create_route = saveComponents.button;
		create_route.addActionListener(this);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == create_route) {

			String sdriver = driver.getSelectedItem().toString();
			String[] parts = sdriver.split(", ");
			int deliveryId = Integer.parseInt(parts[1]);

			// validate no entry
			if (sdriver.isBlank()) {
				helper.showErrorMessage(this, "Please select a driver");
				return;
			}

			try {
				db.insertRoute(delivery.getDate(), deliveryId, "pending");
				this.dispose();
				new AssignDeliveryToRoute(delivery);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == back) {
			this.dispose();
			new AssignDeliveryToRoute(delivery);
		}

	}

}

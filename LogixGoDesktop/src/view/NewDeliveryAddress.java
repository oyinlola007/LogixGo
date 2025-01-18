package view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import controller.DBManagement;
import model.User;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.Helper;
import utils.LayoutComponents;
import utils.SessionManager;

public class NewDeliveryAddress extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	private JTextField tadd_ln_1;
	private JTextField tadd_ln_2;
	private JTextField tcity;
	private JTextField tregion;
	private JTextField tzip;
	private JDatePickerImpl datePicker;
	private JButton send_order;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.7);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 70;

	User user;
	ArrayList<String> selected_products;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();

	public NewDeliveryAddress(ArrayList<String> selected_products) {
		this.selected_products = selected_products;
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

		title = new JLabel("Delivery Details");
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

		mainPanel = new JPanel(new GridLayout(7, 1, 0, 0));
		mainPanel.setBorder(new LineBorder(Constants.border_color, 2));
		mainPanel.setBackground(Constants.background_color);
		mainPanel.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin + title.getHeight() + 10);
		mainPanel.setSize(main_panel_width, main_panel_height);
		c.add(mainPanel);

		ComponentsGenerator componentsGenerator = new ComponentsGenerator(main_panel_width, entry_layout_height);

		LayoutComponents datePickerComponents = componentsGenerator.createDatepicker("Delivery date: ", 0.4, 0.6);
		mainPanel.add(datePickerComponents.panel);
		datePicker = datePickerComponents.datePicker;

		LayoutComponents add_ln_1Components = componentsGenerator.createEntryLayout("Address line 1: ", 0.4, 0.6,
				false);
		mainPanel.add(add_ln_1Components.panel);
		tadd_ln_1 = add_ln_1Components.textField;

		LayoutComponents add_ln_2Components = componentsGenerator.createEntryLayout("Address line 2: ", 0.4, 0.6,
				false);
		mainPanel.add(add_ln_2Components.panel);
		tadd_ln_2 = add_ln_2Components.textField;

		LayoutComponents cityComponents = componentsGenerator.createEntryLayout("City: ", 0.4, 0.6, false);
		mainPanel.add(cityComponents.panel);
		tcity = cityComponents.textField;

		LayoutComponents regionComponents = componentsGenerator.createEntryLayout("Region: ", 0.4, 0.6, false);
		mainPanel.add(regionComponents.panel);
		tregion = regionComponents.textField;

		LayoutComponents zipComponents = componentsGenerator.createEntryLayout("Zip code: ", 0.4, 0.6, false);
		mainPanel.add(zipComponents.panel);
		tzip = zipComponents.textField;

		LayoutComponents saveComponents = componentsGenerator.createButtonLayout("Send order", 0.4, 0.6, 0.6, true, 0,
				-15);
		mainPanel.add(saveComponents.panel);
		send_order = saveComponents.button;
		send_order.addActionListener(this);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == send_order) {

			String sadd_ln_1 = tadd_ln_1.getText();
			String sadd_ln_2 = tadd_ln_2.getText();
			String scity = tcity.getText();
			String sregion = tregion.getText();
			String szip = tzip.getText();
			Date selectedDate = (Date) datePicker.getModel().getValue();

			// validate no entry
			if (selectedDate == null || sadd_ln_1.isBlank() || sadd_ln_2.isBlank() || scity.isBlank()
					|| sregion.isBlank() || szip.isBlank()) {
				helper.showErrorMessage(this, "Please fill all required fields");
				return;
			}

			// Convert the selected Date to LocalDate
			LocalDate selectedLocalDate = selectedDate.toInstant().atZone(java.time.ZoneId.systemDefault())
					.toLocalDate();

			// Get today's date
			LocalDate today = LocalDate.now();

			// Check if the selected date is before today
			if (selectedLocalDate.isBefore(today)) {
				helper.showErrorMessage(this, "Selected date is before today. Please choose a valid date.");
				return;
			}

			// validate correct zip
			if (!Helper.isNumeric(szip) || (szip.length() != 5)) {
				helper.showErrorMessage(this, "Please enter a valid zip code (5 digits only)");
				return;
			}

			try {
				db.insertDeliveryAndItems(selected_products, user.getId(), selectedLocalDate.toString(), sadd_ln_1,
						sadd_ln_2, scity, sregion, szip, "pending");

				this.dispose();
				new HomePage();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource() == back) {
			this.dispose();
			new NewDeliveryProducts(selected_products);
		}

	}

}

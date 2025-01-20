package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import model.DeliveryDetails;
import model.ProductDetails;
import model.User;
import utils.Constants;
import utils.Helper;
import utils.SessionManager;

public class ViewParticularDelivery extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	JPanel scrollablePanel;
	JLabel ldate;
	JLabel lweight;
	JLabel laddress;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.8);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 40;
	static String[] products;

	User user;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();
	DeliveryDetails delivery;

	public ViewParticularDelivery(int deliveryId) {
		try {
			user = db.getUserById(user_id);
			delivery = db.getDeliveryDetails(deliveryId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel();
		if (delivery.getStatus().equals("pending")) {
			title.setText("Pending");
			title.setForeground(Constants.text_red_color);
		} else {
			title.setText("Delivered");
			title.setForeground(Constants.text_green_color);
		}
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

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new LineBorder(Constants.border_color, 2));
		mainPanel.setBackground(Constants.background_color);
		mainPanel.setLocation((int) ((screen_width - main_panel_width) * 0.5), y_start_margin + title.getHeight() + 10);
		mainPanel.setSize(main_panel_width, main_panel_height);
		c.add(mainPanel);

		scrollablePanel = new JPanel(new GridBagLayout());
		scrollablePanel.setAlignmentY(Component.TOP_ALIGNMENT);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weightx = 1.0;
		constraints.gridx = 0;

		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.add(scrollablePanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(wrapperPanel);
		scrollPane.setPreferredSize(new Dimension(580, 350));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		bottomPanel.setBackground(Constants.background_color);
		bottomPanel.setPreferredSize(new Dimension(main_panel_width, entry_layout_height * 2));

		ldate = new JLabel("Delivery date: " + delivery.getDate(), SwingConstants.CENTER);
		ldate.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		ldate.setPreferredSize(new Dimension((int) (main_panel_width * 0.4), entry_layout_height - 30));
		bottomPanel.add(ldate);

		lweight = new JLabel("Total weight: " + String.valueOf(delivery.getTotalWeight()) + " Kg",
				SwingConstants.CENTER);
		lweight.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		lweight.setPreferredSize(new Dimension((int) (main_panel_width * 0.4), entry_layout_height - 30));
		bottomPanel.add(lweight);

		laddress = new JLabel(
				delivery.getAddressLine1() + ", " + delivery.getCity() + " (" + delivery.getZipCode() + ") ",
				SwingConstants.CENTER);
		laddress.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		laddress.setPreferredSize(new Dimension((int) (main_panel_width * 0.8), entry_layout_height - 30));
		bottomPanel.add(laddress);

		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		populateScrollablePanel(constraints, delivery.getProducts());

		this.setVisible(true);

	}

	private JPanel createRow(JPanel scrollablePanel, String field1, int field2) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
		rowPanel.setBorder(new LineBorder(Constants.border_color, 2));

		JLabel label1 = new JLabel(field1, SwingConstants.RIGHT);
		label1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label1.setPreferredSize(new Dimension((int) (main_panel_width * 0.35), entry_layout_height - 30));

		JLabel label2 = new JLabel(field2 + " Kg", SwingConstants.LEFT);
		label2.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label2.setPreferredSize(new Dimension((int) (main_panel_width * 0.25), entry_layout_height - 30));

		rowPanel.add(label1);
		rowPanel.add(label2);

		return rowPanel;
	}

	private void populateScrollablePanel(GridBagConstraints constraints, List<ProductDetails> products) {
		for (ProductDetails product : products) {
			String productName = product.getProductName();
			int productWeight = product.getProductWeight();

			// Add a new row
			constraints.gridy = scrollablePanel.getComponentCount();
			JPanel newRow = createRow(scrollablePanel, productName, productWeight);
			scrollablePanel.add(newRow, constraints);
		}

		// Refresh the scrollablePanel
		scrollablePanel.revalidate();
		scrollablePanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.dispose();
			new ViewDeliveries();
		}

	}

}

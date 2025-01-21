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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import model.DeliveryDetails;
import model.RouteDeliveryDetails;
import model.User;
import utils.Constants;
import utils.Helper;
import utils.SessionManager;

public class ViewRoute extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	JPanel scrollablePanel;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.7);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 70;
	static String warehouse_address;

	User user;
	ArrayList<RouteDeliveryDetails> routeDeliveries;
	DeliveryDetails delivery;
	int maxOrder;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();

	public ViewRoute(int route_id, DeliveryDetails delivery) {
		try {
			user = db.getUserById(user_id);
			routeDeliveries = db.getRouteDeliveries(route_id);
			warehouse_address = db.getConstantValue("warehouse_address");
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

		title = new JLabel("Route Details");
		title.setFont(new Font("Arial", Font.BOLD, Constants.title_size));
		title.setSize((int) (main_panel_width * 0.8), 40);
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

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
		topPanel.setBackground(Constants.background_color);

		JLabel label1 = new JLabel("(Start) " + "Warehouse: " + warehouse_address, SwingConstants.LEFT);
		label1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label1.setPreferredSize(new Dimension((int) (main_panel_width * 0.9), entry_layout_height * 2 - 60));

		topPanel.add(label1);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
		bottomPanel.setBackground(Constants.background_color);

		JLabel label2 = new JLabel("(End) " + "Warehouse: " + warehouse_address, SwingConstants.LEFT);
		label2.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label2.setPreferredSize(new Dimension((int) (main_panel_width * 0.9), entry_layout_height * 2 - 60));

		bottomPanel.add(label2);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

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

		mainPanel.add(scrollPane, BorderLayout.CENTER);
		populateScrollablePanel(constraints, routeDeliveries);

		this.setVisible(true);
		
		if (routeDeliveries.size() == 0) {
			helper.showInfoMessage(this, "No data available for route", "");
		}

	}

	private JPanel createRow(JPanel scrollablePanel, String address, int order) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		rowPanel.setBorder(new LineBorder(Constants.border_color, 2));

		JLabel label1 = new JLabel(address, SwingConstants.LEFT);
		label1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label1.setPreferredSize(new Dimension((int) (main_panel_width * 0.6), entry_layout_height * 2 - 60));

		JLabel label2 = new JLabel("(Stop " + order + ")", SwingConstants.LEFT);
		label2.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label2.setPreferredSize(new Dimension((int) (main_panel_width * 0.2), entry_layout_height * 2 - 60));

		rowPanel.add(label1);
		rowPanel.add(label2);
		return rowPanel;
	}

	private void populateScrollablePanel(GridBagConstraints constraints,
			ArrayList<RouteDeliveryDetails> routeDeliveries) {
		for (RouteDeliveryDetails routeDetails : routeDeliveries) {

			String address = routeDetails.getAddressLine1() + ", " + routeDetails.getCity() + " ("
					+ routeDetails.getZipCode() + ") ";
			int order = routeDetails.getOrder();

			// Add a new row
			constraints.gridy = scrollablePanel.getComponentCount();
			JPanel newRow = createRow(scrollablePanel, address, order);
			scrollablePanel.add(newRow, constraints);
		}

		// Refresh the scrollablePanel
		scrollablePanel.revalidate();
		scrollablePanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == back) {
			if (delivery == null) {
				this.dispose();
				new Routes();

			} else {
				this.dispose();
				new AssignDeliveryToRoute(delivery);
			}
		}
	}

}

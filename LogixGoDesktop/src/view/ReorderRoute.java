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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.DBManagement;
import model.DeliveryDetails;
import model.RouteDeliveryDetails;
import model.User;
import utils.Constants;
import utils.Helper;
import utils.SessionManager;

public class ReorderRoute extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JButton save_route_order;
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

	public ReorderRoute(int route_id) {
		try {
			user = db.getUserById(user_id);
			routeDeliveries = db.getRouteDeliveries(route_id);
			warehouse_address = db.getConstantValue("warehouse_address");
			maxOrder = getMaxOrderValue(routeDeliveries);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Reorder Route");
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

		save_route_order = new JButton("Save route order");
		save_route_order.addActionListener(this);
		save_route_order.setHorizontalAlignment(SwingConstants.CENTER);
		save_route_order.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		save_route_order.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		save_route_order.setSize((int) (main_panel_width * 0.4), entry_layout_height - 10);
		save_route_order.setLocation((int) ((0.6 * main_panel_width) + (0.5 * (screen_width - main_panel_width))),
				y_start_margin + title.getHeight() + mainPanel.getHeight() + 30);
		c.add(save_route_order);

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

	}

	private JPanel createRow(JPanel scrollablePanel, String address, int order, int deliveryId) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		rowPanel.setBorder(new LineBorder(Constants.border_color, 2));

		JLabel label1 = new JLabel(address, SwingConstants.LEFT);
		label1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label1.setPreferredSize(new Dimension((int) (main_panel_width * 0.7), entry_layout_height * 2 - 60));

		JSpinner spinner_qty = new JSpinner(new SpinnerNumberModel(order, 1, maxOrder, 1));
		spinner_qty.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		spinner_qty.setPreferredSize(new Dimension((int) (main_panel_width * 0.1), entry_layout_height - 30));

		JLabel invisibleLabel = new JLabel(String.valueOf(deliveryId));
		invisibleLabel.setVisible(false);

		rowPanel.add(label1);
		rowPanel.add(spinner_qty);
		rowPanel.add(invisibleLabel);

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
			JPanel newRow = createRow(scrollablePanel, address, order, routeDetails.getDeliveryId());
			scrollablePanel.add(newRow, constraints);
		}

		// Refresh the scrollablePanel
		scrollablePanel.revalidate();
		scrollablePanel.repaint();
	}

	public int getMaxOrderValue(List<RouteDeliveryDetails> routeDeliveries) {
		int maxOrder = 0; // Default to 0 if the list is empty
		for (RouteDeliveryDetails delivery : routeDeliveries) {
			if (delivery.getOrder() > maxOrder) {
				maxOrder = delivery.getOrder();
			}
		}
		return maxOrder;
	}

	private boolean validateRows() {
		Set<Integer> encounteredOrders = new HashSet<>();
		boolean allValid = true;

		for (Component component : scrollablePanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel rowPanel = (JPanel) component;

				JSpinner spinner = (JSpinner) rowPanel.getComponent(1);

				// Get the spinner value
				int orderValue = (int) spinner.getValue();

				// Check if the value is within range and not duplicate
				if (orderValue < 1 || orderValue > maxOrder || encounteredOrders.contains(orderValue)) {
					allValid = false;
					break;
				}

				// Add the value to the set
				encounteredOrders.add(orderValue);
			}
		}

		// Ensure all values from 1 to maxOrder are present
		for (int i = 1; i <= maxOrder; i++) {
			if (!encounteredOrders.contains(i)) {
				allValid = false;
				break;
			}
		}

		return allValid;
	}

	private void updateRoutes() {
		for (Component component : scrollablePanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel rowPanel = (JPanel) component;

				JSpinner spinner = (JSpinner) rowPanel.getComponent(1);
				JLabel label = (JLabel) rowPanel.getComponent(2);

				int orderValue = (int) spinner.getValue();
				int deliveryId = Integer.valueOf(label.getText());

				try {
					db.updateRouteDeliveryOrder(routeDeliveries.get(0).getRouteId(), deliveryId, orderValue);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save_route_order) {
			if (validateRows()) {
				updateRoutes();
				this.dispose();
				new Routes();
				
			} else {
				helper.showErrorMessage(this, "Ensure all addresses are filled correctly from 1 to " + maxOrder);
			}
		}

		if (e.getSource() == back) {
			this.dispose();
			new Routes();
		}
	}

}

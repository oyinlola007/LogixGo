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
import model.DriverRouteDetails;
import model.RouteDetails;
import model.User;
import utils.Constants;
import utils.Helper;
import utils.SessionManager;

public class PastMissions extends JFrame implements ActionListener {

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
	static String[] products;

	User user;
	ArrayList<DriverRouteDetails> routesForDriver;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();
	int user_id = SessionManager.getSession();

	public PastMissions() {
		try {
			user = db.getUserById(user_id);
			routesForDriver = db.getPastRoutesForDriver(user_id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Past Missions");
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
		populateScrollablePanel(constraints, routesForDriver);

		this.setVisible(true);
		
		if (routesForDriver.size() == 0) {
			helper.showInfoMessage(this, "No data available for driver", "");
		}

	}

	private JPanel createRow(JPanel scrollablePanel, String field1, String field2, int routeId) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		rowPanel.setBorder(new LineBorder(Constants.border_color, 2));

		JLabel label1 = new JLabel("<html>" + field1 + "</b><br/>" + field2 + "</html>", SwingConstants.LEFT);
		label1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label1.setPreferredSize(new Dimension((int) (main_panel_width * 0.4), entry_layout_height * 2 - 60));

		JButton viewButton = new JButton("View");
		viewButton.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		viewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewButton.setPreferredSize(new Dimension((int) (main_panel_width * 0.15), entry_layout_height - 30));
		viewButton.addActionListener(e -> {
			this.dispose();
			new DriverViewDeliveries(routeId, 2);
		});

		rowPanel.add(label1);
		rowPanel.add(viewButton);

		return rowPanel;
	}

	private void populateScrollablePanel(GridBagConstraints constraints,
			ArrayList<DriverRouteDetails> routesForDriver) {
		for (DriverRouteDetails routeDetails : routesForDriver) {
			String weight = "Total weight: " + routeDetails.getTotalWeight() + " Kg";
			String date = "Delivery Date: " + routeDetails.getDate();
			int routeId = routeDetails.getRouteId();

			// Add a new row
			constraints.gridy = scrollablePanel.getComponentCount();
			JPanel newRow = createRow(scrollablePanel, weight, date, routeId);
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
			new HomePage();
		}
	}

}

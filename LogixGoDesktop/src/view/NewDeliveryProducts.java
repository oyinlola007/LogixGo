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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import controller.DBManagement;
import utils.Constants;
import utils.Helper;

public class NewDeliveryProducts extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	JButton continueButton;
	JPanel scrollablePanel;
	JButton addButton;
	JLabel lweight;

	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.8);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 40;
	static String[] products;

	DBManagement db = new DBManagement();
	Helper helper = new Helper();

	public NewDeliveryProducts(ArrayList<String> selected_products) {
		try {
			products = db.getAllProductNames();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("New Delivery");
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

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		topPanel.setBackground(Constants.background_color);

		JLabel lproduct = new JLabel("Product:", SwingConstants.RIGHT);
		lproduct.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		lproduct.setPreferredSize(new Dimension((int) (main_panel_width * 0.15), entry_layout_height - 30));
		topPanel.add(lproduct);

		JComboBox<String> product_dropdown = new JComboBox<>(products);
		product_dropdown.setFont(new Font("Arial", Font.PLAIN, Constants.input_field_size));
		product_dropdown.setBorder(new LineBorder(Constants.border_color, 2));
		product_dropdown.setPreferredSize(new Dimension((int) (main_panel_width * 0.3), entry_layout_height - 30));
		topPanel.add(product_dropdown);

		product_dropdown.setUI(new BasicComboBoxUI() {
			@Override
			protected void installDefaults() {
				super.installDefaults();
				comboBox.setPreferredSize(new Dimension((int) (main_panel_width * 0.3), entry_layout_height - 30));
			}
		});

		JLabel lqty = new JLabel("Qty:", SwingConstants.RIGHT);
		lqty.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		lqty.setPreferredSize(new Dimension((int) (main_panel_width * 0.1), entry_layout_height - 30));
		topPanel.add(lqty);

		JSpinner spinner_qty = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_qty.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		spinner_qty.setPreferredSize(new Dimension((int) (main_panel_width * 0.1), entry_layout_height - 30));
		topPanel.add(spinner_qty);

		addButton = new JButton("Add");
		addButton.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButton.setPreferredSize(new Dimension((int) (main_panel_width * 0.2), entry_layout_height - 30));
		topPanel.add(addButton);

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

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
		bottomPanel.setBackground(Constants.background_color);

		lweight = new JLabel("Total weight = 0 Kg", SwingConstants.CENTER);
		lweight.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		lweight.setPreferredSize(new Dimension((int) (main_panel_width * 0.5), entry_layout_height - 30));
		bottomPanel.add(lweight);

		continueButton = new JButton("Continue");
		continueButton.addActionListener(this);
		continueButton.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		continueButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		continueButton.setPreferredSize(new Dimension((int) (main_panel_width * 0.3), entry_layout_height - 20));
		bottomPanel.add(continueButton);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		addButton.addActionListener(e -> {
			String sproduct = product_dropdown.getSelectedItem().toString();
			String sqty = spinner_qty.getValue().toString();

			if (sproduct.isEmpty()) {
				helper.showErrorMessage(this, "Please select product");
			} else if (sqty.equals("0")) {
				helper.showErrorMessage(this, "Quantity must be between 1 - 100");
			} else {
				boolean productExists = false;

				// Check if the product already exists in the scrollablePanel
				for (Component component : scrollablePanel.getComponents()) {
					if (component instanceof JPanel) {
						JPanel rowPanel = (JPanel) component;
						JLabel label1 = (JLabel) rowPanel.getComponent(0);
						if (label1.getText().equals(sproduct)) {
							JSpinner spinner = (JSpinner) rowPanel.getComponent(1);
							int currentQty = (int) spinner.getValue();
							spinner.setValue(currentQty + Integer.parseInt(sqty));
							productExists = true;
							break;
						}
					}
				}

				// If the product doesn't exist, add a new row
				if (!productExists) {
					constraints.gridy = scrollablePanel.getComponentCount();
					JPanel newRow = createRow(scrollablePanel, sproduct, Integer.parseInt(sqty));
					scrollablePanel.add(newRow, constraints);
					scrollablePanel.revalidate();
					scrollablePanel.repaint();
				}

				spinner_qty.setValue(0);
			}

			lweight.setText("Total weight = " + calculateTotalWeight() + " Kg");
		});

		populateScrollablePanel(constraints, selected_products);

		this.setVisible(true);

	}

	private JPanel createRow(JPanel scrollablePanel, String field1, int field2) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		rowPanel.setBorder(new LineBorder(Constants.border_color, 2));

		JLabel label1 = new JLabel(field1);
		label1.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		label1.setPreferredSize(new Dimension((int) (main_panel_width * 0.4), entry_layout_height - 30));

		JSpinner spinner = new JSpinner(new SpinnerNumberModel(field2, 0, 100, 1));
		spinner.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		spinner.setPreferredSize(new Dimension((int) (main_panel_width * 0.1), entry_layout_height - 30));
		spinner.addChangeListener(e -> {
			lweight.setText("Total weight = " + calculateTotalWeight() + " Kg");
		});

		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Arial", Font.PLAIN, Constants.input_label_size));
		deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButton.setPreferredSize(new Dimension((int) (main_panel_width * 0.2), entry_layout_height - 30));
		deleteButton.addActionListener(e -> {
			scrollablePanel.remove(rowPanel);
			scrollablePanel.revalidate();
			scrollablePanel.repaint();
			lweight.setText("Total weight = " + calculateTotalWeight() + " Kg");
		});

		rowPanel.add(label1);
		rowPanel.add(spinner);
		rowPanel.add(deleteButton);

		return rowPanel;
	}

	private void populateScrollablePanel(GridBagConstraints constraints, ArrayList<String> selectedProducts) {
		for (String entry : selectedProducts) {
			String[] parts = entry.split(", ");
			String productName = parts[0];
			int quantity = Integer.parseInt(parts[1]);

			// Add a new row
			constraints.gridy = scrollablePanel.getComponentCount();
			JPanel newRow = createRow(scrollablePanel, productName, quantity);
			scrollablePanel.add(newRow, constraints);
		}

		// Refresh the scrollablePanel
		scrollablePanel.revalidate();
		scrollablePanel.repaint();

		// Update the total weight
		lweight.setText("Total weight = " + calculateTotalWeight() + " Kg");
	}

	private ArrayList<String> getAllValues() {
		ArrayList<String> values = new ArrayList<>();
		for (Component component : scrollablePanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel rowPanel = (JPanel) component;
				JLabel label1 = (JLabel) rowPanel.getComponent(0);
				JSpinner spinner = (JSpinner) rowPanel.getComponent(1);
				values.add(label1.getText() + ", " + spinner.getValue());
			}
		}
		return values;
	}

	private int calculateTotalWeight() {
		int totalWeight = 0;

		for (Component component : scrollablePanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel rowPanel = (JPanel) component;

				JLabel label1 = (JLabel) rowPanel.getComponent(0);
				JSpinner spinner = (JSpinner) rowPanel.getComponent(1);

				String field1Text = label1.getText();
				int spinnerValue = (int) spinner.getValue();

				int productWeight = 0;
				try {
					productWeight = db.getProductWeight(field1Text);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				totalWeight += spinnerValue * productWeight;
			}
		}

		return totalWeight;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == continueButton) {
			if (getAllValues().size() == 0) {
				helper.showErrorMessage(this, "Add at least one product to proceed");
				return;
			}

			this.dispose();
			new NewDeliveryAddress(getAllValues());

		}

		if (e.getSource() == back) {
			this.dispose();
			new HomePage();
		}

	}

}

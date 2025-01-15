package view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import utils.ComponentsGenerator;
import utils.Constants;
import utils.LayoutComponents;

public class UpdateDetails extends JFrame implements ActionListener {

	private Container c;
	private JLabel title;
	private JButton back;
	private JPanel mainPanel;
	private JLabel email;
	private JTextField temail;
	private JLabel phone;
	private JTextField tphone;
	private JLabel truck_reg_no;
	private JTextField ttruck_reg_no;
	private JLabel truck_capacity;
	private JTextField ttruck_capacity;
	private JButton save;
	private JButton change_password;
	
	static int screen_width = Constants.screen_width;
	static int screen_height = Constants.screen_height;
	static int main_panel_width = (int) (screen_width * 0.8);
	static int main_panel_height = (int) (screen_height * 0.6);
	static int entry_layout_height = Constants.entry_layout_height;
	static int y_start_margin = 90;

	public UpdateDetails() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("LogixGo");
		setBounds(30, 60, screen_width, screen_height);
		this.setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Update Details");
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
		
		mainPanel = new JPanel(new GridLayout(6, 1, 0, 0));
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
		
		LayoutComponents phoneComponents = componentsGenerator.createEntryLayout("Telephone: ", 0.4, 0.6, false);
		mainPanel.add(phoneComponents.panel);
		phone = phoneComponents.label;
		tphone = phoneComponents.textField;
		
		LayoutComponents truck_reg_noComponents = componentsGenerator.createEntryLayout("Truck registration number: ", 0.4, 0.6, false);
		mainPanel.add(truck_reg_noComponents.panel);
		truck_reg_no = truck_reg_noComponents.label;
		ttruck_reg_no = truck_reg_noComponents.textField;
		
		LayoutComponents truck_capacityComponents = componentsGenerator.createEntryLayout("Truck capacity (Kg): ", 0.4, 0.6, false);
		mainPanel.add(truck_capacityComponents.panel);
		truck_capacity = truck_capacityComponents.label;
		ttruck_capacity = truck_capacityComponents.textField;
		
		LayoutComponents saveComponents = componentsGenerator.createButtonLayout("Save", 0.4, 0.6, 0.6, true, 0, - 15);
		mainPanel.add(saveComponents.panel);
		save = saveComponents.button;
		save.addActionListener(this);
		
		LayoutComponents change_passwordComponents = componentsGenerator.createButtonLayout("Change password", 0.4, 0.6, 0.6, false, 0, - 15);
		mainPanel.add(change_passwordComponents.panel);
		change_password = change_passwordComponents.button;
		change_password.addActionListener(this);
		
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.dispose();
			new Signup();
		}
		
		if (e.getSource() == change_password) {
			this.dispose();
			new ChangePassword();
		}


	}

	public static void main(String[] args) {
		new UpdateDetails();
	}

}

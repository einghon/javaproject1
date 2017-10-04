package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DoctorList extends JFrame implements ActionListener {
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	int width1 = (2 * size.width) / 3;
	int width2 = size.width / 3;
	int height = size.height;

	JTextField name, confee, contact, address;
	JLabel l1, l2, l3, l4, l5, l6;
	JPanel rightPanel, allPanel, leftPanel;
	JButton add, reset, refresh, print;
	DefaultTableModel model1;
	JTable table;
	String speciality[] = { "Audiologist", "Allergist", "Cardiologist", "Dentist", "Dermatologist", "Epidemiologist",
			"Neurologist", "Physiologist", "Radiologist", "Surgeon" };
	String col[] = { "Name", "Speciality", "Cons Fee", "Contact", "Address" };
	String url = "jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	String username = "root";
	String password = "new_password";
	JComboBox com;

	int i = 0, nameCount = 0;
	JScrollPane pane;
	Connection conn;
	Statement stmt;
	ResultSet rs;

	/* _______________Docotor List_______ */
	public JPanel DoctorList() {
		DisplayDoctor();
		AddDoctor();
		allPanel = new JPanel();
		allPanel.setLayout(null);

		leftPanel.setBounds(0, 0, width1, height);
		rightPanel.setBounds(width1, 0, width2, height);
		allPanel.add(leftPanel);
		allPanel.add(rightPanel);
		return allPanel;
	}

	public void DisplayDoctor() {
		String query = "SELECT * FROM DoctorRecord ORDER BY Name";
		model1 = new DefaultTableModel(col, 40);// setting column header and the
												// column number
		table = new JTable(model1) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};

		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);// for the
																// flexible
																// column width
		table.setPreferredScrollableViewportSize(table.getPreferredSize());// for
																			// the
																			// flexible
																			// column
																			// width

		pane = new JScrollPane(table);
		pane.setBounds(0, 0, 900, 600);

		refresh = new JButton("Refresh");
		refresh.setBounds(250, 625, 100, 20);
		refresh.addActionListener(this);

		// delete=new JButton("Delete");
		// delete.setBounds(400,625,100,20);
		// delete.addActionListener(this);

		print = new JButton("Print");
		print.setBounds(550, 625, 100, 20);
		print.addActionListener(this);

		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.add(pane);
		leftPanel.add(refresh);
		// leftPanel.add(delete);
		leftPanel.add(print);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name1 = rs.getString(1);
				String speciality1 = rs.getString(2);
				String consfee = rs.getString(3);
				String contact1 = rs.getString(4);
				String address1 = rs.getString(5);

				table.setValueAt(name1, i, 0);
				table.setValueAt(speciality1, i, 1);
				table.setValueAt(consfee, i, 2);
				table.setValueAt(contact1, i, 3);
				table.setValueAt(address1, i, 4);
				i++;
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, cnfe, "Error", JOptionPane.ERROR_MESSAGE);

		} catch (SQLException sqle) {
			JOptionPane.showConfirmDialog(null, "Name Already exit.Add or Not", "Name Add Or Not",
					JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	/* _________________Adding Doctor_______ */
	public void AddDoctor() {
		l1 = new JLabel("Name");
		l1.setBounds(20, 0, 70, 50);
		l2 = new JLabel("Speciality");
		l2.setBounds(20, 90, 70, 50);
		l3 = new JLabel("Cons Fee");
		l3.setBounds(20, 180, 70, 50);
		l4 = new JLabel("Contact");
		l4.setBounds(20, 270, 70, 50);
		l5 = new JLabel("Address");
		l5.setBounds(20, 358, 110, 50);

		name = new JTextField("Mg Mg", 10);
		name.setBounds(150, 15, 170, 20);
		/* ___________________________place holder___________________________ */
		name.setForeground(Color.gray);

		name.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (name.getText().equals("Mg Mg")) {
					name.setText("");
					name.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (name.getText().isEmpty()) {
					name.setForeground(Color.gray);
					name.setText("Mg Mg");
				}
			}
		});
		/* ____________________________________________________ */
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = name.getText();
				if (name.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all required field!", "error",
							JOptionPane.ERROR_MESSAGE);
					name.setText("");
					name.requestFocus();
				} else {
					try {
						String regx = "^[a-zA-Z \\s]{1,}[\\.]{0,1}[a-zA-Z0-9 \\s]{0,}$";
						Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(txt);
						boolean m = matcher.find();
						if (m == false) {
							JOptionPane.showMessageDialog(null, "invalid name!");
							name.setText(" ");
							name.requestFocus();
						} else {
							com.requestFocus();
							// confee.requestFocus();
							// com.setSelectedIndex(1);
						}
						// confee.requestFocus();

					} catch (Exception exp) {
						JOptionPane.showMessageDialog(null, "invalid name!");
					}
				}
			}
		});

		com = new JComboBox(speciality);
		com.setBounds(150, 105, 170, 20);
		// com.addActionListener(this);

		confee = new JTextField("10000", 10);
		confee.setBounds(150, 195, 170, 20);
		/* ___________________place holder_____________________ */
		confee.setForeground(Color.gray);

		confee.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (confee.getText().equals("10000")) {
					confee.setText("");
					confee.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (confee.getText().isEmpty()) {
					confee.setForeground(Color.gray);
					confee.setText("10000");
				}
			}
		});
		/* ___________________________________________________ */

		confee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regxConfee();
			}
		});

		contact = new JTextField("9xxxxxxxx", 10);
		contact.setBounds(150, 285, 170, 20);
		/* ________________PlaceHolder________________________ */
		contact.setForeground(Color.gray);

		contact.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (contact.getText().equals("9xxxxxxxx")) {
					contact.setText("");
					contact.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (contact.getText().isEmpty()) {
					contact.setForeground(Color.gray);
					contact.setText("9xxxxxxxx");
				}
			}
		});
		/* _____________________________________________________________________ */

		contact.addActionListener(new ActionListener() {
			int ph;

			public void actionPerformed(ActionEvent e) {
				regxContact();
			}
		});

		address = new JTextField("address", 10);
		address.setBounds(150, 375, 170, 20);
		/* ___________________place holder___________________________ */
		address.setForeground(Color.gray);

		address.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (address.getText().equals("address")) {
					address.setText("");
					address.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (address.getText().isEmpty()) {
					address.setForeground(Color.gray);
					address.setText("address");
				}
			}
		});
		/* _______________________________________________ */

		address.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regxAddr();
			}
		});

		add = new JButton("Add");
		reset = new JButton("Reset");
		reset.setBounds(70, 570, 70, 20);
		add.setBounds(240, 570, 70, 20);
		reset.addActionListener(this);
		add.addActionListener(this);

		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.add(add);
		rightPanel.add(reset);
		rightPanel.add(l1);
		rightPanel.add(l2);
		rightPanel.add(l3);
		rightPanel.add(l4);
		rightPanel.add(l5);
		rightPanel.add(name);
		rightPanel.add(com);
		rightPanel.add(confee);
		rightPanel.add(contact);
		rightPanel.add(address);
	}

	public void actionPerformed(ActionEvent e) {

		/* _______________Reset_____________ */
		if (e.getSource() == reset) {
			name.setText("");
			confee.setText("");
			contact.setText("");
			address.setText("");

		}

		/* _________________Add__________ */
		if (e.getSource() == add) {
			if (regxName() && regxConfee() && regxContact() && regxAddr()) {

				/* _____________________Check Duplication________________ */
				Connection con;
				Statement st;
				String sql = "Select name from DoctorRecord where name= '" + name.getText() + "'";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, username, password);
					st = con.createStatement();
					ResultSet r = st.executeQuery(sql);
					if (r.next()) {
						int choice = JOptionPane.showConfirmDialog(null, "Name Already exit.Add or Not",
								"Name Add Or Not", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
						if (choice == JOptionPane.CANCEL_OPTION) {
							name.setText("");
							name.requestFocus();
						}
						if (choice == JOptionPane.OK_OPTION) {
							nameCount += 1;
						}
					}
					table.setValueAt(name.getText() + nameCount, i, 0);
					table.setValueAt((String) com.getSelectedItem(), i, 1);
					table.setValueAt(confee.getText(), i, 2);
					table.setValueAt(contact.getText(), i, 3);
					table.setValueAt(address.getText(), i, 4);
					i++;

					Connection conn;
					Statement stmt;
					String query = "INSERT INTO DoctorRecord VALUES ('" + name.getText() + "','"
							+ (String) com.getSelectedItem() + "','" + confee.getText() + "','" + contact.getText()
							+ "','" + address.getText() + "')";
					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(url, username, password);
						stmt = conn.createStatement();
						int res = stmt.executeUpdate(query);
						if (res == 1)
							JOptionPane.showMessageDialog(this, "Successfully Insert!");
						conn.close();
					} catch (ClassNotFoundException cnfe) {
						JOptionPane.showMessageDialog(null, cnfe, "Error", JOptionPane.ERROR_MESSAGE);

					} catch (SQLException sqle) {
						JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);

					}
				}

				catch (ClassNotFoundException cnfe) {
					JOptionPane.showMessageDialog(null, cnfe, "Error", JOptionPane.ERROR_MESSAGE);

				} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			/* _________________Refresh________________ */
			if (e.getSource() == refresh) {
				String query = "SELECT * FROM DoctorRecord ORDER BY Name";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(url, username, password);
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query);
					i = 0;
					while (rs.next()) {
						String name1 = rs.getString(1);
						String speciality1 = rs.getString(2);
						String consfee = rs.getString(3);
						String contact1 = rs.getString(4);
						String address1 = rs.getString(5);

						table.setValueAt(name1, i, 0);
						table.setValueAt(speciality1, i, 1);
						table.setValueAt(consfee, i, 2);
						table.setValueAt(contact1, i, 3);
						table.setValueAt(address1, i, 4);

						i++;
					}
					rs.close();
					conn.close();
				} catch (ClassNotFoundException cnfe) {
					JOptionPane.showMessageDialog(null, cnfe, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null, sqle, "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
			if (e.getSource() == print) {
				try {
					JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;
					MessageFormat headerFormat = new MessageFormat("Doctors'Record");
					boolean complete = table.print(

							mode, headerFormat, null);
					if (complete) {
						JOptionPane.showMessageDialog(this, "Printing Complete", "Printing Result",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (PrinterException pe) {
					JOptionPane.showMessageDialog(null, pe, "Error Printing:", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public boolean regxName() {
		return true;
	}

	public boolean regxConfee() {
		if (confee.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill all required field!", "error", JOptionPane.ERROR_MESSAGE);
			// confee.setText("");
			confee.requestFocus();
			return false;
		} else
			try {
				// JOptionPane.showMessageDialog(null, "invalid confee!");
				int con = Integer.parseInt(confee.getText());
				if (con == 0) {
					JOptionPane.showMessageDialog(null, "invalid confee!");
					confee.setText("");
					confee.requestFocus();
					return false;
				} else {
					int conf = Integer.parseInt(confee.getText());
					contact.requestFocus();
				}

			} catch (Exception exp) {
				JOptionPane.showMessageDialog(null, "invalid confee!");
				confee.setText("");
				confee.requestFocus();
			}

		return true;
	}

	public boolean regxContact() {
		if (contact.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill all required field!", "error", JOptionPane.ERROR_MESSAGE);
			contact.setText("");
			contact.requestFocus();
			return false;
		} else
			try {
				String phoneNo = contact.getText();
				if (phoneNo.length() != 9) {
					JOptionPane.showMessageDialog(null, "invalid phone no!");
					contact.setText("");
					contact.requestFocus();
					return false;
				} else if (phoneNo.charAt(0) != '7' && phoneNo.charAt(0) != '9' && phoneNo.charAt(0) != '4') {
					JOptionPane.showMessageDialog(null, "invalid phone no!");
					contact.setText("");
					contact.requestFocus();
					return false;
				} else {
					int ph = Integer.parseInt(contact.getText());
					address.requestFocus();
				}
			} catch (Exception exp) {
				JOptionPane.showMessageDialog(null, "invalid phone number!");
			}

		return true;
	}

	public boolean regxAddr() {
		if (address.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill all required filed!", "error", JOptionPane.ERROR_MESSAGE);
			address.requestFocus();
			return false;
		}

		return true;
	}

}

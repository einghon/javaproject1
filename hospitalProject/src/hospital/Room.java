package hospital;
import java.awt.BorderLayout;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Room extends JFrame implements ActionListener {
	 Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	 int width1=(2*size.width)/3;
	 int width2=size.width/3;
	 int height=size.height;
	 int i=0;
	Connection conn;
	Statement stmt;
	ResultSet rs,r;
	PreparedStatement ps;
    int row=0;
    JButton reset,save,delete,refresh;
    JPanel rightPanel,allPanel,leftPanel;
    DefaultTableModel model1;
 	JTable table;
 	String col[]={"Room Type","Room No","Room Per Charges","Free or Not"};
 	String comboxName[]={"General", "Deluxe"};
 	String url="jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false";
	String username="root";
	String password="new_password";

 	JScrollPane pane;
 	JTextField t,t1;
 	JLabel l,l1,l2;
 	JComboBox c;
 	
 	/*________For panel to find  room _____*/
 	public JPanel findR()
 	{
 		createTable();
 		findRoom();
 		DisplayRooms();
 		allPanel=new JPanel();
	    allPanel.setLayout(null);
	   

	     leftPanel.setBounds(0,0,width1,height);
		 rightPanel.setBounds(width1,0,width2,height);
	    
	    allPanel.add(leftPanel);
	    allPanel.add(rightPanel);
		return allPanel;
 	}
    
 	/*_____________creating table______*/
    public void createTable()
	{
		model1 = new DefaultTableModel(col,40);//setting column header and the column number
		table=new JTable(model1){@Override 
		public boolean isCellEditable(int arg0, int arg1) { return false; }};
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
		table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
		pane = new JScrollPane(table);
		
		leftPanel=new JPanel();
		leftPanel.setLayout(null);
		pane.setBounds(0,0,width1,height);
		
		leftPanel.add(pane);
			
	}
	public void findRoom()
    {
		l=new JLabel("Room Type");
		l.setBounds(20,110,70,50);
		l1=new JLabel("Room No");
		l1.setBounds(20,210,70,50);
		
		l2=new JLabel("Amount");
		l2.setBounds(20,320,70,50);
		
		c=new JComboBox(comboxName);
		c.setBounds(120,125,170,20);
		
		t=new JTextField("A1",10);
		t.setBounds(120,225,170,20);
		t.setForeground(Color.GRAY);
		t.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e){
				if(t.getText().equals("A1")){
					t.setText("");
					t.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e){
				if(t.getText().isEmpty()){
					t.setForeground(Color.gray);
					t.setText("Room No");
				}
			}
		}
				);
		
		t1=new JTextField(10);
		t1.setBounds(120,335,170,20);
		t1.setForeground(Color.GRAY);
		reset=new JButton("Reset");
		save=new JButton("Save");
		delete=new JButton("Delete");
		refresh=new JButton("Refresh");
		refresh.setBounds(40,450,100,20);
		refresh.addActionListener(this);	
		
		reset.setBounds(40,485,100,20);
		save.setBounds(170,485,100,20);
		delete.setBounds(300,485,100,20);
		reset.addActionListener(this);
		save.addActionListener(this);
		delete.addActionListener(this);
				 
		rightPanel=new JPanel();
		rightPanel.setLayout(null);
		rightPanel.add(l);rightPanel.add(l1);rightPanel.add(l2);
		rightPanel.add(c);
		rightPanel.add(t);rightPanel.add(t1);
		rightPanel.add(reset);
		rightPanel.add(save);
		rightPanel.add(delete);
		rightPanel.add(refresh);
	}
	/*__________DisplayRooms()_____________*/
	public void DisplayRooms(){
		
		String query="SELECT * FROM room ORDER BY amount";
		//String query="select r.RoomType,r.RoomNo,r.amount from room r,patient p where r.RoomNo<>p.RoomORBed";
		model1 = new DefaultTableModel(col,40);//setting column header and the column number
		table=new JTable(model1){@Override 
		public boolean isCellEditable(int arg0, int arg1) 
				{ return false; }};
        
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
		table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
		
		pane = new JScrollPane(table);
		pane.setBounds(0,0,width1,height-100);
		
		leftPanel=new JPanel();
		leftPanel.setLayout(null);
		leftPanel.add(pane);
     
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);

			while(rs.next()){
				String roomType=rs.getString(1);
				String roomNo=rs.getString(2);
				String amount=rs.getString(3);
				String FreeOrNot=rs.getString(4);
				
				table.setValueAt( roomType,i,0); 
				table.setValueAt(roomNo,i,1);
				table.setValueAt(amount,i,2);
				table.setValueAt(FreeOrNot,i, 3);
				i++;       
				}
		rs.close();
		conn.close();
		}
		catch(ClassNotFoundException cnfe){
			  JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
			   	
			
		}
		catch(SQLException sqle){
			  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
			   	
		}
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==refresh)
		{	String query="SELECT * FROM room ORDER BY roomno";
		
		String url="jdbc:mysql://localhost:3306/hospital";
	    String username="root";
	    String password="new_password";
	    
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url,username,password);
				stmt=conn.createStatement();
				rs=stmt.executeQuery(query);
				i=0;
				while(rs.next()){
					String roomtype=rs.getString(1);
					String roomnumber=rs.getString(2);
					String amount=rs.getString(3);
					String freeornot=rs.getString(4);
					table.setValueAt(roomtype,i,0); 
					table.setValueAt(roomnumber,i,1);
					table.setValueAt(amount,i,2);
					table.setValueAt(freeornot,i,3);
					  i++;       
					}
			rs.close();
			conn.close();
			/*______________Update Room__________________*/
			String update="Update room inner join patient set room.FreeOrNot='occupied' where room.RoomNo=patient.RoomORBed";
			try{
				Connection c;
				Statement s;
				 Class.forName("com.mysql.jdbc.Driver");
				 c= DriverManager.getConnection(url, username, password);
				 s = c.createStatement();
			     s.executeUpdate(update);	
			     //c.close();
				}
				catch (ClassNotFoundException cnfe){
			      System.out.println("Driver could not be loaded: " + cnfe);
				}
			 	catch (SQLException sqle){
			      System.out.println("SQL Exception thrown: " + sqle);
			
			 	}
			
			}
			
			
			catch(ClassNotFoundException cnfe){
				System.out.println("Driver could not loaded :"+cnfe);
				
			}
			catch(SQLException sqle){
				System.out.println("SQL Exception thrown :"+sqle);
			}
			//c.close();
		}
		if(e.getSource()==delete){
			if(table.getSelectedRow()!=-1){
				
				int choice = JOptionPane.showConfirmDialog(null, "Delete selected record?" , "Delete or not", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
				if (choice == JOptionPane.OK_OPTION) {
					int j=table.getSelectedRow();
					String room=(String)table.getValueAt(j,1);
					model1.removeRow(table.getSelectedRow());
			 
					Connection conn;
					Statement stmt;
			 	 
					String query ="DELETE FROM room WHERE RoomNo='"+room+"';";
					 
				try{			 
				 Class.forName("com.mysql.jdbc.Driver");
				 conn = DriverManager.getConnection(url, username, password);
				 stmt = conn.createStatement();
			     stmt.executeUpdate(query);	
			     conn.close();
				}
				catch (ClassNotFoundException cnfe){
			      System.out.println("Driver could not be loaded: " + cnfe);
				}
			 	catch (SQLException sqle){
			      System.out.println("SQL Exception thrown: " + sqle);
			 	}
				}
			}
	}
	
			
			if(e.getSource()==reset){
				
				c.setSelectedItem("General");
				t.setText("");
			
			}
			
			if(e.getSource()==save){
				 if (t.getText().equals("")) {
			           JOptionPane.showMessageDialog( this, "Please enter room no.","Error", JOptionPane.ERROR_MESSAGE);
			           return;
			         }
				 else
				 {
					 Connection con;
					 Statement st;
					 String sql="Select RoomNo from room where RoomNo= '" + t.getText() + "'";
					 try{			 
							Class.forName("com.mysql.jdbc.Driver");
							con = DriverManager.getConnection(url, username, password);
							 st= con.createStatement();
							 r=st.executeQuery(sql);
							 if(r.next()){
								 JOptionPane.showMessageDialog( this, "Room No. already exists","Error", JOptionPane.ERROR_MESSAGE);
							        t.setText("");
							        t.requestFocus();
							        t1.setText("");
							      // return;
							 }
						     
						 } 
						 catch(ClassNotFoundException cnfe){
							 JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
								
						 }
						 catch (SQLException sqle){
							  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
						   	
						 } 
					table.setValueAt(c.getSelectedItem(),i,0); 
					table.setValueAt(t.getText(),i,1);
					table.setValueAt(t1.getText(),i,2);
					table.setValueAt("FREE",i,3);
					i++;
			 
					Connection conn;
					Statement stmt;
					  	
					String query1= "insert into Room (roomtype,roomNo,amount)values('"+ c.getSelectedItem() + "','"+t.getText() + "','" +t1.getText() + "')";	 

					try{			 
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(url, username, password);
						 stmt = conn.createStatement();
					     int res=stmt.executeUpdate(query1);	
					     if(res==1) 
								JOptionPane.showMessageDialog(this, "Successfully Insert!");
					     t.setText("");
					        t.requestDefaultFocus();
					        t.requestFocus();
					        t1.setText("");
					     
					     conn.close();
					 } 
					 catch(ClassNotFoundException cnfe){
						 JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
							
					 }
					 catch (SQLException sqle){
						  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
					   	
					 } 
					
				}
			 }
				
			}
			
	}




	
	

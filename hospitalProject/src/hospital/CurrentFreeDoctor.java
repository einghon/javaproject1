package hospital;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

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

public class CurrentFreeDoctor extends JFrame 

{
	JTextField text_box_for_drname;
    JPanel panelforall=new JPanel(new BorderLayout());
    JPanel panelfortitle=new JPanel();
    JPanel panelfortable=new JPanel();
    JLabel l1=new JLabel();
    Connection conn;
		Statement stmt;
		ResultSet rs;
	    int row=0;
		String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	 	String username="root";
	 	String password="rootroot";
	 	
		int hour =LocalDateTime.now().getHour();
		int hour_for_query;
		
		DefaultTableModel model1;
		JScrollPane pane;
		JTable table;
		
		String col[]={"Name","Contact","Speciality","Address"};

 	 	public static void main(String[] args) throws IOException
 	 	{
 	 		new CurrentFreeDoctor();
 	 	}
 		
 	 	public CurrentFreeDoctor()
 	 	{      
 			l1.setText("Current Free Doctors");
 			l1.setFont(new Font("Serif",Font.BOLD,24));
 			panelfortitle.add(l1);
 			panelforall.add(panelfortitle,BorderLayout.NORTH);
 	 		displayDoc();
 	 		panelforall.add(panelfortable,BorderLayout.CENTER);
 	 		setVisible(true);
 			setLocation(350,100);
 			setSize(400,400);
 			setResizable(false);
 			setTitle("Current Free Doctors");
 			setContentPane(panelforall);
 	 	}
 	 	
 	 	public void displayDoc()
 	 	{   String query1=null;
 			///////////////////////////////////////////////////
 			if (isBetween(hour, 1,3 )) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty1='FREE');";
 				}
 		    else if (isBetween(hour, 3, 6)) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty2='FREE');";
 				}
 			if (isBetween(hour,6 ,9 )) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty3='FREE');";
 	 				}
 	 		else if (isBetween(hour, 9, 12)) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty4='FREE');";
 	 				}
 			if (isBetween(hour, 12,15 )) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty5='FREE');";
 				}
 		    else if (isBetween(hour, 15, 18)) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty6='FREE');";
 				}
 			
 			if (isBetween(hour, 18,21 )) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty7='FREE');";
 				}
 		    else if (isBetween(hour, 21, 24)) {
 				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,schedule s where(s.name=d.name and s.duty8='FREE');";
 				}
 			/////////////////////////////////////////////////
 			
 			model1 = new DefaultTableModel(col,10);//setting column header and the column number
 			table=new JTable(model1){@Override 
 			public boolean isCellEditable(int arg0, int arg1) { return false; }};
 	        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
 			table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
 			pane = new JScrollPane(table);
 			panelfortable.add(pane);
 			 			 
 			try{			 
 				 Class.forName("com.mysql.jdbc.Driver");
 				 conn = DriverManager.getConnection(url, username, password);
 				 stmt = conn.createStatement();
 			     rs=stmt.executeQuery(query1);
 			     int i=0;
 			     while(rs.next()){
 			    	  
 			     String name=rs.getString(1);
 			     String contact=rs.getString(2);
 			     String speciality=rs.getString(3);
 			     String address=rs.getString(4);
 			     
 			     table.setValueAt(name,i,0); 
				 table.setValueAt(contact,i,1);
				 table.setValueAt(speciality,i,2);
				 table.setValueAt(address,i,3);
			     
				 i++; 
 				 
 				}
 			     /*if ( name[0]==null)
 			     {
 			   System.out.print("Found Nothing!!");}*/
 			     	 rs.close();
 			     	 conn.close();
 			 	 } 
 			catch(ClassNotFoundException cnfe){
 				System.out.print(cnfe+"Error");
 			 }
 			 catch (SQLException sqle){
 				System.out.print(sqle+"Error"); } 
 			
 			}
 	 ///////////////////////////////////////////	
 	 	public static boolean isBetween(int x, int lower, int upper) {
 	 	 boolean boo;
 	 		if(lower<=x && upper>x)
 	 		boo=true;
 	 		else boo=false;
 	 		return boo;
 	 	}
     /////////////////////////////////////////////
 			     }
	

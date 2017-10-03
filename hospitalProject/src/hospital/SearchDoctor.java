package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

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

public class SearchDoctor extends JFrame implements ActionListener {
	 String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
		String username="root";
		String password="new_password";
	Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	 int width1=(2*size.width)/3;
	 int width2=size.width/3;
	 int height=size.height;
 
		 Connection conn;
		 Statement stmt;
		 ResultSet rs;
     int row=0;
     JButton ser,reset,delete,print;
     JPanel p,p1,p2;
     DefaultTableModel model1;
 	JTable table;
 	String col[]={"Name","Speciality","Cons Fee","Contact","Address"};
 	
 	JScrollPane pane;
 	JTextField t;
 	JLabel l,l1;
 	JComboBox c;
 	public JPanel SearchDoctor()
 	{
 		CreateTable();
 		 SearchDoc();
 		p1=new JPanel();
	     p1.setLayout(null);
	   
	 p2.setBounds(0,0,width1,height);
	 p.setBounds(width1,0,width2,height);
	     p1.add(p2);
	  p1.add(p);
		return p1;
 	}
     
     
	public void CreateTable()
	{
		model1 = new DefaultTableModel(col,40);//setting column header and the column number
		table=new JTable(model1){@Override 
			public boolean isCellEditable(int arg0, int arg1) { return true; }};
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
		 table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
		pane = new JScrollPane(table);

		
		p2=new JPanel();
		p2.setLayout(null);
		pane.setBounds(0,0,width1,height-100);
	
		p2.add(pane);
	
		
		
	}
	public void SearchDoc()
        {
		 l=new JLabel("Search By");
		 l.setBounds(20,110,70,50);
		 l1=new JLabel("Search For");
		 l1.setBounds(20,290,70,50);
		 
		 c=new JComboBox(col);
		 c.setBounds(120,125,170,20);
		 
		 t=new JTextField(10);
		 t.setBounds(120,305,170,20);
		 
		 ser=new JButton("Search");
		 ser.setBounds(120,485,80,20);
	
		 reset=new JButton("Reset");
		 reset.setBounds(20,485,70,20);
		
		 delete=new JButton("Delete");
		 delete.setBounds(220,485,110,20);
		 
		 delete.addActionListener(this);
		 ser.addActionListener(this);
		 reset.addActionListener(this);
		
			print=new JButton("Print");
			print.setBounds(20,550,110,20);
			print.addActionListener(this);
			
		 
		 
		 p=new JPanel();
		 p.setLayout(null);
		 p.add(l);p.add(l1);
		 p.add(c);
		 p.add(t);
		 p.add(ser);
		 p.add(reset);
		 p.add(print);
		 p.add(delete);
		 
		 
		 
        }
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==ser){
			for(int j=0;j<=row;j++)
			{
				table.setValueAt(null,j,0); 
				table.setValueAt(null,j,1);
				table.setValueAt(null,j,2);
				table.setValueAt(null,j,3);
				table.setValueAt(null,j,4);	
			}
		
		
			String query1=null;  
		
		
		 String str=(String)c.getSelectedItem();
			
		 if(str.equals("Name"))
			 
		 { 	 if(!(t.getText().matches("[A-Z a-z 0-9]+"))){
			 JOptionPane.showMessageDialog(null, "Please Enter valid name",null, JOptionPane.WARNING_MESSAGE);
		 	 t.setText("");
		 	 t.requestFocus();
		 	 }
		 else{
			 query1="SELECT * FROM DoctorRecord WHERE Name='"+t.getText()+"';";
		 }
		 }
		 else
		 if(str.equals("Speciality"))
		 {   
			 if(!(t.getText().matches("[A-Z a-z 0-9]+"))){
				 JOptionPane.showMessageDialog(null, "Please Enter valid speciality",null, JOptionPane.WARNING_MESSAGE);
			 	 t.setText("");
			 	 t.requestFocus();
			 	 }
			 else
			 {
			  query1="SELECT * FROM DoctorRecord WHERE Speciality='"+t.getText()+"';";
		 }}
		 else
			 if(str.equals("Cons Fee"))
			 {
				 if(!(t.getText().matches("[0-9]+"))){
					 JOptionPane.showMessageDialog(null, "Please Enter amount only in number",null, JOptionPane.WARNING_MESSAGE);
				 	 t.setText("");
				 	 t.requestFocus();
				 	 }
				 else{
				  query1="SELECT * FROM DoctorRecord WHERE ConsFee='"+t.getText()+"';";
				 }
				 }
			 else
				 if(str.equals("Contact"))
				 {
					 if(!(t.getText().matches("[0-9]+"))){
						 JOptionPane.showMessageDialog(null, "Please Enter valid phone number",null, JOptionPane.WARNING_MESSAGE);
					 	 t.setText("");
					 	 t.requestFocus();
					 	 }
					 else{
					  query1="SELECT * FROM DoctorRecord WHERE Contact='"+t.getText()+"';";
				 }}
					  else
					 if(str.equals("Address"))
					 {
						 if(!(t.getText().matches("[A-Z a-z 0-9]+"))){
							 JOptionPane.showMessageDialog(null, "Please Enter valid address",null, JOptionPane.WARNING_MESSAGE);
						 	 t.setText("");
						 	 t.requestFocus();
						 	 }
						 else{
						  query1="SELECT * FROM DoctorRecord WHERE Address='"+t.getText()+"';";
					 }}
		if(query1!=null){
				 
		 try{			 
				Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(url, username, password);
			 stmt = conn.createStatement();
		     rs=stmt.executeQuery(query1);
		     int i=0;
		     while(rs.next()){
					String name1=rs.getString(1);
					String speciality1=rs.getString(2);
					String consfee=rs.getString(3);
					String contact1=rs.getString(4);
					String address1=rs.getString(5);
					
					table.setValueAt(name1,i,0); 
					table.setValueAt(speciality1,i,1);
					table.setValueAt(consfee,i,2);
					table.setValueAt(contact1,i,3);
					table.setValueAt(address1,i,4);
				
					  i++;       
					}
		     if (t.getText()!=null && table.getValueAt(0,1)==null)
		     
		    	 JOptionPane.showMessageDialog(null,"Found Nothing!!","Error",JOptionPane.ERROR_MESSAGE);
		     	 rs.close();
		     	 conn.close();
		 	 
			row=i;
		 } 
		 catch(ClassNotFoundException cnfe){
				System.out.println("Driver could not loaded :"+cnfe);
				
			}
		 catch (SQLException sqle){
			  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
		   	
		 } 
		}
		}
		
		if(e.getSource()==reset){
			
			c.setSelectedItem("Name");
			t.setText("");
		
		}
		
		if(e.getSource()==delete){
			if(table.getSelectedRow()!=-1){
				
				int choice = JOptionPane.showConfirmDialog(null, "Delete selected record?" , "Delete or not", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
				if (choice == JOptionPane.OK_OPTION) {
					int j=table.getSelectedRow();
					String name=(String)table.getValueAt(j,0);
					model1.removeRow(table.getSelectedRow());
			 
					Connection conn;
					Statement stmt;
			 	 
					String query ="DELETE FROM DoctorRecord WHERE Name='"+name+"';";
					 
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
		if(e.getSource()==print){
			try {
				JTable.PrintMode mode=JTable.PrintMode.FIT_WIDTH;
		          String str1=(String)c.getSelectedItem();
					String str2=t.getText();
					MessageFormat headerFormat=new MessageFormat("Doctors searched by "+str1+":\""+str2+"\"");
			          
			          boolean complete=table.print(
			        		  
			        		  mode, headerFormat,
			        		  null);
		          if(complete)
		          {JOptionPane.showMessageDialog(this,"Printing Complete","Printing Result",JOptionPane.INFORMATION_MESSAGE);}
			} catch (PrinterException pe) {
		        	  JOptionPane.showMessageDialog(null,pe,"Error Printing:",JOptionPane.ERROR_MESSAGE);
			  	}
		}
}
		

	
	
}

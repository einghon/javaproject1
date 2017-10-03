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

public class SearchPatientQuery extends JFrame implements ActionListener {
	 Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	 int width1=(2*size.width)/3;
	 int width2=size.width/3;
	 int height=size.height;
	Connection conn;
	Statement stmt;
	ResultSet rs;
    int row=0;
    JButton ser,reset,print;
    JPanel rightPanel,allPanel,leftPanel;
    DefaultTableModel model1;
 	JTable table;
 	String col[]={"Name","Disease","First Date","Room/bed","Prescribed Doctor","Address","TotalBill"};
 	String comboxName[]={"Name","Disease","First Date","Room/bed","Prescribed Doctor"};
 	String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	String username="root";
	String password="new_password";

 	JScrollPane pane;
 	JTextField t;
 	JLabel l,l1;
 	JComboBox c;
 	
 	/*________For panel to find  patient _____*/
 	public JPanel findP()
 	{
 		createTable();
 		findPatient();
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
	public void findPatient()
    {
		l=new JLabel("Search By");
		l.setBounds(20,110,70,50);
		l1=new JLabel("Search For");
		l1.setBounds(20,290,70,50);
		 
		c=new JComboBox(comboxName);
		c.setBounds(120,125,170,20);
		
		t=new JTextField(10);
		t.setBounds(120,305,170,20);
		ser=new JButton("Search");
			
		reset=new JButton("Reset");
		reset.setBounds(20,485,70,20);
		ser.setBounds(120,485,170,20);
		ser.addActionListener(this);
		reset.addActionListener(this);
		
		print=new JButton("Print");
		print.setBounds(20,585,70,20);
		print.addActionListener(this);
		rightPanel=new JPanel();
		rightPanel.setLayout(null);
		rightPanel.add(l);rightPanel.add(l1);
		rightPanel.add(c);
		rightPanel.add(t);
		rightPanel.add(ser);
		rightPanel.add(reset);
		rightPanel.add(print);
	}
	
	public void actionPerformed(ActionEvent e) {
		/*_____________Search Button_______*/
		if(e.getSource()==ser){
			for(int j=0;j<=row;j++)
			{
				table.setValueAt(null,j,0); 
				table.setValueAt(null,j,1);
				table.setValueAt(null,j,2);
				table.setValueAt(null,j,3);
				table.setValueAt(null,j,4);
				table.setValueAt(null,j,5);
				table.setValueAt(null,j,6);
			}
					
			String  query1 = null;
			String str=(String)c.getSelectedItem();
			if(str.equals("Name"))
			{
				query1="SELECT * FROM PATIENT WHERE Name='"+t.getText()+"';";
			}
			else
				if(str.equals("Disease"))
				{   
					query1="SELECT * FROM PATIENT WHERE Disease='"+t.getText()+"';";
				}
				else
					if(str.equals("First Date"))
					{ 
						query1="SELECT * FROM PATIENT WHERE FirstDate='"+t.getText()+"';";
					}
					else
						if(str.equals("Room Number"))
						{
						  query1="SELECT * FROM PATIENT WHERE RoomORBed='"+t.getText()+"';";
						}
						else
							if(str.equals("Prescribed Doctor"))
							{
							  query1="SELECT * FROM PATIENT WHERE PrescribedDoctor='"+t.getText()+"';";
							}
						
			 try{			 
				 Class.forName("com.mysql.jdbc.Driver");
				 conn = DriverManager.getConnection(url, username, password);
				 stmt = conn.createStatement();
			     rs=stmt.executeQuery(query1);
			     int i=0;
			     while(rs.next()){
			    	 String name=rs.getString(1);
			    	 String disease=rs.getString(2);
			      	 String firstDate=rs.getString(3);
			      	 String Room=rs.getString(4);
			      	 String doctor=rs.getString(5);
			      	 String address=rs.getString(6);
			      	 String totalBill=rs.getString(7);
			     	 table.setValueAt(name,i,0); 
					 table.setValueAt(disease,i,1);
					 table.setValueAt(firstDate,i,2);
					 table.setValueAt(Room,i,3);
					 table.setValueAt(doctor,i,4);
					 table.setValueAt(address,i,5);
					 table.setValueAt(totalBill,i,6);
					 i++; 
					 row++;
				}
			    
			     if (t.getText()!=null && table.getValueAt(0,1)==null)
			     {
			    	 JOptionPane.showMessageDialog(null,"Found Nothing!!","Error",JOptionPane.ERROR_MESSAGE);}
			     	 rs.close();
			     	 conn.close();
			 	 } 
			catch(ClassNotFoundException cnfe){
			  JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
			 }
			 catch (SQLException sqle){
				  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
			 } 
			
			}
			
			if(e.getSource()==reset){
				
				c.setSelectedItem("Name");
				t.setText("");
			
			}
			
			if(e.getSource()==print){
				try {JTable.PrintMode mode=JTable.PrintMode.FIT_WIDTH;
				String str1=(String)c.getSelectedItem();
				String str2=t.getText();
				MessageFormat headerFormat=new MessageFormat("Patients searched by "+str1+":\""+str2+"\"");
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

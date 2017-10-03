package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.print.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;


public class PatientList extends Calculate implements ActionListener {
	 Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	 int width1=(2*size.width)/3;
	 int width2=size.width/3;
	 int height=size.height;
	
	JTextField name,disease,firstDate,room,prescribedDr,address;
	JLabel l1,l2,l3,l4,l5,l6;
	JPanel rightPanel,allPanel,leftPanel;
	JButton add,reset,refresh,delete,print;
	DefaultTableModel model1;
	JTable table;
	String col[]={"Name","Disease","First Date","Room/Bed","Prescribed Doctor","Address","TotalBill",};
	String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	String username="root";
	String password="new_password";
	JButton total=new JButton("Total Bill");
	int i=0,namecount=0;
	JScrollPane pane;
	Connection conn;
	Statement stmt;
	ResultSet rs,rs1;
	
	/*_______________Patient List_______*/
	public JPanel PatientList()
	{    DisplayPatient();
	     AddPatient();
	     allPanel=new JPanel();
	     allPanel.setLayout(null);
	   
	     leftPanel.setBounds(0,0,width1,height);
		 rightPanel.setBounds(width1,0,width2,height);
	     allPanel.add(leftPanel);
	     allPanel.add(rightPanel);
		return allPanel;
	}
	
	
	/*__________________Display Patient_______*/
	public void DisplayPatient()
	{
		String query="SELECT * FROM PATIENT ORDER BY Name";
		model1 = new DefaultTableModel(col,40);//setting column header and the column number
		table=new JTable(model1){@Override 
		public boolean isCellEditable(int arg0, int arg1) 
				{ return false; }};
        
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
		table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
		//table.setName("Patient Record");
		pane = new JScrollPane(table);
		pane.setBounds(0,0,900,600);
		
		refresh=new JButton("Refresh");
		refresh.setBounds(250,625,100,20);
		refresh.addActionListener(this);	
		
		delete=new JButton("Delete");
		delete.setBounds(400,625,100,20);
		delete.addActionListener(this);	

		print=new JButton("Print");
		print.setBounds(550,625,100,20);
		print.addActionListener(this);	
		
		
		leftPanel=new JPanel();
		leftPanel.setLayout(null);
		leftPanel.add(pane);
		leftPanel.add(refresh);
		leftPanel.add(delete);
		leftPanel.add(print);
	     
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);

			while(rs.next()){
				String name1=rs.getString(1);
				String disease1=rs.getString(2);
				Date firstDate=new Date(rs.getDate(3).getTime());
				String room1=rs.getString(4);
				String preDr1=rs.getString(5);
				String address=rs.getString(6);
				String totalBill=rs.getString(7);
				
				table.setValueAt(name1,i,0); 
				table.setValueAt(disease1,i,1);
				table.setValueAt(firstDate,i,2);
				table.setValueAt(room1,i,3);
				table.setValueAt(preDr1,i,4);
				table.setValueAt(address,i,5);
				table.setValueAt(totalBill,i,6); 
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
	
	/*_________________Adding Patient_______*/
	public void AddPatient()
	{
		l1=new JLabel("Name"); 
		l1.setBounds(20,0,70,50);
		l2=new JLabel("Disease");
		l2.setBounds(20,90,70,50);
		l3=new JLabel("First Date");
		l3.setBounds(20,180,70,50);
		l4=new JLabel("Room/Bed");
		l4.setBounds(20,270,70,50);
		l5=new JLabel("Prescribed Doctor");
		l5.setBounds(20,358,110,50);
		l6=new JLabel("Address");
		l6.setBounds(20,448,70,50);
	
		name=new JTextField("Mg Mg",10);
		name.setBounds(150,15,170,20);
		/*________________________*/
		name.setForeground(Color.gray);		
		
		name.addFocusListener(new FocusListener(){
    		@Override
    		public void focusGained(FocusEvent e){
    			if(name.getText().equals("Mg Mg")){
    				name.setText("");
    				name.setForeground(Color.black);
    			}
    		}
    		@Override
    		public void focusLost(FocusEvent e){
    			if( name.getText().isEmpty()){
    				name.setForeground(Color.gray);
    				name.setText("Mg Mg");
    			}
    		}
    	});
		/*___________________*/

		name.setDropTarget(null);
		name.setFocusTraversalKeysEnabled(false);
		name.requestFocus();
		//name.getCaret().setVisible(false);
		name.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			/*	String txt=name.getText();
				if(name.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					name.setText("");
					name.requestFocus();
				}
				else{
					try{
						String regx="^[a-zA-Z \\s]{1,}[\\.]{0,1}[a-zA-Z0-9 \\s]{0,}$";
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid name!");
							name.setText("");
							name.requestFocus();
						}
						else{
						//	disease.setFocusTraversalKeysEnabled(false);
							disease.requestFocus();
							
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid name!");
					}
				}*/
				regxName();
			}
				
		});
		
		disease=new JTextField("Disease",10);
		disease.setBounds(150,105,170,20);
		/*_____________________*/
		disease.setForeground(Color.gray);		
		
		disease.addFocusListener(new FocusListener(){
    		@Override
    		public void focusGained(FocusEvent e){
    			if(disease.getText().equals("Disease")){
    				disease.setText("");
    				disease.setForeground(Color.black);
    			}
    		}
    		@Override
    		public void focusLost(FocusEvent e){
    			if( disease.getText().isEmpty()){
    				disease.setForeground(Color.gray);
    				disease.setText("Disease");
    			}
    		}
    	});
		/*___________________*/
		disease.setDropTarget(null);
		disease.setFocusTraversalKeysEnabled(false);
		disease.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				regxDisease();
							}
		});
		firstDate=new JTextField("yy/mm/dd",10);
		firstDate.setBounds(150,195,170,20);
		
		/*______________________*/
		firstDate.setForeground(Color.gray);		
		
		firstDate.addFocusListener(new FocusListener(){
    		@Override
    		public void focusGained(FocusEvent e){
    			if(firstDate.getText().equals("yy/mm/dd")){
    				firstDate.setText("");
    				firstDate.setForeground(Color.black);
    			}
    		}
    		@Override
    		public void focusLost(FocusEvent e){
    			if( firstDate.getText().isEmpty()){
    				firstDate.setForeground(Color.gray);
    				firstDate.setText("yy/mm/dd");
    			}
    		}
    	});
		/*_________________*/
		
		firstDate.setFocusTraversalKeysEnabled(false);

				firstDate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				regxDate();
							}
		});
		
		room=new JTextField("A1",10);
		room.setBounds(150,285,170,20);		
		/*______________________*/
		room.setForeground(Color.gray);		
		
		room.addFocusListener(new FocusListener(){
    		@Override
    		public void focusGained(FocusEvent e){
    			if(room.getText().equals("A1")){
    				room.setText("");
    				room.setForeground(Color.black);
    			}
    		}
    		@Override
    		public void focusLost(FocusEvent e){
    			if( room.getText().isEmpty()){
    				room.setForeground(Color.gray);
    				room.setText("A1");
    			}
    		}
    	});
		/*_________________*/
		
		room.setFocusTraversalKeysEnabled(false);		
		room.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				regxRoom();	}
		});
		
		prescribedDr=new JTextField("Dr Name",10);
		prescribedDr.setBounds(150,375,170,20);
		/*______________________*/
		prescribedDr.setForeground(Color.gray);		
		
		prescribedDr.addFocusListener(new FocusListener(){
    		@Override
    		public void focusGained(FocusEvent e){
    			if(prescribedDr.getText().equals("Dr Name")){
    				prescribedDr.setText("");
    				prescribedDr.setForeground(Color.black);
    			}
    		}
    		@Override
    		public void focusLost(FocusEvent e){
    			if( prescribedDr.getText().isEmpty()){
    				prescribedDr.setForeground(Color.gray);
    				prescribedDr.setText("Dr Name");
    			}
    		}
    	});
		/*_________________*/
		
		prescribedDr.setFocusTraversalKeysEnabled(false);
		prescribedDr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				regxDoctor();	}
		});
		
		address=new JTextField("address",10);
		address.setBounds(150,465,170,20);
        /*__________________________*/
		address.setForeground(Color.gray);		
		
		address.addFocusListener(new FocusListener(){
    		@Override
    		public void focusGained(FocusEvent e){
    			if( address.getText().equals("address")){
    				address.setText("");
    				address.setForeground(Color.black);
    			}
    		}
    		@Override
    		public void focusLost(FocusEvent e){
    			if(address.getText().isEmpty()){
    				address.setForeground(Color.gray);
    				address.setText("address");
    			}
    		}
    	});
		/*_________*/
		address.setFocusTraversalKeysEnabled(false);
				address.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				regxAddr();
			}	
		});

		total.setBounds(100,550,170,20);
		total.addActionListener(this);
		total.setFocusTraversalKeysEnabled(false);
		add=new JButton("Add");	
		reset=new JButton("Reset");
		reset.setBounds(70,600,70,20);
		add.setBounds(240,600,70,20);
		reset.addActionListener(this);
		add.addActionListener(this);
		
		
		rightPanel=new JPanel();
		rightPanel.setLayout(null);
		rightPanel.add(add);
		rightPanel.add(total);
		rightPanel.add(reset);
		rightPanel.add(l1);
		rightPanel.add(l2);
		rightPanel.add(l3);
		rightPanel.add(l4);
		rightPanel.add(l5);
		rightPanel.add(l6);
		rightPanel.add(name);
		rightPanel.add(disease);
		rightPanel.add(firstDate);
		rightPanel.add(room);
		rightPanel.add(prescribedDr);
		rightPanel.add(address);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		/*____________________Delete_________*/
		
	if(e.getSource()==delete){
		if(table.getSelectedRow()!=-1){
			
			int choice = JOptionPane.showConfirmDialog(null, "Delete selected record?" , "Delete or not", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
			if (choice == JOptionPane.OK_OPTION) {
				int j=table.getSelectedRow();
				String room=(String)table.getValueAt(j,3);
				model1.removeRow(table.getSelectedRow());
		 
				Connection conn;
				Statement stmt;
		 	 
				String query ="DELETE FROM PATIENT WHERE RoomORBed='"+room+"';";
				 
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
	
	
	/*_______________Reset_____________*/
	if(e.getSource()==reset){
		name.setText("");
		disease.setText("");
		firstDate.setText("");
		room.setText("");
		prescribedDr.setText("");
		address.setText("");

	}
	
	/*________________Total________*/
	if(e.getSource()==total){
		
		Calculate mainFrame	= new Calculate();
		mainFrame.setVisible( true );
		
	}
	
	/*_________________Add__________*/
	if(e.getSource()==add){
		if(regxName() && regxDisease() && regxDate() && regxDoctor() && regxRoom() && regxAddr()&& 
				super.getS1()!=null){
			 Connection con;
			 Statement st;
			 String sql="Select name from patient where name= '" + name.getText() + "'";
			 try{			 
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, username, password);
					 st= con.createStatement();
					 rs1=st.executeQuery(sql);
					 if(rs1.next()){
						 JOptionPane.showMessageDialog( this, "Name already exists","Error", JOptionPane.ERROR_MESSAGE);
					        name.setText("");
					        name.requestFocus();
					        disease.setText("");
					        firstDate.setText("");
					        room.setText("");
					        prescribedDr.setText("");
					        address.setText("");
					    
					 }
				     
				 } 
				 catch(ClassNotFoundException cnfe){
					 JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
						
				 }
				 catch (SQLException sqle){
					  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
				   	
				 } 
			if(!name.getText().equals("")){
				table.setValueAt(name.getText(),i,0); 
			table.setValueAt(disease.getText(),i,1);
			table.setValueAt(firstDate.getText(),i,2);
			table.setValueAt(room.getText(),i,3);
			table.setValueAt(prescribedDr.getText(),i,4);
			table.setValueAt(address.getText(),i,5);
			table.setValueAt(super.getS1(), i, 6);
			i++;
	 
			Connection conn;
			Statement stmt;
			  	
			String query = "INSERT INTO PATIENT VALUES ('"+name.getText()+"','"+disease.getText()+"','"
					+firstDate.getText()+"','"+room.getText()+"','"+prescribedDr.getText()+"','"+address.getText()+"','"+super.getS1()+"')";
					
			try{			 
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, username, password);
				 stmt = conn.createStatement();
			     int res=stmt.executeUpdate(query);	
			     if(res==1) 
						JOptionPane.showMessageDialog(this, "Successfully Insert!");
			     name.setText("");
			        name.requestDefaultFocus();
			        name.requestFocus();
			       // t1.setText("");
			     
			     conn.close();
			 } 
			 catch(ClassNotFoundException cnfe){
				 JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
					
			 }
			 catch (SQLException sqle){
				  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
			   	
			 } 
			
		}			/*_____________________Check Duplication________________
			Connection con;
			Statement st;
			String sql="Select name from patient where name= '" + name.getText() + "'";
			try{			 
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
				 st= con.createStatement();
				 rs=st.executeQuery(sql);
				 if(rs.next()){
					 int choice=JOptionPane.showConfirmDialog(null, "Name Already exit.Add or Not" ,
							 "Name Add Or Not", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
					 if (choice == JOptionPane.CANCEL_OPTION) {
						 name.setText("");
					     name.requestFocus();
					}
					if(choice==JOptionPane.OK_OPTION){
						 	namecount+=1;
					}}
		table.setValueAt(name.getText(),i,0); 
		table.setValueAt(disease.getText(),i,1);
		String fd=(String)firstDate.getText();
		DateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
		//Date d1;
		try{
			java.util.Date d11=formatter.parse(fd);
			
			java.sql.Date sd=new java.sql.Date(d11.getTime());
			//String newDS=formatter.format(d1);
			table.setValueAt(sd,i,1);
		}
		catch(ParseException exp){
			exp.printStackTrace();
		}
		//String f
		table.setValueAt(firstDate.getText(),i,2);
		table.setValueAt(room.getText(),i,3);
		table.setValueAt(prescribedDr.getText(),i,4);
		table.setValueAt(address.getText(),i,5);
		table.setValueAt(super.getS1(), i, 6);
		i++;
 
		Connection conn;
		Statement stmt;
		  	
		String query = "INSERT INTO PATIENT VALUES ('"+name.getText()+"','"+disease.getText()+"','"
		+firstDate.getText()+"','"+room.getText()+"','"+prescribedDr.getText()+"','"+address.getText()+"','"+super.getS1()+"')";
					 
		try{			 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			 stmt = conn.createStatement();
		     int res=stmt.executeUpdate(query);	
		     if(res==1) {
					JOptionPane.showMessageDialog(this, "Successfully Insert!");
		     conn.close();}
		 } 
		 catch(ClassNotFoundException cnfe){
			 JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
				
		 }
		 catch (SQLException sqle){
			  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
		   	
		 }}
		 catch(ClassNotFoundException cnfe){
			 JOptionPane.showMessageDialog(null,cnfe,"Error",JOptionPane.ERROR_MESSAGE);
				
		 }
		 catch (SQLException sqle){
			  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
		   	
		 } */
		}

		else{
			JOptionPane.showMessageDialog(null, "Please fill all required field!","error",
					JOptionPane.ERROR_MESSAGE);
			name.setText("");
			disease.setText("");
			firstDate.setText("");
			room.setText("");
			prescribedDr.setText("");
			address.setText("");
			name.requestFocus();
		}

	 }//e.getsource
/*_________________Refresh________________*/
	if(e.getSource()==refresh){
		String query="SELECT * FROM PATIENT ORDER BY Name";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			i=0;
			while(rs.next()){
				String name1=rs.getString(1);
				String disease1=rs.getString(2);
				Date firstDate=new Date(rs.getDate(3).getTime());
				//String firstDate=rs.getString(3);
				String room1=rs.getString(4);
				String preDr1=rs.getString(5);
				String address=rs.getString(6);
				String totalBill=rs.getString(7);
				
				table.setValueAt(name1,i,0); 
				table.setValueAt(disease1,i,1);
				
				table.setValueAt(firstDate,i,2);
				table.setValueAt(room1,i,3);
				table.setValueAt(preDr1,i,4);
				table.setValueAt(address,i,5);
				table.setValueAt(totalBill,i,6);
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
			if(e.getSource()==print){
				try
			{
					JTable.PrintMode mode=JTable.PrintMode.FIT_WIDTH;
					MessageFormat headerFormat=new MessageFormat("PatientRecord");
			          boolean complete=table.print(
			        		  
			        		  mode, headerFormat,
			        		  null);
			          if(complete)
			          {JOptionPane.showMessageDialog(this,"Printing Complete","Printing Result",JOptionPane.INFORMATION_MESSAGE);}

			}catch (PrinterException pe) {
			        	  JOptionPane.showMessageDialog(null,pe,"Error Printing:",JOptionPane.ERROR_MESSAGE);
				  	 }
			}

}
	public boolean regxName(){
		String txt=name.getText();
		if(name.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
			name.setText("");
			name.requestFocus();
			return false;
		}
		else{
			try{
				String regx="^[a-zA-Z \\s]{1,}[\\.]{0,1}[a-zA-Z0-9 \\s]{0,}$";
				Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
				Matcher matcher=pattern.matcher(txt);
				boolean m=matcher.find();
				if(m==false){
					JOptionPane.showMessageDialog(null, "invalid name!");
					name.setText("");
					name.requestFocus();
					return false;
				}
				else{
				//	disease.setFocusTraversalKeysEnabled(false);
					disease.requestFocus();
					
				}
			//	confee.requestFocus();
				
			}
			catch(Exception exp){
				JOptionPane.showMessageDialog(null, "invalid name!");
			}
		}
		return true;
	}
	public boolean regxDisease(){
		String txt=disease.getText();
		if(disease.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
			disease.setText("");
			disease.requestFocus();
			return false;
		}
		else{
			try{
				
				String regx="^[a-zA-Z \\s]{1,}[\\.]{0,1}[a-zA-Z0-9 \\s]{0,}$";
				Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
				Matcher matcher=pattern.matcher(txt);
				boolean m=matcher.find();
				if(m==false){
					JOptionPane.showMessageDialog(null, "invalid disease name!");
					disease.setText("");
					disease.requestFocus();
					return false;
				}
				else{
					firstDate.requestFocus();
				}
			//	confee.requestFocus();
				
			}
			catch(Exception exp){
				JOptionPane.showMessageDialog(null, "invalid disease name!");
			}
		}
return true;
	}
	public boolean regxDate(){
		String txt=firstDate.getText();
		if(firstDate.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
			firstDate.setText("");
			firstDate.requestFocus();
			return false;
		}
		else{
			try{	  // String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
				String DATE_PATTERN="((?:19|20)\\d\\d)/(0?[1-9]|1[012])/([12][0-9]|3[01]|0?[1-9])";
			   Pattern pattern = Pattern.compile(DATE_PATTERN);
		     Matcher matcher = pattern.matcher(txt);
		     Pattern pattern1=Pattern.compile("^[a-zA-Z]+$",Pattern.CASE_INSENSITIVE);
		     	Matcher m1=pattern1.matcher(txt);
				boolean m=m1.find();
				if(m==true){
					JOptionPane.showMessageDialog(null,"Invalid date\nyear/month/day");
					firstDate.setText("");
					firstDate.requestFocus();
					return false;
					
				}else {
		     if(matcher.matches()){

			 matcher.reset();

			 if(matcher.find())
			 {
				 //JOptionPane.showMessageDialog(null,"Match found");
		         String day = matcher.group(3);
			     String month = matcher.group(2);
			     int year = Integer.parseInt(matcher.group(1));
			     	//JOptionPane.showMessageDialog(null, day+"\n"+month+"\n"+year);
			     if(year>2017)
			     {
			    	 JOptionPane.showMessageDialog(null, "Year must not exceed 2017!");		
						firstDate.setText("");
						firstDate.requestFocus();
			     }
			     
			     else {if (day.equals("31") &&
				  (month.equals("4") || month .equals("6") || month.equals("9") ||
		                  month.equals("11") || month.equals("04") || month .equals("06") ||
		                  month.equals("09"))
				  )
			     {
					JOptionPane.showMessageDialog(null, "Your day is not valid");		// only 1,3,5,7,8,10,12 has 31 day
					firstDate.setText("");
					firstDate.requestFocus();
					return false;// only 1,3,5,7,8,10,12 has 31 day
			     } else if (month.equals("2") || month.equals("02")) 
			     {
		                  //leap year
				  if(year % 4==0)
				  {
					  if(day.equals("30") || day.equals("31"))
					  {
						  //return false;
						  JOptionPane.showMessageDialog(null, "Your day is not valid");		
							firstDate.setText("");
							firstDate.requestFocus();
							return false;
					  }else{
						//  return true;
						  room.requestFocus();
					  }
				  }else
				  {
				         if(day.equals("29")||day.equals("30")||day.equals("31"))
				         {
						 // return false;
				        	 JOptionPane.showMessageDialog(null, "Your day is not valid");		
				 			firstDate.setText("");
				 			firstDate.requestFocus();
				 			return false;
				         }else{
						  //return true;
				        	 room.requestFocus();
					  }
				  }
			      }else{
				//return true;
			    	  room.requestFocus();
			      }
			   }
			 }else
			   {
				   //return false;
				   JOptionPane.showMessageDialog(null, "Your day is not valid");		
					firstDate.setText("");
					firstDate.requestFocus();
					return false;
			   }
		     }
		     else
		     {
			  //return false;
		    	 JOptionPane.showMessageDialog(null, "Your day is not valid");	
					firstDate.setText("");
					firstDate.requestFocus();
					return false;
		     }
			}
		    		//room.requestFocus();
			
			}
			
			catch(Exception exp){
				JOptionPane.showMessageDialog(null, "invalid date!");
			}
		}
return true;
	}

	public boolean regxRoom(){

		String txt=room.getText();
		if(room.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
			room.setText("");
			room.requestFocus();
			return false;
		}
		else{
			try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
				
				Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
				Matcher matcher=pattern.matcher(txt);
				boolean m=matcher.find();
				if(m==false){
					JOptionPane.showMessageDialog(null, "invalid Room!");
					room.setText("");
					room.requestFocus();
					return false;
				}
				else{
					prescribedDr.requestFocus();
				}
			//	confee.requestFocus();
				
			}
			catch(Exception exp){
				JOptionPane.showMessageDialog(null, "invalid Room!");
			}
			return true;
		}

	}
public boolean regxDoctor(){
	String txt=prescribedDr.getText();
	if(prescribedDr.getText().isEmpty()){
		JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
		prescribedDr.setText("");
		prescribedDr.requestFocus();
		return false;
	}
	else{
		try{
			String regx="^[a-zA-Z \\s]{1,}[\\.]{0,1}[a-zA-Z0-9 \\s]{0,}$";
			Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
			Matcher matcher=pattern.matcher(txt);
			boolean m=matcher.find();
			if(m==false){
				JOptionPane.showMessageDialog(null, "invalid Doctor name!");
				prescribedDr.setText("");
				prescribedDr.requestFocus();
				return false;
			}
			else{
				address.requestFocus();
			}
		//	confee.requestFocus();
			
		}
		catch(Exception exp){
			JOptionPane.showMessageDialog(null, "invalid Doctor name!");
		}
	}return true;
}
public boolean regxAddr(){

	if(address.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Please fill all required filed!","error",JOptionPane.ERROR_MESSAGE);
		address.setText("");
		return false;
	}
	return true;
}
	}
	
	
package hospital;



import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Schedule extends JFrame implements ActionListener {

	 Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	 int width1=(2*size.width)/3;
	 int width2=size.width/3;
	 int height=size.height;
	JButton add,reset,print;
	JTextField name,duty1,duty2,duty3,duty4,duty5,duty6,duty7,duty8;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JPanel p1,p,p2;
	DefaultTableModel model1;
	JTable table;
	String col[]={"Name","1:00-3:00","3:00-6:00","6:00-9:00","9:00-12:00","12:00-15:00","15:00-18:00","18:00-21:00","21:00-24:00"};
	JButton search;
	JPanel p3;
	int i=0;
	JScrollPane pane;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	String username="root";
	String password="new_password";
	public JPanel Schedule()
	{    DisplayDoc();
	     AddDoc();
	     p1=new JPanel();
	     p1.setLayout(null);
	     p2.setBounds(0,0,width1,height);
		 p.setBounds(width1,0,width2,height);
	  p1.add(p2);
	  p1.add(p);
		return p1;
	}
	public void DisplayDoc()
	{
	
		
		String query="SELECT * FROM DocSchedule1 ORDER BY name";
		model1 = new DefaultTableModel(col,40);							//setting column header and the column number
		table=new JTable(model1){@Override 
			public boolean isCellEditable(int arg0, int arg1) { return false; }};
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);		//for the flexible column width 
		 table.setPreferredScrollableViewportSize(table.getPreferredSize());			//for the flexible column width 
		pane = new JScrollPane(table);
		pane.setBounds(0,0,width1,height);
		search=new JButton("Search");
		search.addActionListener(this);
		search.setBounds(300,0,100,20);
		p3=new JPanel();
		p3.setLayout(null);
		p3.add(search);
		pane.setBounds(0,0,width1,height-100);
	    p3.setBounds(0,height-100,width1,100);
		p2=new JPanel();
        p2.setLayout(null);
        p2.add(pane);
        p2.add(p3);
       
        try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);

			while(rs.next()){
				String name1=rs.getString(1);
				String duty1a=rs.getString(2);					
				String duty2a=rs.getString(3);
				String duty3a=rs.getString(4);
				String duty4a=rs.getString(5);
				String duty5a=rs.getString(6);
				String duty6a=rs.getString(7);
				String duty7a=rs.getString(8);
				String duty8a=rs.getString(9);
				
				table.setValueAt(name1, i, 0);
				table.setValueAt(duty1a, i, 1);
				table.setValueAt(duty2a, i, 2);
				table.setValueAt(duty3a, i, 3);
				table.setValueAt(duty4a, i, 4);
				table.setValueAt(duty5a, i, 5);
				table.setValueAt(duty6a, i, 6);
				table.setValueAt(duty7a, i, 7);
				table.setValueAt(duty8a, i, 8);
				  i++;       
				}
		rs.close();
		conn.close();
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("Driver could not loaded :"+cnfe);
			
		}
		catch(SQLException sqle){
			System.out.println("SQL Exception thrown :"+sqle);
		}
		
	}
	public void AddDoc()
	{
		l1=new JLabel("Name");
		l1.setBounds(20,20,70,50);
		l2=new JLabel("1:00-3:00");
		l2.setBounds(20,70,70,50);
		l3=new JLabel("3:00-6:00");
		l3.setBounds(20,120,70,50);
		l4=new JLabel("6:00-9:00");
		l4.setBounds(20,170,70,50);
		l5=new JLabel("9:00-12:00");
		l5.setBounds(20,220,70,50);
		l6=new JLabel("12:00-15:00");
		l6.setBounds(20,270,70,50);
		l7=new JLabel("15:00-18:00");
		l7.setBounds(20,320,70,50);
		l8=new JLabel("18:00-21:00");
		l8.setBounds(20,370,70,50);
		l9=new JLabel("21:00-24:00");
		l9.setBounds(20,420,70,50);
		
		name=new JTextField(10);
		name.setBounds(120,35,170,20);
		name.addActionListener(this);
		duty1=new JTextField(10);
		duty1.setBounds(120,85,170,20);
		duty1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty1.getText();
				if(duty1.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty1.setText("");
					duty1.requestFocus();
				}
				else{
					duty2.requestFocus();
					/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty1.setText("");
							duty1.requestFocus();
						}
						else{
							duty2.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
				}
			
				
			}
			
		});
		
		duty2=new JTextField(10);
		duty2.setBounds(120,135,170,20);
		duty2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty2.getText();
				if(duty2.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty2.setText("");
					duty2.requestFocus();
				}
				else{/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty2.setText("");
							duty2.requestFocus();
						}
						else{
							duty3.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					duty3.requestFocus();
				}
			
				
			}
			
		});
		
		duty3=new JTextField(10);
		duty3.setBounds(120,185,170,20);
		duty3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty3.getText();
				if(duty3.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty3.setText("");
					duty3.requestFocus();
				}
				else{
					/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty3.setText("");
							duty3.requestFocus();
						}
						else{
							duty4.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					duty4.requestFocus();
				}
			
				
			}
			
		});
		duty4=new JTextField(10);
		duty4.setBounds(120,235,170,20);
		duty4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty4.getText();
				if(duty4.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty4.setText("");
					duty4.requestFocus();
				}
				else{/*
			/*		try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty4.setText("");
							duty4.requestFocus();
						}
						else{
							duty5.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					duty5.requestFocus();
				}
			
				
			}
			
		});
		duty5=new JTextField(10);
		duty5.setBounds(120,285,170,20);
		duty5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty5.getText();
				if(duty5.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty5.setText("");
					duty5.requestFocus();
				}
				else{/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty5.setText("");
							duty5.requestFocus();
						}
						else{
							duty6.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					duty6.requestFocus();
				}
			
				
			}
			
		});
		duty6=new JTextField(10);
		duty6.setBounds(120,335,170,20);
		duty6.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty6.getText();
				if(duty6.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty6.setText("");
					duty6.requestFocus();
				}
				else{/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty6.setText("");
							duty6.requestFocus();
						}
						else{
							duty7.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					duty7.requestFocus();
				}
			
				
			}
			
		});
		duty7=new JTextField(10);
		duty7.setBounds(120,385,170,20);
		duty7.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty7.getText();
				if(duty7.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty7.setText("");
					duty7.requestFocus();
				}
				else{/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty7.setText("");
							duty7.requestFocus();
						}
						else{
							duty8.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					duty8.requestFocus();
				}
			
				
			}
			
		});
		duty8=new JTextField(10);
		duty8.setBounds(120,435,170,20);
		duty8.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt2=(String)duty8.getText();
				if(duty8.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
					duty8.setText("");
					duty8.requestFocus();
				}
				else{/*
					try{String regx="^[a-zA-Z]{1,}[0-9]{1,3}$";
						
						Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
						Matcher matcher=pattern.matcher(txt2);
						boolean m=matcher.find();
						if(m==false){
							JOptionPane.showMessageDialog(null, "invalid Room!");
							duty8.setText("");
							duty8.requestFocus();
						}
						else{
							add.requestFocus();
						}
					//	confee.requestFocus();
						
					}
					catch(Exception exp){
						JOptionPane.showMessageDialog(null, "invalid Room!");
					}*/
					
				}
			
				
			}
			
		});
		
		 add=new JButton("Add");
	
		 reset=new JButton("Reset");
		 print=new JButton("Print");
	 	  print.setBounds(20,525,100,20);
	      print.addActionListener(this);	
		 reset.setBounds(20,485,70,20);
		 add.setBounds(120,485,170,20);
		 add.addActionListener(this);
		 reset.addActionListener(this);
		p=new JPanel();
		 p.setLayout(null);
		 p.add(add);
		
		 p.add(reset);
		 p.add(print);
		 p.add(l1);
		 p.add(l2);
		 p.add(l3);
		 p.add(l4);
		 p.add(l5);
		 p.add(l6);
		 p.add(l7);
		 p.add(l8);
		 p.add(l9);
		 p.add(name);
		 p.add(duty1);
		 p.add(duty2);
		 p.add(duty3);
		 p.add(duty4);
		 p.add(duty5);
		 p.add(duty6);
		 p.add(duty7);
		 p.add(duty8);
	
		 /*setContentPane(p);
	     setSize(300,500);
		 
	     setTitle("DoctorRecord");
	     setSize(400,500);
	     setLocation(100,100);
	     setResizable(true);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setVisible(true); */
		}
	
	public static void main(String args[])
	{
	new Schedule();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add){
			table.setValueAt(name.getText(),i,0); 
			table.setValueAt(duty1.getText(),i,1);
			table.setValueAt(duty2.getText(),i,2);
			table.setValueAt(duty3.getText(),i,3);
			table.setValueAt(duty4.getText(),i,4);
			table.setValueAt(duty5.getText(),i,5);
			table.setValueAt(duty6.getText(),i,6);
			table.setValueAt(duty7.getText(),i,7);
			table.setValueAt(duty8.getText(),i,8);
			i++;
			
			  
 
		 Connection conn;
		 Statement stmt;
		 String query = "INSERT INTO DocSchedule1 VALUES ('"+name.getText()+"','"+
		 duty1.getText()+"','"+duty2.getText()+"','"+duty3.getText()+"','"+
				 duty4.getText()+"','"+duty5.getText()+"','"+duty6.getText()+"','"+duty7.getText()+"','"+duty8.getText()+"')";
							 
		 try{			 
				Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(url, username, password);
			 stmt = conn.createStatement();
		     int res=stmt.executeUpdate(query);	
		     if(res==1) 
					JOptionPane.showMessageDialog(this, "Successfully Insert!");
		     
		     conn.close();
		 } 
		 catch(ClassNotFoundException cnfe){
				System.out.println("Driver could not loaded :"+cnfe);
				
			}
		 catch (SQLException sqle){
			  JOptionPane.showMessageDialog(null,sqle,"Error",JOptionPane.ERROR_MESSAGE);
		   	
		 } 
		
		}
		if(e.getSource()==name){
			String txt=name.getText();
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
						name.setText(" ");
						name.requestFocus();
					}
					else{
						duty1.requestFocus();
					}
				//	confee.requestFocus();
					
				}
				catch(Exception exp){
					JOptionPane.showMessageDialog(null, "invalid name!");
				}
			}
		
	
		
		
		if(e.getSource()==reset)
		{
			
			name.setText(" ");
			duty1.setText("");
			duty2.setText("");
			duty3.setText("");
			duty4.setText("");
			duty5.setText("");
			duty6.setText("");
			duty7.setText("");
			duty8.setText("");
		
		}
		
	    if(e.getSource()==search)
	    {
	    	ScheduleQuery mainFrame	= new ScheduleQuery();
			mainFrame.setVisible( true );
	    }
		if(e.getSource()==print){
			try {JTable.PrintMode mode=JTable.PrintMode.FIT_WIDTH;
			MessageFormat headerFormat=new MessageFormat("Doctor's Schedule");
	          boolean complete=table.print(
	        		  
	        		  mode, headerFormat,
	        		  null);
		          if(complete)
		          {JOptionPane.showMessageDialog(this,"Printing Complete","Printing Result",JOptionPane.INFORMATION_MESSAGE);}
			} catch (PrinterException pe) {
		        	  JOptionPane.showMessageDialog(null,pe,"Error Printing:",JOptionPane.ERROR_MESSAGE);
			  	}
		}
		if(e.getSource()==duty1){
			

		}

		}}
}
